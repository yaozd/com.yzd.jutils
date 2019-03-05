
> 理论参考：
- [消息队列如何保证高可用呢-RabbitMQ与Kafka](https://blog.csdn.net/qq_36081696/article/details/85611052)
    1. RabbitMQ 有三种模式：单机模式、普通集群模式、镜像集群模式
    ```
    1.单机模式
    2.普通集群模式（无高可用性）
    3.镜像集群模式（高可用性）
    ```
    2. Kafka 的高可用性
    ```
    Kafka 一个最基本的架构认识：由多个 broker 组成，每个 broker 是一个节点；你创建一个 topic，这个 topic 可以划分为多个 partition，每个 partition 可以存在于不同的 broker 上，每个 partition 就放一部分数据。
    就有所谓的高可用性了，因为如果某个 broker 宕机了，没事儿，那个 broker上面的 partition 在其他机器上都有副本的，如果这上面有某个 partition 的 leader，那么此时会从 follower 中重新选举一个新的 leader 出来，大家继续读写那个新的 leader 即可。这就有所谓的高可用性了。
    --------------------- 
    ```
- [流处理与消息队列------《Designing Data-Intensive Applications》](https://www.cnblogs.com/happenlee/p/8514109.html)
- []()