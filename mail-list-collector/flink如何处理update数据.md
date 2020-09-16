# Flink如何处理update数据

> 《flink实时统计GMV,如果订单金额下午变了该怎么处理》 http://apache-flink.147419.n8.nabble.com/flink-GMV-td6960.html

## 原文问题描述
- 实时统计每天的GMV,但是订单金额是会修改的。
- 订单存储在mysql,通过binlog解析工具实时同步到kafka.然后从kafka实时统计当日订单总额。
- 假设订单009 上午10点生成，金额为1000. 生成一条json数据到kafka ,GMV实时统计为1000。
- 然后下午15点，009订单金额被修改为500。数据生成json也会进入kafka. 这时如果不减去上午已经统计的金额。那么总金额就是错的。

根据 update /delete 要写这个减去的逻辑。按日去重是不行了，因为是增量处理不能，上午的数据已经被处理了不能再获取了。

## 解决思路
1. 首先版本是1.11+， 可以直接用binlog
format，这样数据的修改其实会自动对应到update_before和update_after的数据，这样Flink
  内部的算子都可以处理好这种数据，包括聚合算子。比如你是select sum(xxx) from T group by
yyy这种，那这个sum指标会自动做好这件事。
2. 如果不用binlog模式，只是取最新的数据来做聚合计算，也可以用去重算子[1] 将append数据流转成retract数据流，这样下游再用同样的
  聚合逻辑，效果也是一样的。

[1]https://ci.apache.org/projects/flink/flink-docs-master/dev/table/sql/queries.html#deduplication

只要source端产生了changelog数据，后面的算子是可以自动处理update消息的，简单理解，你可以认为
1. append / update_after 消息会累加到聚合指标上
2. delete / update_before 消息会从聚合指标上进行retract