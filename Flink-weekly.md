### Flink Weekly | 每周社区动态更新 - 2020/03/01 
- 有两位用户都碰到了Flink 1.10 Hive集成的kerberos认证异常，问题还在排查中。
    http://apache-flink.147419.n8.nabble.com/Flink-1-10-hive-kerberos-td1751.html

- 猫猫提出了flink-jdbc-driver的使用问题，引出了目前batch不支持UpsertTableSink，也就是不支持目前的JDBCUpsertSink和HBaseUpsertSink，目前正在支持中。
    http://apache-flink.147419.n8.nabble.com/flink-jdbc-driver-mysql-flink1-10-0-td1763.html

- 有两位用户都遇到了Class冲突的问题，这是因为Flink
  1.10把客户端的ClassLoader解析顺序调整为了Child优先，这就导致用户的Jar包不能包含Flink框架的classes，比如常见的Calcite、Flink-Planner依赖、Hive依赖等等。用户需要把有冲突classes的jar放到flink-home/lib下，或者调整策略为Parent优先。
    http://apache-flink-mailing-list-archive.1008284.n3.nabble.com/Flink-1-10-exception-Unable-to-instantiate-java-compiler-td38221.html
