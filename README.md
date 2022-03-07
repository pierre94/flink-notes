


# flink-notes
flink的一些学习材料，将在github上持续更新
> [https://github.com/pierre94/flink-notes](https://github.com/pierre94/flink-notes)


## 一、官方资料索引

- [Flink中文官方文档](https://flink.apache.org/zh/)   | 综合类，中文，不太全
- [Flink官方博客](https://flink.apache.org/blog/)  |  读官方博客与文档是学习的首选方向
- [Flink中文社区ververica](https://ververica.cn/)   | Flink中文社区,大量学习资料和视频
- [Apache Flink 中文用户邮件列表](http://apache-flink.147419.n8.nabble.com/)
- [Apache Flink 用户邮件列表](http://apache-flink-user-mailing-list-archive.2336050.n4.nabble.com/) | 英文，更活跃
- [Flink社区包 https://flink-packages.org/](https://flink-packages.org/)
- [Flink-FLIP](https://cwiki.apache.org/confluence/display/FLINK) | Flink Improvement Proposals


## 二、技术博客索引

 

- [JarkWu的博客](http://wuchong.me/)   |  Flink committer

- [jrwang的flink源码解读系列](https://blog.jrwang.me/tags/flink/ ) | 源码解读质量不错

- [VinoYang的博客](https://blog.csdn.net/yanghua_kobe/article/category/6170573)   | Flink项目活跃贡献者，主要是flink早期的一些文章

- [flink-china:flink-training-course](https://github.com/flink-china/flink-training-course)  | 本系列课程由 Apache Flink Community China 官方出品。主要是钉钉群里的直播视频和PPT

- [realguoshuai的Hadoop生态圈中常用大数据组件文档](https://github.com/realguoshuai/hadoop_study)  | 包含Flink Solr Sparksql ES Scala Kafka Hbase/phoenix Redis Kerberos

- [zhisheng17的flink博客](https://github.com/zhisheng17/flink-learning)  | 含 Flink 入门、概念、原理、实战、性能调优、源码解析等内容，适合入门学习。 不过一些内容可能要到他付费的知识星球才能看到。

- [heibaiying/BigData-Notes](https://github.com/heibaiying/BigData-Notes)  | 包含Hadoop\Hive\Spark\Storm\Flink\Hbase\Kafka\Zookeeper\Flume\Sqoop\Azkaban\Scala等模块的文章

- [Flink Forward Asia 2019](https://ververica.cn/developers/flink-forward-asia-2019/) | 资料位于Flink中文社区ververica.cn

- [Flink Forward China 2018](https://github.com/flink-china/flink-forward-china-2018)  | 资料位于github

- [youtube: Flink Forward San Francisco 2015-2017](https://www.youtube.com/channel/UCY8_lgiZLZErZPF47a2hXMA) | 需要翻```wall```上外网


## 三、优秀文章索引

### 1、基础入门

- [flink web 上传的jar包在哪里？](http://www.54tianzhisheng.cn/2019/03/13/flink-job-jars/ ) | ${flink-web-url}/#/job-manager/config 的```web.tmpdir```配置相关 

- [flink如何处理依赖](https://ci.apache.org/projects/flink/flink-docs-release-1.9/dev/projectsetup/dependencies.html)

- [Apache Flink 类型和序列化机制简介](https://ververica.cn/developers/introduction-to-type-and-serialization-mechanisms/)

- [聊聊flink的ParameterTool](https://juejin.im/post/5c660f06e51d4501302e307c) | flink程序使用启动参数时会用到


### 2、进阶

#### Flink on yarn

- [Flink on yarn 官方文档](https://ci.apache.org/projects/flink/flink-docs-master/zh/ops/deployment/yarn_setup.html#start-flink-session) | Session-Cluster和Per-Job-Cluster模式


#### Metric
- [Flink metric 官方文档](https://ci.apache.org/projects/flink/flink-docs-release-1.9/monitoring/metrics.html) | Counter\Gauge\Meter\Histogram
- [详解 Flink 指标、监控与告警](https://mp.weixin.qq.com/s/wfV1SfOKa1D9_ZF2rdO2kg) | 


#### 算子
- [fanrui:在 Flink 算子中使用多线程如何保证不丢数据？](https://mp.weixin.qq.com/s/YWKw8jhTdaDoppkcoYYf7g) | 基于CyclicBarrier实现,提高 CPU 利用率
- [Flink有什么办法解决由于Key partition造成的数据倾斜问题？ ---待补充]()
- [Flink WaterMark分析](https://juejin.im/post/5bf95810e51d452d705fef33)


#### checkpoint

- [Flink Checkpoint 问题排查实用指南](https://ververica.cn/developers/flick-checkpoint-troubleshooting-practical-guide/)
- [Flink 1.11 Unaligned Checkpoint 解析](https://mp.weixin.qq.com/s/6zUCeFGw4_AAcQzw4ugWoQ) | 反压下的checkpoint

#### OPS
- [Flink 流式计算在节省资源方面的简单分析](https://ververica.cn/developers/flink-saves-resources-analysis/) | Apache Flink 在小米的发展;含与spark的模型对比。
- [Flink HA & 恢复策略 &并发度 --待补充]()
- [Flink 中的应用部署：当前状态与新应用模式](https://mp.weixin.qq.com/s/6KYzhaskos8fk4oKeh_xcA) | Flink 1.11 引入了 Application 模式（应用模式）
- [数据处理能力相差 2.4 倍？Flink 使用 RocksDB 和 Gemini 的性能对比实验](https://mp.weixin.qq.com/s/Fu2m_HEO9sdp4asKYAkY1w)

#### CEP
- [基于 Flink 构建 CEP 引擎的挑战和实践](https://mp.weixin.qq.com/s?__biz=MzU3Mzg4OTMyNQ==&mid=2247485684&idx=1&sn=61be9ee7ca60afffb2460b743bc6294b&chksm=fd3b86b6ca4c0fa0076ed507b89cb593d4fb2f40e908d129ca0cc3abae84f1de3344fb257f58&scene=21#wechat_redirect)

#### CDC
- [Flink SQL CDC 上线！我们总结了 13 条生产实践经验](https://mp.weixin.qq.com/s/Mfn-fFegb5wzI8BIHhNGvQ) |  Flink SQL CDC 在生产环境的落地实践以及总结的实战经验
- [Flink SQL CDC 的介绍和教程](https://www.bilibili.com/video/BV1zt4y1D7kt/) | B站视频
- [flink-cdc-connectors 项目官网](https://github.com/ververica/flink-cdc-connectors) |  flink-cdc-connectors 项目官网 github
- [自定义 Format 消费 Maxwell CDC 数据](https://mp.weixin.qq.com/s/HaSi4E1Ez4jV06RWAQ2wAQ) 

#### SQL
- [Flink SQL FileSystem Connector 分区提交与自定义小文件合并策略​](https://mp.weixin.qq.com/s/4d7pEXzUtTaaKSYlf1e_Cg) 
- [Flink 1.11 SQL 使用攻略](https://mp.weixin.qq.com/s/BBRw3sR323d-jaxxONYknQ)

#### 源码分析
- [万字长文详解 Flink 中的 CopyOnWriteStateTable](https://mp.weixin.qq.com/s/q-xbKTxlw35z8R1BUWv91w)

#### misc
- [解决问题 1474 个，Flink 1.11 究竟有哪些易用性上的改善？](https://mp.weixin.qq.com/s/yuR39vqkuPPeLfq2ncBTkg)

### 3、Flink应用文章

#### ETL
- [基于 Flink 的典型 ETL 场景实现方案](https://mp.weixin.qq.com/s/l--W_GUOGXOWhGdwYqsh9A) | 美团点评的实践
- [Flink 1.11 新特性之 SQL Hive Streaming 简单示例](https://mp.weixin.qq.com/s/oovUAfp0i662oYp282-26A)
- [字节跳动基于Flink的MQ-Hive实时数据集成](https://mp.weixin.qq.com/s/SDkgYqBZrejObpJ_2bpURw)

#### 机器学习
- [微博基于 Flink 的机器学习实践](https://mp.weixin.qq.com/s/NeyF3t6PRtYG2c9OZ-ICAA)


### 4、官方实用文档索引
- [flink prometheus](https://ci.apache.org/projects/flink/flink-docs-release-1.9/monitoring/metrics.html#prometheus-orgapacheflinkmetricsprometheusprometheusreporter)



### 5、比赛

- [Apache Flink极客挑战赛——Flink TPC-DS性能优化](https://tianchi.aliyun.com/competition/entrance/231742/introduction?spm=5176.12281949.1003.4.41af2448eCyOzJ)

- [Apache Flink极客挑战赛——垃圾图片分类](https://tianchi.aliyun.com/competition/entrance/231743/introduction?spm=5176.12281949.1003.26.41af2448eCyOzJ)


## 四、学习书籍
- [pdf:《追源索骥：透过源码看懂Flink核心框架的执行流程.pdf》](./books/追源索骥：透过源码看懂Flink核心框架的执行流程.pdf) | from github

- [pdf:《Introduction to Apache Flink》](./books/Introduction_to_Apache_Flink.pdf) | 英文版,100多页的小册子

- [pdf:《Flink基础教程》(《Introduction to Apache Flink》中文版)](./books/Flink基础教程.pdf) | 上面那本书《Introduction to Apache Flink》的中文版

- [微信读书:《深入理解Flink:实时大数据处理实践》](https://weread.qq.com/web/reader/b19329f071845564b199467) | 包含一部分FlinkML

- [微信读书:《Flink原理、实战与性能优化》](https://weread.qq.com/web/reader/56332f30718247bd563ee2f) | [github源码地址 https://github.com/zlb1028/learning-flink](https://github.com/zlb1028/learning-flink)

- [flink中文社区出品: 《flink知识图谱》](./books/Apache-Flink-Stateful-Computations-over-Data-Streams.pdf) | 脑图形式: 一图在手,学好flink不愁! (此图待深入学习)

- [《Apache Flink特刊（正式电子版）2019》](./books/Apache%20Flink特刊（正式电子版）2019.pdf)

- [《为什么学习flink》](./books/flink-china-为什么学习flink.pdf)

- [《flink关于checkpoint的疑虑》](./books/flink%20关于checkpoint%20疑虑%20.pdf)

- [《flink十大技术难点实践》](./books/flink十大难题实战.pdf)


## 五、相关开源项目

### 1、flinkx:基于flink实现的分布式数据同步工具
- [flinkx](https://github.com/DTStack/flinkx) | 自己的一篇学习总结[《数据同步工具Flinkx的研究与实践》](https://blog.csdn.net/u013128262/article/details/103510510)

### 2、flinkk8soperator
- [flinkk8soperator github地址](https://github.com/lyft/flinkk8soperator)  | 待测试

### 3、Alink
- [Alink github地址](https://github.com/alibaba/Alink)

## 六、开发实践

### 1、入门案例

- WordCount:统计单词出现的频率


### 2、电商行为分析系列
> 可以参考尚硅谷的这篇[文档](./books/电商用户行为数据分析.pdf)自由发挥

- 状态编程:[订单超时告警](./detail/state-program.md)

- Connect:[Flink双流处理:实时对账实现](./detail/Flink双流处理-实时对账实现.pdf)


### 3、FlinkSQL

- [wuchong: Flink 1.9 实战：使用 SQL 读取 Kafka 并写入 MySQL](http://wuchong.me/blog/2019/09/02/flink-sql-1-9-read-from-kafka-write-into-mysql/)
- [wuchong: Demo：基于 Flink SQL 构建流式应用](http://wuchong.me/blog/2020/02/25/demo-building-real-time-application-with-flink-sql/#more) | Kafka, MySQL, Elasticsearch, Kibana, docker

### 4、Cloudera的flink教程
- [Tutorials for Flink on Cloudera](https://github.com/cloudera/flink-tutorials) 


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

- [如何从小白成长为 Apache Committer?](http://wuchong.me/blog/2019/02/12/how-to-become-apache-committer/) | 希望自己2~3年内也能成为一个Apache Committer

## 微信公众号
更多文章与内容请关注:
![](https://bear2-10045049.cos.ap-shanghai.myqcloud.com/mzmq5nkwlf.png)
