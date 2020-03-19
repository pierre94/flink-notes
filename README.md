- [flink-notes](#flink-notes)
  * [一、官方资料索引](#--------)
  * [二、技术博客索引](#--------)
  * [三、优秀文章索引](#--------)
    + [1、基础入门](#1-----)
    + [2、进阶](#2---)
    + [3、官方实用文档索引](#3---------)
    + [3、比赛](#3---)
  * [四、学习书籍](#------)
  * [五、相关开源项目](#--------)
    + [1、flinkx:基于flink实现的分布式数据同步工具](#1-flinkx---flink------------)
    + [2、flinkk8soperator](#2-flinkk8soperator)
    + [3、Alink](#3-alink)
  * [六、源码实践](#------)
    + [1、入门案例](#1-----)
    + [2、电商行为分析系列](#2---------)
    + [3、misc](#3-misc)
  * [七、企业案例](#------)
  * [八、flink-weekly学习摘要](#--flink-weekly----)
  * [九、MISC](#--misc)

<small><i><a href='http://ecotrust-canada.github.io/markdown-toc/'>Table of contents generated with markdown-toc</a></i></small>

# flink-notes
flink的一些学习笔记，将在github上持续更新
> [https://github.com/pierre94/flink-notes](https://github.com/pierre94/flink-notes)


## 一、官方资料索引

- [Flink中文官方文档](https://flink.apache.org/zh/)   | 综合类，中文，不全
   
- [Flink官方博客](https://flink.apache.org/blog/)  |  读文档永远是学习的首选方向

- [Flink中文社区ververica](https://ververica.cn/)   | Flink中文社区,大量学习资料和视频

- [Apache Flink 中文用户邮件列表](http://apache-flink.147419.n8.nabble.com/)

- [Flink社区包 https://flink-packages.org/](https://flink-packages.org/)
- [Flink-FLIP](https://cwiki.apache.org/confluence/display/FLINK) | Flink Improvement Proposals


## 二、技术博客索引

 

[JarkWu的博客](http://wuchong.me/)   |  Flink committer,主要是Flink SQL方向
  

[VinoYang的博客](https://blog.csdn.net/yanghua_kobe/article/category/6170573)   | Flink项目活跃贡献者，主要是flink早期的一些文章

[flink-china:flink-training-course](https://github.com/flink-china/flink-training-course)  | 本系列课程由 Apache Flink Community China 官方出品。主要是钉钉群里的直播视频和PPT

[realguoshuai的Hadoop生态圈中常用大数据组件文档](https://github.com/realguoshuai/hadoop_study)  | 包含Flink Solr Sparksql ES Scala Kafka Hbase/phoenix Redis Kerberos (项目包含hadoop思维导图 印象笔记 Scala版本简单demo 常用工具类 去敏后的train code,适合入门学习

[zhisheng17的flink博客](https://github.com/zhisheng17/flink-learning)  | 含 Flink 入门、概念、原理、实战、性能调优、源码解析等内容，适合入门学习。 不过一些内容可能要到他付费的知识星球才能看到。

[heibaiying/BigData-Notes](https://github.com/heibaiying/BigData-Notes)  | 包含Hadoop\Hive\Spark\Storm\Flink\Hbase\Kafka\Zookeeper\Flume\Sqoop\Azkaban\Scala等模块的文章

[Flink Forward Asia 2019](https://ververica.cn/developers/flink-forward-asia-2019/) | 资料位于Flink中文社区ververica.cn

[Flink Forward China 2018](https://github.com/flink-china/flink-forward-china-2018)  | 资料位于github

[youtube: Flink Forward San Francisco 2015-2017](https://www.youtube.com/channel/UCY8_lgiZLZErZPF47a2hXMA) | 需要翻```wall```上外网

补充中……



## 三、优秀文章索引

### 1、基础入门

[flink web 上传的jar包在哪里？](http://www.54tianzhisheng.cn/2019/03/13/flink-job-jars/ )

> ${flink-web-url}/#/job-manager/config 的```web.tmpdir```配置相关 

[flink如何处理依赖](https://ci.apache.org/projects/flink/flink-docs-release-1.9/dev/projectsetup/dependencies.html)

[Apache Flink 类型和序列化机制简介](https://ververica.cn/developers/introduction-to-type-and-serialization-mechanisms/)

[聊聊flink的ParameterTool](https://juejin.im/post/5c660f06e51d4501302e307c) | flink程序使用启动参数时会用到




补充中……

### 2、进阶

[Flink on yarn 官方文档](https://ci.apache.org/projects/flink/flink-docs-master/zh/ops/deployment/yarn_setup.html#start-flink-session)

> Flink提供了两种在yarn上运行的模式，分别为Session-Cluster和Per-Job-Cluster模式，本文分析两种模式及启动流程。

[Flink metric 官方文档](https://ci.apache.org/projects/flink/flink-docs-release-1.9/monitoring/metrics.html)

> flink内部收集指标: Counter 计数器、Gauge 一个值、Meter 统计吞吐量，单位时间内发生的次数、Histogram 统计数据分布，Max Min Mean

[Flink WaterMark分析](https://juejin.im/post/5bf95810e51d452d705fef33)

[fanrui:在 Flink 算子中使用多线程如何保证不丢数据？](https://mp.weixin.qq.com/s/YWKw8jhTdaDoppkcoYYf7g)
>基于CyclicBarrier实现。对于非 CPU 密集型的任务可以使用该方案来提高 CPU 利用率,有实现demo源码

[Flink 流式计算在节省资源方面的简单分析](https://ververica.cn/developers/flink-saves-resources-analysis/)
> Apache Flink 在小米的发展;含与spark的模型对比。

[Flink Checkpoint 问题排查实用指南](https://ververica.cn/developers/flick-checkpoint-troubleshooting-practical-guide/)
> 在实际情况中，我们可能会遇到 Checkpoint 失败，或者 Checkpoint 慢的情况，本文会统一聊一聊 Flink 中 Checkpoint 异常的情况（包括失败和慢），以及可能的原因和排查思路。


### 3、官方实用文档索引
[flink prometheus](https://ci.apache.org/projects/flink/flink-docs-release-1.9/monitoring/metrics.html#prometheus-orgapacheflinkmetricsprometheusprometheusreporter)

[Flink HA & 恢复策略 &并发度 --待补充]()

[Flink有什么办法解决由于Key partition造成的数据倾斜问题？ ---待补充]()

### 3、比赛

[Apache Flink极客挑战赛——Flink TPC-DS性能优化](https://tianchi.aliyun.com/competition/entrance/231742/introduction?spm=5176.12281949.1003.4.41af2448eCyOzJ)

[Apache Flink极客挑战赛——垃圾图片分类](https://tianchi.aliyun.com/competition/entrance/231743/introduction?spm=5176.12281949.1003.26.41af2448eCyOzJ)




补充中……


## 四、学习书籍
[pdf:《追源索骥：透过源码看懂Flink核心框架的执行流程.pdf》](./books/追源索骥：透过源码看懂Flink核心框架的执行流程.pdf) | from github

[pdf:《Introduction to Apache Flink》](./books/Introduction_to_Apache_Flink.pdf) | 英文版,100多页的小册子

[pdf:《Flink基础教程》(《Introduction to Apache Flink》中文版)](./books/Flink基础教程.pdf) | 上面那本书《Introduction to Apache Flink》的中文版

[微信读书:《深入理解Flink:实时大数据处理实践》](https://weread.qq.com/web/reader/b19329f071845564b199467) | 包含一部分FlinkML

[微信读书:《Flink原理、实战与性能优化》](https://weread.qq.com/web/reader/56332f30718247bd563ee2f)
> 阅读中,总体比较全面……  [github源码地址 https://github.com/zlb1028/learning-flink](https://github.com/zlb1028/learning-flink)

[flink中文社区出品: flink知识图谱](./books/Apache-Flink-Stateful-Computations-over-Data-Streams.pdf) | 脑图形式: 一图在手,学好flink不愁! (此图待深入学习)

[Apache Flink特刊（正式电子版）2019](./books/Apache%20Flink特刊（正式电子版）2019.pdf)

[为什么学习flink](./books/flink-china-为什么学习flink.pdf)

[flink关于checkpoint的疑虑](./books/flink%20关于checkpoint%20疑虑%20.pdf)

补充中……

## 五、相关开源项目

### 1、flinkx:基于flink实现的分布式数据同步工具
[flinkx](https://github.com/DTStack/flinkx)


> 自己的一篇学习总结[《数据同步工具Flinkx的研究与实践》](https://blog.csdn.net/u013128262/article/details/103510510)

### 2、flinkk8soperator
[flinkk8soperator github地址](https://github.com/lyft/flinkk8soperator)  | 待测试


### 3、Alink
[Alink github地址](https://github.com/alibaba/Alink)


补充中……

## 六、源码实践

### 1、入门案例

- WordCount:统计单词出现的频率


### 2、电商行为分析系列
> 可以参考尚硅谷的这篇[文档](./books/电商用户行为数据分析.pdf)自由发挥

- 状态编程:[订单超时告警](./detail/state-program.md)

- Connect:[Flink双流处理:实时对账实现](./detail/Flink双流处理-实时对账实现.pdf)


### 3、FlinkSQL

- [wuchong: Flink 1.9 实战：使用 SQL 读取 Kafka 并写入 MySQL](http://wuchong.me/blog/2019/09/02/flink-sql-1-9-read-from-kafka-write-into-mysql/)
- [wuchong: Demo：基于 Flink SQL 构建流式应用](http://wuchong.me/blog/2020/02/25/demo-building-real-time-application-with-flink-sql/#more) | Kafka, MySQL, Elasticsearch, Kibana, docker 使用 Flink SQL 构建一个电商用户行为的实时分析应用

补充中……

## 七、企业案例
- Tencent
- 阿里巴巴
- B站
- ByteDance(字节跳动)
- Criteo
- eBay
- DellEMC
- DiDi(滴滴)
- Intel

……

内容过多，汇聚到独立页面。详见 [这里](./企业案例.md)！

## 八、flink-weekly学习摘要
> 摘取***Flink Weekly***中自己感兴趣的一些部分

[详情](flink-weekly.md)

## 九、MISC
