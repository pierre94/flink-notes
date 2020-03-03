# flink-notes
flink的一些学习笔记，将在github上持续更新
> [https://github.com/pierre94/flink-notes](https://github.com/pierre94/flink-notes)

## 一、技术博客索引
1. [Flink中文官方文档](https://flink.apache.org/zh/) 
    >综合类，中文，不全

2. [Flink官方博客](https://flink.apache.org/blog/)
    > 读文档永远是学习的首选方向

3. [Flink中文社区ververica](https://ververica.cn/)
    > Flink中文社区,大量学习资料和视频

4. [JarkWu的博客](http://wuchong.me/)
    > Flink committer,主要是Flink SQL方向

5. [VinoYang的博客](https://blog.csdn.net/yanghua_kobe/article/category/6170573)
   > Flink项目活跃贡献者，主要是flink早期的一些文章

6. [flink-china:flink-training-course](https://github.com/flink-china/flink-training-course)
   > 本系列课程由 Apache Flink Community China 官方出品。主要是钉钉群里的直播视频和PPT

7. [realguoshuai的Hadoop生态圈中常用大数据组件文档](https://github.com/realguoshuai/hadoop_study)
    >  包含Flink Solr Sparksql ES Scala Kafka Hbase/phoenix Redis Kerberos (项目包含hadoop思维导图 印象笔记 Scala版本简单demo 常用工具类 去敏后的train code,适合入门学习

8. [zhisheng17的flink博客](https://github.com/zhisheng17/flink-learning)
   > 含 Flink 入门、概念、原理、实战、性能调优、源码解析等内容，适合入门学习。 不过一些内容可能要到他付费的知识星球才能看到。

9. [heibaiying/BigData-Notes](https://github.com/heibaiying/BigData-Notes)
    > 包含Hadoop\Hive\Spark\Storm\Flink\Hbase\Kafka\Zookeeper\Flume\Sqoop\Azkaban\Scala等模块的文章

10. [Flink Forward Asia 2019](https://ververica.cn/developers/flink-forward-asia-2019/)
    > 资料位于Flink中文社区ververica.cn

11. [Flink Forward China 2018](https://github.com/flink-china/flink-forward-china-2018)
    > 资料位于github

12. [youtube: Flink Forward San Francisco 2015-2017](https://www.youtube.com/channel/UCY8_lgiZLZErZPF47a2hXMA)
    > 需要翻```wall```上外网

补充中……



## 二、优秀文章索引

### 1、基础入门

[flink web 上传的jar包在哪里？](http://www.54tianzhisheng.cn/2019/03/13/flink-job-jars/ )

> ${flink-web-url}/#/job-manager/config 的```web.tmpdir```配置相关 

[flink如何处理依赖](https://ci.apache.org/projects/flink/flink-docs-release-1.9/dev/projectsetup/dependencies.html)

> Note on IntelliJ: To make the applications run within IntelliJ IDEA, the Flink dependencies need to be declared in scope compile rather than provided. Otherwise IntelliJ will not add them to the classpath and the in-IDE execution will fail with a NoClassDefFountError. To avoid having to declare the dependency scope as compile (which is not recommended, see above), the above linked Java- and Scala project templates use a trick: They add a profile that selectively activates when the application is run in IntelliJ and only then promotes the dependencies to scope compile, without affecting the packaging of the JAR files.

[Apache Flink 类型和序列化机制简介](https://ververica.cn/developers/introduction-to-type-and-serialization-mechanisms/)

> ![Flink类型分类](./img/introduction-to-type-and-serialization-mechainisms-1.png)

[聊聊flink的ParameterTool](https://juejin.im/post/5c660f06e51d4501302e307c)

> flink程序使用启动参数时会用到



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


## 三、学习书籍
[pdf:《追源索骥：透过源码看懂Flink核心框架的执行流程.pdf》](./books/追源索骥：透过源码看懂Flink核心框架的执行流程.pdf)
>from github

[pdf:《Introduction to Apache Flink》](./books/Introduction_to_Apache_Flink.pdf)
>英文版,100多页的小册子

[pdf:《Flink基础教程》(《Introduction to Apache Flink》中文版)](./books/Flink基础教程.pdf)
> 上面那本书《Introduction to Apache Flink》的中文版

[微信读书:《深入理解Flink:实时大数据处理实践》](https://weread.qq.com/web/reader/b19329f071845564b199467)
> 包含一部分FlinkML

[微信读书:《Flink原理、实战与性能优化》](https://weread.qq.com/web/reader/56332f30718247bd563ee2f)
> 阅读中,总体比较全面……  [github源码地址 https://github.com/zlb1028/learning-flink](https://github.com/zlb1028/learning-flink)

[flink中文社区出品: flink知识图谱](./books/Apache-Flink-Stateful-Computations-over-Data-Streams.pdf)
> 脑图形式: 一图在手,学好flink不愁! (此图待深入学习)

补充中……

## 四、相关开源项目

### 1、flinkx:基于flink实现的分布式数据同步工具
[flinkx](https://github.com/DTStack/flinkx)


> 自己的一篇学习总结[《数据同步工具Flinkx的研究与实践》](https://blog.csdn.net/u013128262/article/details/103510510)

### 2、flinkk8soperator
[flinkk8soperator github地址](https://github.com/lyft/flinkk8soperator)
> 待测试

### 3、Alink
[Alink github地址](https://github.com/alibaba/Alink)


补充中……

## 五、源码实践

### 1、入门案例

- WordCount:统计单词出现的频率


### 2、

[wuchong: Flink 1.9 实战：使用 SQL 读取 Kafka 并写入 MySQL](http://wuchong.me/blog/2019/09/02/flink-sql-1-9-read-from-kafka-write-into-mysql/)
> 亲测可用。 https://github.com/wuchong/flink-sql-submit 1) 能用来提交 SQL 文件的 SqlSubmit 实现。 2） 用于演示的 SQL 示例、Kafka 启动停止脚本、 一份测试数据集、Kafka 数据源生成器

补充中……

## 六、知识细点


## 七、企业案例

> 主要来自Flink中文社区的整理

### Tencent
- 基于Apache Flink的平台化构建及运维优化经验(ppt) https://files.alicdn.com/tpsservice/9bcc469feb3dcca4ea15226e70e23ed5.pdf 
- 基于Apache Flink的平台化构建及运维优化经验(视频) https://yq.aliyun.com/live/703 (大概从03:04:00处开始)
- Apache Flink 在腾讯实时计算平台中的实践(ppt)
> https://files.alicdn.com/tpsservice/e663abe7c45661ec6b4a6e8bf0d16a32.pdf
- Apache Flink 在腾讯实时计算平台中的实践(视频)https://www.bilibili.com/video/av50935702


### 阿里巴巴
1. https://ververica.com/blog/blink-flink-alibaba-search
2. https://files.alicdn.com/tpsservice/23c67b6682c7d74339af7c53fccac429.pdf 
3. https://files.alicdn.com/tpsservice/8dab3c208f8044a26937a7bd7aed3c3d.pdf 
4. https://files.alicdn.com/tpsservice/badd0c8d32c9008d95addc0a28f1eb11.pdf

### B站
- Flink在B站的应用和实践(ppt) https://files.alicdn.com/tpsser- vice/834a31a74bd5bc1e7e4fb2a8c46fdd66.pdf 
- Flink在B站的应用和实践(视频) https://www.bilibili.com/video/av52637658/

### ByteDance(字节跳动)
- https://files.alicdn.com/tpsservice/6b7686e18135389a76e2a0e476b270ec.pdf

### Criteo
- https://files.alicdn.com/tpsservice/c429c9351675f89a56000489519135a8.pdf 
- https://yq.aliyun.com/live/702 (大概从01:27:00处开始)

### eBay
- 基于 Kubernetes 的 Flink 特性及管理(ppt)https://files.alicdn.com/tpsservice/6b9bd1843b5065- cae6b329d4238a84a6.pdf
- 基于 Kubernetes 的 Flink 特性及管理(视频)https://www.bilibili.com/video/av52637658/

### DellEMC
- https://files.alicdn.com/tpsservice/8c72901db4a4bda83e33d35b8e6d0ecd.pdf

### DiDi(滴滴)
- https://files.alicdn.com/tpsservice/aca017af879a657ed0983b8f1cf4bbfd.pdf

### Intel
- Take advantage of DPCM in Flink(ppt) https://files.alicdn.com/tpsservice/440bf9d770da0f274- fa6ec69276197eb.pdf
- Take advantage of DPCM in Flink(视频) https://www.bilibili.com/video/av67458709/

### iQIYI(爱奇艺)
- https://files.alicdn.com/tpsservice/c421720fcb1c51026257cd770923844a.pdf 

### meituan(美团):
- https://files.alicdn.com/tpsservice/d855dadbdeacb1d7bae82c2780a545b5.pdf

### OPPO
- 基于Apache Flink SQL构建实时数仓(ppt) https://files.alicdn.com/tpsservice/13849590bcd8d391049adf9de12499b8.pdf 
- 基于Apache Flink SQL构建实时数仓(视频) https://www.bilibili.com/video/av50935702

### Qunar(去哪儿)
- https://files.alicdn.com/tpsservice/44558decf0f39980283107647d1e5755.pdf

### Uber
- https://files.alicdn.com/tpsservice/9bf841f251392aedcbb7cc98c5d140fa.pdf https://yq.aliyun.com/live/702

### Xiaomi(小米)
- Flink 在小米的应用与实践(ppt) https://files.alicdn.com/tpsservice/d77d3ed3f2709790f0d84f4ec279a486.pdf 
- Flink 在小米的应用与实践(视频) https://www.bilibili.com/video/av68914405/

### 袋鼠云
- https://files.alicdn.com/tpsservice/65149b8dc2643415c0a10878195d38b2.pdf 

### 趣头条:
- https://ververica.cn/corporate_practice/qtt-real-time-platform-construction-practice-based-on-flink/ 

### 快手
- https://ververica.cn/corporate_practice/kuaishou/

### 网易
- 网易云音乐基于Flink的实时计算平台实践(ppt)
> https://files.alicdn.com/tpsservice/6e80a73d98bada41275f08487a1382bd.pdf 
- 网易云音乐基于Flink的实时计算平台实践(视频) 
> https://www.bilibili.com/video/av50935702

### 携程
- https://mp.weixin.qq.com/s/H3mFfUXhJ1kp_Sp9Rr_D3Q

### 中国农业银行
- Apache Flink在中国农业银行的探索和实践(ppt) https://files.alicdn.com/tpsservice/80188db16e5f23d8ba7c04d0674d064d.pdf
- Apache Flink在中国农业银行的探索和实践(视频)https://www.bilibili.com/video/av66720978/


## 八、flink-weekly学习摘要
> 摘取***Flink Weekly***中自己感兴趣的一些部分
### Flink Weekly | 每周社区动态更新 - 2020/03/01 
- 有两位用户都碰到了Flink 1.10 Hive集成的kerberos认证异常，问题还在排查中。
    http://apache-flink.147419.n8.nabble.com/Flink-1-10-hive-kerberos-td1751.html

- 猫猫提出了flink-jdbc-driver的使用问题，引出了目前batch不支持UpsertTableSink，也就是不支持目前的JDBCUpsertSink和HBaseUpsertSink，目前正在支持中。
    http://apache-flink.147419.n8.nabble.com/flink-jdbc-driver-mysql-flink1-10-0-td1763.html

- 有两位用户都遇到了Class冲突的问题，这是因为Flink
  1.10把客户端的ClassLoader解析顺序调整为了Child优先，这就导致用户的Jar包不能包含Flink框架的classes，比如常见的Calcite、Flink-Planner依赖、Hive依赖等等。用户需要把有冲突classes的jar放到flink-home/lib下，或者调整策略为Parent优先。
    http://apache-flink-mailing-list-archive.1008284.n3.nabble.com/Flink-1-10-exception-Unable-to-instantiate-java-compiler-td38221.html
## 九、其他
- [Apache Flink 中文用户邮件列表](http://apache-flink.147419.n8.nabble.com/)

- [flink社区包 https://flink-packages.org/](https://flink-packages.org/)