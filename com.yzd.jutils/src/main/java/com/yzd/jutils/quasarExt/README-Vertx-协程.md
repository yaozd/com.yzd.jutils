## Vertx-协程

## quasar

## coroutine 协程
- [如何将逻辑放倒Fiber里](https://www.jianshu.com/p/bc7d0d18e89f)
```
fiber可以很廉价的被创造出来，但是他本质上还是跑在一个线程上面，
如果其中一个fiber执行了非常耗时的操作，则后面的fiber会一直等待，从而造成整个线程阻塞。
也就是说一个fiber不能执行非常耗时的操作，比如计算100万以内的素数之和，
对于这种操作，我们可以通过直接将逻辑放到vert.x的worker线程里单独去跑，然后通过fiber包装一下就可以了。

```

## Vertx-协程 实现
- [续 vertx-sync实践](https://www.jianshu.com/p/bc7d0d18e89f)

## vertx-sync
- [https://github.com/vert-x3/vertx-sync/](https://github.com/vert-x3/vertx-sync/)
```
vert.x官方为了解决异步代码编写的困难，使之更加同步化对开发人员更友好，
便基于quasar包装了一个的同步库，vertx-sync，该库的作者同样也是vert.x的原作者，所以完成度还是很高的。
```

## 总结
```
相比较传统的回调Handler，vertx-sync的包装十分优雅，基本可以恢复到同步方法调用级别，很好的减轻了异步回调带来的心智负担。
但是这个毕竟不是JVM级别的实现，所以或多或少还是有点门槛的，比如部署的时候，需要通过设置JVM参数来修改部分字节码，同时还要注意一些
需要挂起的方法上面加注释或者强行让其抛出可中断异常。个人建议在一些不重要的工具级项目里使用，非常重要的项目不推荐使用，
当然了如果你觉得你的业务只需要依赖vert.x那么强烈你推荐你使用，只要记得打开 BlockingChecker 就好，可以即时的发现潜在的阻塞逻辑。

链接：https://www.jianshu.com/p/bc7d0d18e89f
```
