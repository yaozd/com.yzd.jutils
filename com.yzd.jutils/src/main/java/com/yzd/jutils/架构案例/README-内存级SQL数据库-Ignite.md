## 内存数据组织 Apache Ignite
-[内存数据组织 Apache Ignite](https://www.oschina.net/p/apache-ignite)

## IGNITE应用实践
- [IGNITE应用实践](https://www.jianshu.com/p/2bef1ca12211)
```
一、 应用场景
数据云及云计算数据缓存层
背景:业务数据库越来越大，全量抽取更新及关联查询维表对业务库Mysql造成巨大压力，较长sql关联查询耗时20分钟以上。
结论：重新改变原始的数据加工逻辑后，引入ignite框架完成分钟级数据同步。单任务提高效率，由每小时同步一次数据，提升至15分钟，性能提高4倍。并且关联计算由ignite完成，减少了业务端Mysql的压力。
在之前的架构中耗时较长的部分为：
T1:Mysql关联查询，并对业务库Mysql造成较大压力，随着数据量的增加，较长关联查询已经到达20分钟以上。
文件读写，利用spark将csv转orc文件并写入hive表。
数据分发，由hive写入hdfs文件，通过BulkLoad加载到hbase，后续使用phoenix做业务关联查询。
T2:优化后的ignite架构：(mysql+kafka+ignite)
Mysql关联查询移到Ignite中。Ignite的内存关联查询部分优于Mysql数据库。
取消数据的文件读写，使用Mysql binlog接入kafka实时更新ignite表数据，规避csv转orc过程。
同时将ignite数据分发到hive和phoenix做业务关联查询使用。

```