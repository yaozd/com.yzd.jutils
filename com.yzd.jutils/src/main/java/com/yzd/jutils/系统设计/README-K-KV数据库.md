## key/value数据库

### Pika
- [https://github.com/Qihoo360/pika](https://github.com/Qihoo360/pika)
- 性能
    - [https://github.com/Qihoo360/pika](https://github.com/Qihoo360/pika)
    - [https://gitee.com/baotiao/pika](https://gitee.com/baotiao/pika)
    - [pika 性能压不上去【sharding 模式】](https://github.com/Qihoo360/pika/issues/985)
- 选型参考
    - [高性能kv存储之Redis、Redis Cluster、Pika：如何应对4000亿的日访问量？](https://www.cnblogs.com/ExMan/p/10691325.html)
    - [TerarkDB 能把 Pika 顶到多高的性能](https://cloud.tencent.com/developer/news/366922)
- 关键词解释
    - TerarkDB
        ```
      TerarkDB 是 Terark 修改版的 RocksDB，包括了完全开源的 Terark RocksDB 和 部分依赖私有库的 Terark-Zip-RocksDB。
      在兼容 RocksDB API 的同时，TerarkDB 使用了独创的可检索压缩算法，替代了 RocksDB 原有的数据压缩与索引算法
      ```


### TiKV
- [https://github.com/tikv/](https://github.com/tikv/)
- [TiKV 在饿了么的大规模应用实践](https://blog.csdn.net/weixin_34258078/article/details/88880908)
    - TiKV在饿了么的KV数据库选型中突出，对比的其他组件为（MySQL、Redis、Mongo、Cassandra ）
    - 技术要求
        ```
      大数据量，可以存储至少数十 TB 级别的数据。
      高性能，在满足高 QPS 的同时，保证比较低的延时。
      高可靠，数据被可靠的持久化存储，少量机器的损坏不会导致数据的丢失。
      高可用，作为在线服务的底层依赖存储，要有非常完善的高可用性能力，外卖服务不同于电子商务，对实时性要求非常高，对系统的可用性的要求则是更高的。
      易运维，可以在不停服的基础上进行数据迁移，和集群扩容
      ```
    - 性能
        ```
      业务高峰期时，最繁忙的一个集群，写入 QPS 近 5w，读取 QPS 近 10w
      ```
- 部署
    - [TiDB 整体架构主要概念](https://docs.pingcap.com/zh/tidb/v3.0/architecture)
    - [https://gitee.com/mirrors/TiKV](https://gitee.com/mirrors/TiKV)
    - [https://tikv.org/docs/4.0/tasks/deploy/docker/](https://tikv.org/docs/4.0/tasks/deploy/docker/)
    - [https://tikv.org/docs/4.0/tasks/deploy/binary/](https://tikv.org/docs/4.0/tasks/deploy/binary/)
- 选型参考
    - [TiDB 深度实践之旅--真实“踩坑”经历](https://www.bbsmax.com/A/gVdnDamEJW/)
- java client
    - [https://github.com/tikv/client-java/](https://github.com/tikv/client-java/)

- Tikv 兼容Redis协议
    - [https://github.com/distributedio/titan](https://github.com/distributedio/titan)    
           - [Benchmarks of Titan](https://github.com/distributedio/titan/blob/master/docs/benchmark/benchmark.md)
    - [https://github.com/eleme/tedis](https://github.com/eleme/tedis)    
    - [https://github.com/yongman/tidis/wiki/Tidis-base-benchmark](https://github.com/yongman/tidis/wiki/Tidis-base-benchmark)
    - 原理   
        - [使用 TiKV 构建分布式类 Redis 服务](https://www.jianshu.com/p/b4dee8372d8d)
            ```
            
            ```    



### cassandra
- 参考
    - [为什么选择Cassandra](https://zhuanlan.zhihu.com/p/78255146)
    - [国内哪些互联网公司使用了 Cassandra 数据库？](https://www.zhihu.com/question/19592244) 重点看评论byArvin
- 缺点
    ```
  1. 读的性能太慢无中心的设计，造成读数据时通过逆熵做计算，性能损耗很大，甚至会严重影响服务器运作。
  2. 数据同步太慢（最终一致性延迟可能非常大）由于无中心设计，要靠各节点传递信息。
     相互发通知告知状态，如果副本集有多份，其中又出现节点有宕机的情况，那么做到数据的一致性，延迟可能非常大，效率也很低的。
  3. 用插入和更新代替查询，缺乏灵活性，所有查询都要求提前定义好。
  链接：https://www.zhihu.com/question/19592244/answer/21430967
  ```
- 优点：使用场景
    ```
  作者：樱木花
  链接：https://www.zhihu.com/question/19592244/answer/558774017
  Cassandra解决的痛点是是什么，是解决海量数据长期存储问题【动不动就分库、分表、搞拆迁的那种系统】，
  【天天搞拆迁、路由、合并查询的码农真的不辛苦烦躁？】对了cassandra就是解决这种问题。其次还解决了容灾和部分节点故障上的问题，
  这两个都是【高可用核心系统】运维总监很头疼的问题， 做的好了，系统容灾基本不需要太费钱了、效果还好，也不要战战兢兢的做容灾切换。
  cassandra 适合解决什么样业务系统，就是【写的规模总量要大，又不能重度关系型数据系统】，网络游戏、电商、
  社交系统等轻度关系型系统实际上是很适合的】类型的海量数据系统。
  电商平台其实就是典型的【轻度关系型数据系统】，逻辑不算复杂，重要的是解决海量的订单问题，
  这个其实正是cassandra 的强项，当然，在事物方面需要改造，用反向更新等编程手段【冲红】替代“rollback“SQL预计，软件设计上有些不同。
  我国现状是存在一批动辄开口闭口提淘宝、京东、腾讯的数据量的【项目经理、研发经理】，个个都像要搞航天似的牛逼哥，【喉咙比本领大的领导】，
  动不动就囔囔分库分表，所以MYSQL的分库分表数量远远多于做严谨技术选型的人。除了这三-四家，剩下的99.99%公司哪里需要这么做。
  甚至用RDBMS用好都能解决问题，不深入研究是码农和农夫的是通病。
  ```
- 

### 未知名词
- 逆熵做计算
    ```
  cassandra无中心的设计，造成读数据时通过逆熵做计算，性能损耗很大
  ```
- 在事物方面需要改造，用反向更新等编程手段【冲红】替代“rollback“SQL预计，软件设计上有些不同
    - [发票冲红](https://baike.baidu.com/item/%E5%8F%91%E7%A5%A8%E5%86%B2%E7%BA%A2/7462995)