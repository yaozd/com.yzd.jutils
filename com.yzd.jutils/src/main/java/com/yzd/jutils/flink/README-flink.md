## flink
- [Flink 入门教程](https://blog.csdn.net/u010862794/article/details/90025104)
- [Flink入门这一篇就够了](https://blog.csdn.net/w15321970103/article/details/83992749)
- [一文弄懂Flink基础理论](https://blog.csdn.net/oTengYue/article/details/102689538)
- []()


## 计算链路
- [Flink 在快手实时多维分析场景的应用](https://www.sohu.com/a/403646183_120342237)
```
快手计算链路是从 DB/Binlog 以及 WebService Log 实时入到 Kafka 中，
然后接入 Flink 做实时计算，其中包括实时数仓、实时分析以及实时训练，
最后的结果存到 Druid、Kudu、HBase 或者 ClickHouse 里面；同时 Kafka 数据实时 Dump 一份到 Hadoop 集群，
然后通过 Hive、MapReduce 或者 Spark 来做离线计算；最终实时计算和离线计算的结果数据会用内部自研 BI 工具 KwaiBI 来展现出来。
```
## Flink与Druid 和 ClickHouse的对比分析
- [Flink 在快手实时多维分析场景的应用](https://www.sohu.com/a/403646183_120342237)
```
实时多维查询业务场景进行对比分析：

计算能力方面：多维查询这种业务场景需要支持 Sum、Count 和 count distinct 等能力，而 Druid 社区版本不支持 count distinct，快手内部版本支持数值类型、但不支持字符类型的 count distinct；ClickHouse 本身全都支持这些计算能力；Flink 是一个实时计算引擎，这些能力也都具备。
分组聚合能力方面：Druid 的分组聚合能力一般，ClickHouse 和 Flink 都支持较强的分组聚合能力。
查询并发方面：ClickHouse 的索引比较弱，不能支持较高的查询并发，Druid 和 Flink 都支持较高的并发度，存储系统 Kudu，它也支持强索引以及很高的并发。
查询延迟方面：Druid 和 ClickHouse 都是在查询时进行现计算，而 Flink+Kudu 方案，通过 Flink 实时计算后将指标结果直接存储到 Kudu 中，查询直接从 Kudu 中查询结果而不需要进行计算，所以查询延迟比较低。
```
## 案例
- [Flink 在快手实时多维分析场景的应用](https://www.sohu.com/a/403646183_120342237)
- [基于Flink+ClickHouse构建亿级电商实时数据分析平台（PC、移动、小程序）](https://www.jianshu.com/p/5f279e0f6e64)
- [使用Apache Kudu和Impala实现存储分层](https://blog.csdn.net/github_32521685/article/details/90374987) -Kudu与HDFS对比分析
- []()