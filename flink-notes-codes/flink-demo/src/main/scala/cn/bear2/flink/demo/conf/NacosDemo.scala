package cn.bear2.flink.demo.conf


import java.util.Properties
import java.util.concurrent.Executor

import com.alibaba.nacos.api.{NacosFactory, PropertyKeyConst}
import com.alibaba.nacos.api.config.listener.Listener
import com.alibaba.nacos.api.exception.NacosException
import org.apache.flink.api.common.functions.RichFilterFunction
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.immutable.List


// 基于nacos实现业务逻辑配置热更.热更新内容


object NacosDemo {
  val LOG: Logger = LoggerFactory.getLogger(this.getClass)

  @throws[NacosException]
  @throws[InterruptedException]
  def main(args: Array[String]): Unit = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    env.setParallelism(1)

    LOG.info("starting……")

    val stream = env.socketTextStream("127.0.0.1", 9999)
      .filter(new MyFilter)
    stream.print()

    env.execute("test")
  }
}

// 配置热更的函数必须是RichFunction
class MyFilter() extends RichFilterFunction[String] {
  var nameList: List[String] = List()

  override def open(parameters: Configuration): Unit = {
    val serverAddr = "127.0.0.1:8080"
    val dataId = "YOUR_DataId"
    val group = "DEFAULT_GROUP"
    val properties = new Properties()
    properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr)

    val configService = NacosFactory.createConfigService(properties)
    nameList = configService.getConfig(dataId, group, 5000).split(",").toList
    configService.addListener(dataId, group, new Listener() {
      override def getExecutor: Executor = {
        null
      }

      override def receiveConfigInfo(s: String): Unit = {
        nameList = s.split(",").toList
      }
    })
  }

  override def close(): Unit = super.close()

  override def filter(value: String): Boolean = {
    nameList.contains(value.split(",")(0))
  }

}
