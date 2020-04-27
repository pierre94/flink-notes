package cn.bear2.flink.demo

import java.text.SimpleDateFormat

import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction
import org.apache.flink.streaming.api.scala.{StreamExecutionEnvironment, _}
import org.apache.flink.util.Collector

case class Event1(id: Long, eventTime: Long)

case class Event2(id: Long, eventTime: Long)

case class Result(id: Long, warnings: String)

// 双流处理
object StreamCo {
  val simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
  val txErrorOutputTag = new OutputTag[Result]("txErrorOutputTag")


  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    val event1Stream = env.socketTextStream("127.0.0.1", 9999)
      .map(data => {
        val dataArray = data.split(",")
        Event1(dataArray(0).trim.toLong, simpleDateFormat.parse(dataArray(1).trim).getTime)
      }).assignAscendingTimestamps(_.eventTime * 1000L)
      .keyBy(_.id)

    val event2Stream = env.socketTextStream("127.0.0.1", 9998)
      .map(data => {
        val dataArray = data.split(",")
        Event2(dataArray(0).trim.toLong, simpleDateFormat.parse(dataArray(1).trim).getTime)
      }).assignAscendingTimestamps(_.eventTime * 1000L)
      .keyBy(_.id)

    val coStream = event1Stream.connect(event2Stream)
      .process(new CoTestProcess())

    //    union 必须是同一条类型的流
    //    val unionStream = event1Stream.union(event2Stream)
    //    unionStream.print()

    coStream.print("ok")
    coStream.getSideOutput(txErrorOutputTag).print("txError")

    env.execute("union test")
  }

  //共享状态
  class CoTestProcess() extends KeyedCoProcessFunction[Long,Event1, Event2, Result] {
    lazy val event1State: ValueState[Boolean]
    = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("event1-state", classOf[Boolean]))

    lazy val event2State: ValueState[Boolean]
    = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("event2-state", classOf[Boolean]))


    override def processElement1(value: Event1, ctx: KeyedCoProcessFunction[Long, Event1, Event2, Result]#Context, out: Collector[Result]): Unit = {
      if (event2State.value()) {
        event2State.clear()
        out.collect(Result(value.id, "ok"))
      } else {
        event1State.update(true)
        //等待一分钟
        ctx.timerService().registerEventTimeTimer(value.eventTime + 1000L * 60)
      }
    }

    override def processElement2(value: Event2, ctx: KeyedCoProcessFunction[Long, Event1, Event2, Result]#Context, out: Collector[Result]): Unit = {
      if (event1State.value()) {
        event1State.clear()
        out.collect(Result(value.id, "ok"))
      } else {
        event2State.update(true)
        ctx.timerService().registerEventTimeTimer(value.eventTime + 1000L * 60)
      }
    }

    override def onTimer(timestamp: Long, ctx: KeyedCoProcessFunction[Long, Event1, Event2, Result]#OnTimerContext, out: Collector[Result]): Unit = {
      if(event1State.value()){
        ctx.output(txErrorOutputTag,Result(ctx.getCurrentKey,s"no event2,timestamp:$timestamp"))
        event1State.clear()
      }else if(event2State.value()){
        ctx.output(txErrorOutputTag,Result(ctx.getCurrentKey,s"no event1,timestamp:$timestamp"))
        event2State.clear()
      }
    }
  }


}
