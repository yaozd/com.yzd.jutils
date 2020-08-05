# EventBus
> EventBus是Guava的事件处理机制，是设计模式中的观察者模式（生产/消费者编程模型）的优雅实现
>
- [Guava之EventBus原理](https://www.jianshu.com/p/4bddd45a8e7a)
- [google Guava之EventBus](https://blog.csdn.net/wangdong5678999/article/details/80561198) -推荐参考byArvin
    - EventBus之异常处理
    > 在默认情况下，EventBus不会对异常信息进行处理，异常信息也不会终止EventBus的运行，只会简单的打印出异常堆栈信息。
    - DeadEvent 注册的订阅者中没有找到处理该事件的方法时调用
    > 当EventBus发布了一个事件，但是注册的订阅者中没有找到处理该事件的方法，那么EventBus就会把该事件包装成一个DeadEvent事件来重新发布；我们在应用中可以提供如下的事件处理方法来处理DeadEvent。

- []()

## EventBus路由规则-通过监听参数类型
```
不同类型参数的Subscribe
监听的方法，必须使用注解声明，且只能有一个参数，实际触发一个事件的时候会根据参数类型触发方法
结论：eventBus会根据Listener的参数类型的不同，分别向不同的Subscribe发送不同的消息。
```

## EventBus和AsyncEventBus使用区别
- [EventBus和AsyncEventBus使用区别](https://blog.csdn.net/qq_38345296/article/details/100539989)
```
上面的测试案例简单，并且很能说明问题。
EventBus:同步事件总线
1.同步执行，事件发送方在发出事件之后，会等待所有的事件消费方执行完毕后，才会回来继续执行自己后面的代码。
2.事件发送方和事件消费方会在同一个线程中执行，消费方的执行线程取决于发送方。
3.同一个事件的多个订阅者，在接收到事件的顺序上面有不同。谁先注册到EventBus的，谁先执行，如果是在同一个类中的两个订阅者一起被注册到EventBus的情况，收到事件的顺序跟方法名有关。
AsyncEventBus:异步事件总线
1.异步执行，事件发送方异步发出事件，不会等待事件消费方是否收到，直接执行自己后面的代码。
2.在定义AsyncEventBus时，构造函数中会传入一个线程池。事件消费方收到异步事件时，消费方会从线程池中获取一个新的线程来执行自己的任务。
3.同一个事件的多个订阅者，它们的注册顺序跟接收到事件的顺序上没有任何联系，都会同时收到事件，并且都是在新的线程中，异步并发的执行自己的任务。
```
## AllowConcurrentEvents(进行并发设置)
- [Guava异步事件总线AsyncEventBus的注解AllowConcurrentEvents分析](https://blog.csdn.net/koflance/article/details/55211990)
```
结论：
如果当前观察者（method）是线程安全的thread-safe，建议增加注解@AllowConcurrentEvents，以减少同步开销。
对于使用的是非异步（AsyncEventBus），也建议增加@AllowConcurrentEvents，因为不需要进行同步。
```

## 示例
### 状态机
- [EventBus-实现java状态机](https://www.jianshu.com/p/8def04b34b3c) -推荐参考byArvin
    - [https://github.com/jettyrun/statusMachine](https://github.com/jettyrun/statusMachine)
    - [https://github.com/yaozd/statusMachine](https://github.com/yaozd/statusMachine)
### 其他
- [基于Guava、RocketMQ的事件主线](https://blog.csdn.net/liuyanglglg/article/details/105455332)
- [google guava EventBus使用](https://www.jianshu.com/p/4efbfdc01cf6) -spring cloud中使用

### 需求
```
一个简单的电商，有几个重要的需求点
//
商品下单后TODO:
存储订单信息
锁定商品库存
消息推送商家端
//
订单支付后TODO:
存储订单支付信息
商品库存减少
消息推送商家端
会员积分调整
```