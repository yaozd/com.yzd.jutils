### 同步队列-控制当前线程池执行的线程数
- 同步队列

### 使用场景
- 解决MQ情况下的公平调度问题

### 思路
- 1.拉取任务，执行模式：串行
- 2.处理任务，执行模式：并行

### 参考
- RabbitMQ[https://github.com/yaozd/rabbitmq/tree/dev-yzd](https://github.com/yaozd/rabbitmq/tree/dev-yzd)
- 如何优雅退出，参考：
    - RabbitMQ[https://github.com/yaozd/rabbitmq/tree/dev-yzd](https://github.com/yaozd/rabbitmq/tree/dev-yzd)
