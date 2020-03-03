> Flink状态编程-学习小结

# 一、基础概念

在Flink架构体系中，有状态计算可以说是Flink非常重要的特性之一。

![image.png](https://ask.qcloudimg.com/draft/1060300/zyte1cgif.png)

有状态计算是指:

> 在程序计算过程中，在Flink程序内部存储计算产生的中间结果，并提供给后续Function或算子计算结果使用。（如下图所示）

![image.png](https://ask.qcloudimg.com/draft/1060300/90v9l6st52.png)

无状态计算实现的复杂度相对较低，实现起来较容易，但是无法完成提到的比较复杂的业务场景:

- CEP（复杂事件处理）:获取符合某一特定事件规则的事件，状态计算就可以将接入的事件进行存储，然后等待符合规则的事件触发
- 最大值、均值等聚合指标（如pv,uv）: 需要利用状态来维护当前计算过程中产生的结果，例如事件的总数、总和以及最大，最小值等
- 机器学习场景，维护当前版本模型使用的参数
- 其他需要使用历史数据的计算

# 二、Flink状态编程

## 1、支持的状态类型

Flink根据数据集是否根据Key进行分区，将状态分为Keyed State和Operator State（Non-keyed State）两种类型。

其中Keyed State是Operator State的特例，可以通过Key Groups进行管理，主要用于当算子并行度发生变化时，自动重新分布Keyed Sate数据

同时在Flink中Keyed State和Operator State均具有两种形式:

- 一种为托管状态（ManagedState）形式，由Flink Runtime中控制和管理状态数据，并将状态数据转换成为内存Hashtables或RocksDB的对象存储，然后将这些状态数据通过内部的接口持久化到Checkpoints中，任务异常时可以通过这些状态数据恢复任务。
- 另外一种是原生状态（Raw State）形式，由算子自己管理数据结构，当触发Checkpoint过程中，Flink并不知道状态数据内部的数据结构，只是将数据转换成bytes数据存储在Checkpoints中，当从Checkpoints恢复任务时，算子自己再反序列化出状态的数据结构。

在Flink中推荐用户使用Managed State管理状态数据，主要原因是Managed State能够更好地支持状态数据的重平衡以及更加完善的内存管理。

## 2、Managed Keyed State

### 六种类型

Managed Keyed State 又分为如下六种类型:

![image.png](https://ask.qcloudimg.com/draft/1060300/xc1z053n6y.png)

> FoldingState已经被标注为deprecated

### 基本API

在Flink中需要通过创建StateDescriptor来获取相应State的操作类。如下方代码，构建一个ValueState:

```scala
lazy val isPayedState: ValueState[Boolean] = getRuntimeContext.getState(new ValueStateDescriptor[Boolean]("is-payed-state", classOf[Boolean]))
```

其中对ValueState可以增删改查:

```scala
# 获取状态值
val isPayed = isPayedState.value()

# 更新状态值
isPayedState.update(true)

# 释放状态值
isPayedState.clear()
```

### 状态的生命周期

对于任何类型Keyed State都可以设定状态的生命周期（TTL），以确保能够在规定时间内及时地清理状态数据。

实现方法:

1、生成StateTtlConfig配置

2、将StateTtlConfig配置传入StateDescriptor中的enableTimeToLive方法中即可

```scala
import org.apache.flink.api.common.state.StateTtlConfig
import org.apache.flink.api.common.state.ValueStateDescriptor
import org.apache.flink.api.common.time.Time

val ttlConfig = StateTtlConfig
    .newBuilder(Time.seconds(1))
    .setUpdateType(StateTtlConfig.UpdateType.OnCreateAndWrite)
    .setStateVisibility(StateTtlConfig.StateVisibility.NeverReturnExpired)
    .build
    
val stateDescriptor = new ValueStateDescriptor[String]("text state", classOf[String])
stateDescriptor.enableTimeToLive(ttlConfig)
```

StateTtlConfig的详细配置见: [https://ci.apache.org/projects/flink/flink-docs-release-1.10/dev/stream/state/state.html#state-time-to-live-ttl](https://ci.apache.org/projects/flink/flink-docs-release-1.10/dev/stream/state/state.html#state-time-to-live-ttl)

## 3、Managed Operator State

Operator State是一种non-keyed state，与并行的操作算子实例相关联，例如在KafkaConnector中，每个Kafka消费端算子实例都对应到Kafka的一个分区中，维护Topic分区和Offsets偏移量作为算子的Operator State。在Flink中可以实现`Checkpointed-Function`或者`ListCheckpointed<T extends Serializable>`两个接口来定义操作Managed Operator State的函数。

(待补充……)

# 三、案例:订单延迟告警统计

## 1、需求描述

> 需求与数据来自《大数据技术之电商用户行为分析》

在电商平台中，最终创造收入和利润的是用户下单购买的环节；更具体一点，是用户真正完成支付动作的时候。用户下单的行为可以表明用户对商品的需求，但在现实中，并不是每次下单都会被用户立刻支付。当拖延一段时间后，用户支付的意愿会降低。

所以为了让用户更有紧迫感从而提高支付转化率，同时也为了防范订单支付环节的安全风险，电商网站往往会对订单状态进行监控，设置一个失效时间（比如 15 分钟），如果下单后一段时间仍未支付，订单就会被取消。

此时需要给用户发送一个信息提醒用户，提高支付转换率！

## 2、需求分析

本需求可以使用CEP来实现，但这里推荐使用process function原生的状态编程。

问题可以简化成: 在pay事件超时未发生的情况下,输出超时报警信息。

一个简单的思路是:

1. 在订单的 create 事件到来后注册定时器，15分钟后触发；
2. 用一个布尔类型的 Value 状态来作为标识位，表明 pay 事件是否发生过。
3. 如果 pay 事件已经发生，状态被置为true，那么就不再需要做什么操作；
4. 而如果 pay 事件一直没来，状态一直为false，到定时器触发时，就应该输出超时报警信息。

## 3、数据与模型

示例数据:

```
34729,create,,1558430842
34730,create,,1558430843
34729,pay,sd76f87d6,1558430844
34730,modify,3hu3k2432,1558430845
34731,create,,1558430846
34731,pay,35jue34we,1558430849
34732,create,,1558430852
34733,create,,1558430855
34734,create,,1558430859
34734,create,,1558431000
34733,pay,,1558431000             
34732,pay,,1558449999   
```

我们可以得到Flink的输入与输出类

```scala
// 定义输入订单事件的样例类
case class OrderEvent(orderId: Long, eventType: String, txId: String, eventTime: Long)

// 定义输出结果样例类
case class OrderResult(orderId: Long, resultMsg: String)
```

## 4、详细实现

```scala
import org.apache.flink.api.common.state.{ValueState, ValueStateDescriptor}
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.util.Collector

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
```