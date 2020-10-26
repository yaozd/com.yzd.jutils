# 消息中心

## 中通消息服务运维平台实践（已开源）
- [中通消息服务运维平台实践](https://my.oschina.net/u/4502220/blog/4529897)
- code
    - [https://gitee.com/zto_express/zms](https://gitee.com/zto_express/zms)
    - [https://github.com/ZTO-Express/zms](https://github.com/ZTO-Express/zms)

## 基础概念
- rocket mq
    -  [RocketMQ核心概念扫盲篇](https://my.oschina.net/u/4052033/blog/4679761)
        ```
        RocketMQ提供了众多的队列负载算法:
        1.AllocateMessageQueueAveragely 平均分配
        2.AllocateMessageQueueAveragelyByCircle 轮流平均分配
        消费进度
        1. 集群模式下，消息消费进度存储在broker端
        消费模型:
        1.并发消费
        2.顺序消费
        ```
## 消息中心架构调整 - 新架构设计
- 需要实现的功能
```
希望新架构支持多消息协议、事务消息、消息堆积、低延迟、支持长短连接、推拉模型、消息监控追踪、
消息运维[端连接中断、消息重发、消息阻断等]
覆盖dalayqueue的回调、pusher的推送、ack、mqtt消息等功能
```
- 