
### [hadoop中HBase、Flume、Hive、Zookeeper简要概述](https://blog.csdn.net/suibianshen2012/article/details/48274101)
```
（1）HBase是一个分布式的、面向列的开源数据库，该技术来源于 Fay Chang 所撰写的Google论文“Bigtable：一个结构化数据的分布式存储系统”。就像Bigtable利用了Google文件系统（File System）所提供的分布式数据存储一样，HBase在Hadoop之上提供了类似于Bigtable的能力。HBase是Apache的Hadoop项目的子项目。HBase不同于一般的关系数据库，它是一个适合于非结构化数据存储的数据库。另一个不同的是HBase基于列的而不是基于行的模式。

    HBase – Hadoop Database，是一个高可靠性、高性能、面向列、可伸缩的分布式存储系统，利用HBase技术可在廉价PC Server上搭建起大规模结构化存储集群

（2）Flume是Cloudera提供的日志收集系统，Flume支持在日志系统中定制各类数据发送方，用于收集数据;同时，Flume提供对数据进行简单处理，并写到各种storage。Flume是一个分布式、可靠、和高可用的海量日志采集、聚合和传输的系统。

（3）hive是基于Hadoop的一个数据仓库工具，可以将结构化的数据文件映射为一张数据库表，并提供完整的sql查询功能，可以将 sql语句转换为MapReduce任务进行运行。 其优点是学习成本低，可以通过类SQL语句快速实现简单的MapReduce统计，不必开发专门的MapReduce应用，十分适合数据仓库的统计分析。

（4）zookeeper是Hadoop的正式子项目，它是一个针对大型分布式系统的可靠协调系统，提供的功能包括：配置维护、名字服务、分布式同步、组服务等。ZooKeeper的目标就是封装好复杂易出错的关键服务，将简单易用的接口和性能高效、功能稳定的系统提供给用户。
Zookeeper是Google的Chubby一个开源的实现，是高有效和可靠的协同工作系统，Zookeeper能够用来leader选举，配置信息维护等，在一个分布式的环境中，需要一个Master实例或存储一些配置信息，确保文件写入的一致性等.
ZooKeeper是一个分布式的，开放源码的分布式应用程序协调服务，包含一个简单的原语集，是Hadoop和Hbase的重要组件。 提供Java和C的接口。
--------------------- 
原文：https://blog.csdn.net/suibianshen2012/article/details/48274101 
```

### -hive的特点
```
其优点是学习成本低，可以通过类SQL语句快速实现简单的MapReduce统计，不必开发专门的MapReduce应用，十分适合数据仓库的统计分析。
```

### -[Spark是否能替代Hive](https://blog.csdn.net/ys_230014/article/details/83210800)
```
 由上表可以看出，Spark不适合作为数据仓库主要有以下几点：

1)Spark本身没有自己的存储与meta库两种最核心的东西，需要依赖HDFS和Hive的相关功能，而社区的发展趋势也没有往这边开发的意思，故Spark是作为一个计算引擎的定位长期存在的；

2)RDD， DataSet、DataFrames的三种计算形式 由于计算过程中没有一个持久化的计算元数据管理导致后续对于数据血缘的解析难度过大，无法满足数据仓库调度对于数据体系依赖分析及元数据管理相关要求，故不能作为数据仓库的主要使用方式

3)SparkSql是最有潜力成为数据仓库的主要形式，但目前来说仍然是以Hive meta库作为元数据管理 hdfs作为数据存储，由于本身的sql解析器不如Hive，一般情况下是用Hive的sql解析器来替换本身的解析器。本质来说SparkSql只是作为hive的计算速度强化版使用

4)在cpu密集任务及复杂计算任务上，它的性能及稳定性远远比不上Hive

5)Spark在运行过程中经常会出现内存错误
--------------------- 
原文：https://blog.csdn.net/ys_230014/article/details/83210800 
```
