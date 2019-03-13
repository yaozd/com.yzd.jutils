>  RabbitMQ-Install
- [windows10环境下的RabbitMQ安装步骤（图文）](https://blog.csdn.net/weixin_39735923/article/details/79288578)
```
目前使用的版本：rabbitmq-server-3.7.11 
用管理员模式下执行cmd 定位到rabbitMQ的安装目录
打开管理界面：
rabbitmq-plugins enable rabbitmq_management
命令：
rabbitmq-service.bat stop  
rabbitmq-service.bat install  
rabbitmq-service.bat start 
-----------------
```

>  RabbitMQ-启动
- [http://localhost:15672](http://localhost:15672) 
```
默认用户名和密码都是:guest
```
> [Rabbitmq-基本概念](https://blog.csdn.net/qq_38455201/article/details/80308771)
```
/**
Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输, 
Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。 
Queue:消息的载体,每个消息都会被投到一个或多个队列。 
Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来. 
Routing Key:路由关键字,exchange根据这个关键字进行消息投递。 
vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。 
Producer:消息生产者,就是投递消息的程序. 
Consumer:消息消费者,就是接受消息的程序. 
Channel:消息通道,在客户端的每个连接里,可建立多个channel.
*/
--------------------- 
exchange交换机机制
什么是交换机
binding？
Direct Exchange交换机：所有binding到该交换机上的queue，routing-key都和queue的name一样
Topic Exchange交换机：通配符交换机，exchange会把消息发送到一个或者多个满足通配符规则的routing-key的queue。其中*表号匹配一个word，#匹配多个word和路径，路径之间通过.隔开。如满足a.*.c的routing-key有a.hello.c；满足#.hello的routing-key有a.b.c.helo
Fanout Exchange交换机：该交换机会把消息发送到所有binding到该交换机上的queue。这种是publisher/subcribe模式。用来做广播最好
Header Exchange交换机：设置header attribute参数类型的交换机
```
> Rabttimq-基本请求流程
```
P: 生产者
X：交换机
Q：消息队列
C：消费者
BK:bindingKey：Q到X绑定的Key，不是唯一的，可以相同。
M：消息
RK:routingKey:P到X绑定的Key，不是唯一的，可以相同。
----------------------
P->M->X->RK->BK->Q->C->M
```
> [AcknowledgeMode选择](https://blog.csdn.net/weixin_38380858/article/details/84963944)
```
AcknowledgeMode.NONE模式
AcknowledgeMode.AUTO模式
AcknowledgeMode.MANUAL模式

对比
无ack模式：效率高，存在丢失大量消息的风险。
有ack模式：效率低，不会丢消息。
```
> 参考书
- [RabbitMQ实战](https://book.douban.com/subject/26649178/?from=tag)

> 推荐参考-配置多个RabbitMQ
- [Spring Boot配置多个RabbitMQ-推荐参考byArvin](https://blog.csdn.net/qq_37608766/article/details/80912370)
- [SpringBoot中如何监听两个不同源的RabbitMQ消息队列-推荐参考byArvin](https://blog.csdn.net/u012129558/article/details/79495298)
```
监听(消费方)与生产者（消息生产方）配置
```
> [rabbitmq的延迟队列](https://blog.csdn.net/u012129558/article/details/85330768)
```
延迟队列能做什么？
订单业务： 在电商/点餐中，都有下单后 30 分钟内没有付款，就自动取消订单。
短信通知： 下单成功后 60s 之后给用户发送短信通知。
失败重试： 业务操作失败后，间隔一定的时间进行失败重试。
```

> 推荐参考
- [rabbitMq生产者角度:消息持久化、事务机制、PublisherConfirm、mandatory](https://blog.csdn.net/u014045580/article/details/70311746)
- [RabbitMQ实战篇10-消息确认机制之消息的准确发布](https://blog.csdn.net/zhuzhezhuzhe1/article/details/80706167)
- [Git-https://github.com/zhuzhegithub/rabbitmq-推荐byArvin](https://github.com/zhuzhegithub/rabbitmq)
- [Springboot整合一之Springboot整合RabbitMQ](https://blog.csdn.net/zhuzhezhuzhe1/article/details/80454956)
- [Git-https://github.com/zhuzhegithub/springboot-rabbitmq](https://github.com/zhuzhegithub/springboot-rabbitmq)
- [springboot整合rabbitMQ步骤介绍以及各模式发布接收消息的Demo](https://blog.csdn.net/u011059021/article/details/79827793)
- [RabbitMQ笔记十五：消息确认之一（Publisher Confirms）-手写]()
- [RabbitMq（一）走进RabbitMq-推荐参考byArvin-可实现自定义绑定RabbitMQ的连接监听](https://www.jianshu.com/p/a5f7fce67803)
- [随便谈谈RabbitMQ与springBoot进行集成-RabbitAutoConfig](https://blog.csdn.net/roykingw/article/details/78404956)
```
@RabbitListener(containerFactory = "helloRabbitListenerContainer",queues = "spring-boot")
PS:重新定义containerFactory的实现
```
- [Springboot中整合RabbitMq之Topic模式](https://blog.csdn.net/zhaodj5660/article/details/79895562)
- []()
- []()
- []()

> Rabbitmq-spring boot-demo
- [Git-https://github.com/zhuzhegithub/springboot-rabbitmq](https://github.com/zhuzhegithub/springboot-rabbitmq)
- [Git-https://github.com/zhuzhegithub/rabbitmq-推荐byArvin](https://github.com/zhuzhegithub/rabbitmq)