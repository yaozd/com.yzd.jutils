> Rabbitmq减少重复请求，实现方式Rabbitmq+Redis的基本思路
```
1.Redis-set:拦截请求，挡板作用。
2.Redis-SortedSet：时间维度做为计数，维护正在运行的任务，防止发版时任务没有处理。
3.RabbitMq-ACK:手动确认模式保证消息100%处理
4.CountDownLacth:通过计数器实现任务的自动扩容
----
1，消息进来后先插入到RabbitMq,然后再放入到Redis-set中。
2, 取消息先插入到Redis-SortedSet，然后再确认消息已经处理。
3.处理完成后，先删除Redis-set，再删除Redis-SortedSet
```