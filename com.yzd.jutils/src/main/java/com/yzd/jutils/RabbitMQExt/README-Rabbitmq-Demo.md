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
```
> 推荐参考
- [rabbitMq生产者角度:消息持久化、事务机制、PublisherConfirm、mandatory](https://blog.csdn.net/u014045580/article/details/70311746)
- [RabbitMQ实战篇10-消息确认机制之消息的准确发布](https://blog.csdn.net/zhuzhezhuzhe1/article/details/80706167)
- [Git-https://github.com/zhuzhegithub/rabbitmq](https://github.com/zhuzhegithub/rabbitmq)
- [Springboot整合一之Springboot整合RabbitMQ](https://blog.csdn.net/zhuzhezhuzhe1/article/details/80454956)
- [Git-https://github.com/zhuzhegithub/springboot-rabbitmq](https://github.com/zhuzhegithub/springboot-rabbitmq)
- [springboot整合rabbitMQ步骤介绍以及各模式发布接收消息的Demo](https://blog.csdn.net/u011059021/article/details/79827793)
- []()
> Rabbitmq-spring boot-demo
