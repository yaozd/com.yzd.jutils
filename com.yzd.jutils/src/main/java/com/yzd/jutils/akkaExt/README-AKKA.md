# AKKA(end to end)
- [https://akka.io/](https://akka.io/)
## hello-world
- [快速入门 Akka Java 指南](https://blog.csdn.net/qq_35246620/article/details/79050895)

## 概念篇
- [AKKA~概念篇](https://zhuanlan.zhihu.com/p/25598361)

## 电子书
- [Akka入门与实践 PDF 下载](http://www.java1234.com/a/javabook/javaweb/2017/1215/9991.html)
- [Akka实战：快速构建高可用分布式应用 PDF 下载](https://www.jb51.net/books/677565.html) -ok
- [Akka 中文指南](https://blog.csdn.net/qq_35246620/category_9268180.html)
- [Akka应用模式：分布式应用程序设计实践指南 中文pdf扫描版](https://www.jb51.net/books/642174.html) -ok 正在看
> ok状态：在百度云-》电子书-》akka中有备份

## 示例
- [https://github.com/boomblog/AkkaDemo](https://github.com/boomblog/AkkaDemo) -入门参考-推荐byArvin
    - [https://github.com/yaozd/AkkaDemo](https://github.com/yaozd/AkkaDemo)
- [CookIM - 一个基于akka的分布式websocket聊天程序](https://gitee.com/cookeem/CookIM) -推荐参考
    > 该演示启动了两个CookIM服务，访问地址分别为8080端口以及8081端口，用户通过两个浏览器分别访问不同的的CookIM服务，用户在浏览器中通过websocket发送消息到akka集群，akka集群通过分布式的消息订阅与发布，把消息推送到集群中相应的节点，实现消息在不同服务间的分布式通讯。
                                                                                 
- [Robot Control System](https://github.com/sqshq/robot-control-system)
- [https://github.com/guobinhit/heimdall](https://github.com/guobinhit/heimdall)
    > A log monitor system, based on Akka.
- [https://gitee.com/spirit_demon/CrawlerDemon](https://gitee.com/spirit_demon/CrawlerDemon) - 分布式爬虫 Crawler
- akka-stream-http-kafka
    - [https://github.com/Lockdain/akka-stream-http-kafka](https://github.com/Lockdain/akka-stream-http-kafka)
- [https://gitee.com/beykery/bakka/tree/master](https://gitee.com/beykery/bakka/tree/master) -Akka封装版
                                                                               
## Akka(Actor模型)在中国不温不火
- [为什么Akka(Actor模型)在中国不温不火？](https://www.zhihu.com/question/279512440)
- []()

## 使用场景
- [akka文档（java）——akka的用例示例](https://ifeve.com/akka-doc-java-examples-of-use-cases-for-akka/)

## Akka Cluster
> Akka集群的表现形式也是这样，当然它背后的理论基础是基于gossip协议的
>
>Akka集群支持去中心化的基于P2P的集群服务，没有单点故障（SPOF）问题，它主要是通过Gossip协议和向量时钟（VECTOR CLOCKS）来实现。对于集群成员的状态，Akka提供了一种故障检测机制，能够自动发现出现故障而离开集群的成员节点，通过事件驱动的方式，将状态传播到整个集群的其它成员节点。
- [AKKA Cluster 原理及实战](https://blog.csdn.net/hohojiang/article/details/52444302)
- [Akka集群之Akka Cluster](https://www.jianshu.com/p/6d86a27584e7)
- [https://gitee.com/beykery/bakka/tree/master](https://gitee.com/beykery/bakka/tree/master)

## 对比分析
- [Spark为何使用Netty通信框架替代Akka](https://blog.csdn.net/zhao897426182/article/details/79231752)

## 关键点
- [消息投递](https://blog.csdn.net/beliefer/article/details/53929751)
```
at-most-once 意味着每条应用了这种机制的消息会被投递0次或1次。可以说这条消息可能会丢失。
at-least-once 意味着每条应用了这种机制的消息潜在的存在多次投递尝试并保证至少会成功一次。就是说这条消息可能会重复但是不会丢失。
exactly-once 意味着每条应用了这种机制的消息只会向接收者准确的发送一次。换言之，这种消息既不会丢失也不会重复
```
- [跨越多个数据中心-可能遇到的问题](https://blog.csdn.net/qq_35246620/article/details/54932423)
```
在网络分裂（network partition）期间，群集成员关系的管理将停止，如下面单独一节所述。这意味着在数据中心之间的网络分裂期间，不能添加和删除节点。
对跨数据中心的网络连接进行更频繁的误报检测。在数据中心内部和跨数据中心的故障检测中不可能有不同的设置。
对于网络分裂中的节点关闭/删除，对于数据中心内的故障和跨数据中心的故障，通常应采取不同的处理方法。对于数据中心之间的网络分裂，系统通常不应关闭不可访问的节点，而是等待其恢复，或者等待人工或外部监控系统做出决定。对于同一个数据中心内的故障，可以采用更积极的停机机制进行快速故障切换。
集群单例的快速故障转移和从一个数据中心到另一个数据中心的集群分片很难以安全的方式进行。存在单例或分片实体在网络分裂的两侧变得活跃的风险。
由于缺少位置信息，因此很难优化通信，使其更倾向于靠近较远节点的节点。例如，如果将消息路由到自己的数据中心中的节点，那么支持集群的路由器将更高效。
```
- [Lagom 框架文档中的其他讨论：内部和外部通信](https://blog.csdn.net/qq_35246620/article/details/54932423)
```
外部通信:
通过网关。可通过网关打通，通过网关多活打通多个IDC
内部通信：
AKKA内部网络即可
```
- []()
- []()