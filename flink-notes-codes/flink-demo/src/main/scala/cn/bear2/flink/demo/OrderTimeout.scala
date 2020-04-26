package cn.bear2.flink.demo

import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

// 定义输入订单事件的样例类
case class OrderEvent(orderId: Long, eventType: String, txId: String, eventTime: Long)

// 定义输出结果样例类
case class OrderResult(orderId: Long, resultMsg: String)

object OrderTimeout {
  val orderTimeoutOutputTag = new OutputTag[OrderResult]("orderTimeout")

  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val orderEventStream = env.socketTextStream("127.0.0.1", 9999)
      .map(data => {
        val dataArray = data.split(",")
        OrderEvent(dataArray(0).trim.toLong, dataArray(1).trim, dataArray(2).trim, dataArray(3).trim.toLong)
      })
      .assignAscendingTimestamps(_.eventTime * 1000L)
      .keyBy(_.orderId)

    val orderResultStream = orderEventStream.process(new OrderPayMatch())
    orderResultStream.print("payed")
    orderResultStream.getSideOutput(orderTimeoutOutputTag).print("time out order")
    env.execute("order timeout without cep job")

  }

  class OrderPayMatch() extends KeyedProcessFunction[Long, OrderEvent, OrderResult]() {
    lazy val isPayedState: ValueState[Boolean] = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("is-payed-state", classOf[Boolean]))
    lazy val timerState: ValueState[Long] = getRuntimeContext.getState(new ValueStateDescriptor[Long]("timer-state", classOf[Long]))

    override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[Long, OrderEvent, OrderResult]#OnTimerContext, out: Collector[OrderResult]): Unit = {
      val isPayed = isPayedState.value()
      if (isPayed) {
        ctx.output(orderTimeoutOutputTag, OrderResult(ctx.getCurrentKey, "payed but no create"))
      } else {
        // 典型场景，只create,没有pay
        ctx.output(orderTimeoutOutputTag, OrderResult(ctx.getCurrentKey, " order timeout"))
      }
      isPayedState.clear()
      timerState.clear()
    }

    override def processElement(value: OrderEvent, ctx: KeyedProcessFunction[Long, OrderEvent, OrderResult]#Context, out: Collector[OrderResult]): Unit = {
      val isPayed = isPayedState.value()
      val timerTs = timerState.value()
      if (value.eventType == "create") {
        // 乱序行为，先到pay再到create
        if (isPayed) {
          out.collect(OrderResult(value.orderId, "payed successfully"))
          ctx.timerService().deleteEventTimeTimer(timerTs)
          isPayedState.clear()
          timerState.clear()
        } else {
          // 已创建订单未支付,设置定时器
          val ts = value.eventTime * 1000L + 15 * 60 * 1000L
          ctx.timerService().registerEventTimeTimer(ts)
          timerState.update(ts)
        }
      } else if (value.eventType == "pay") {
        //假如有定时器，说明create过
        if (timerTs > 0) {
          // timerTs 是 认为超时后的时间戳
          if (timerTs > value.eventTime * 1000L) {
            out.collect(OrderResult(value.orderId, "payed successfully"))
          } else {
            ctx.output(orderTimeoutOutputTag, OrderResult(value.orderId, "this order is timeout"))
          }
          ctx.timerService().deleteEventTimeTimer(timerTs)
          isPayedState.clear()
          timerState.clear()
        } else {
          // 先来pay
          isPayedState.update(true)
          // 等待watermark时间
          ctx.timerService().registerEventTimeTimer(value.eventTime * 1000L)
          timerState.update(value.eventTime * 1000L)
        }
      }
    }
  }

}
