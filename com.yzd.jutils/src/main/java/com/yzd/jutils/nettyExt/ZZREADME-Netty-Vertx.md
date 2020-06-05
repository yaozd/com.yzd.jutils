## Vertx-官网
- [https://vertx.io/](https://vertx.io/)
- [Documentation](https://vertx.io/docs/)
- [Create a new Vert.x application](https://start.vertx.io/) -https://start.vertx.io
- [https://github.com/vert-x3/vertx-examples](https://github.com/vert-x3/vertx-examples)
- [https://github.com/vert-x3](https://github.com/vert-x3)
- [vertx-web-client](https://vertx.io/docs/vertx-web-client/java/#_rxjava_2_api)
- []()
## Vertx高级开发教程
- [https://github.com/vietj/advanced-vertx-guide](https://github.com/vietj/advanced-vertx-guide)
- [vert.x中future的简单使用](https://blog.csdn.net/qq_38366063/article/details/105906296)
- [Vert.x Future 解决复杂查询](https://blog.csdn.net/weixin_33856370/article/details/88112968)

## Vertx
- [Vertx学习一：这玩意是到底是个啥](https://blog.csdn.net/lizhou828/article/details/93297153)
- [VERT.X 线程模型揭秘](https://www.cnblogs.com/zhangboyu/p/7452605.html)
- [Vert.x Web Client](https://www.cnblogs.com/heqiyoujing/p/9746253.html)
### Vert.x核心组件
- [Vert.x核心组件概览](https://www.dazhuanlan.com/2019/10/17/5da7749d4e9eb/)
- [https://vertx.io/docs/vertx-core/java/](https://vertx.io/docs/vertx-core/java/)
- []()
- []()
- []()

### [Vertx原理解析](https://blog.csdn.net/lizhou828/article/details/93297153)
```
本小节主要讲一些理论性的东西，核心是想讲明白Vertx的体系结构，verticle和vertx的关系，以及verticles 之间是如何通信和运行的，很枯燥的一章。建议大家先大体看看，不用立刻理解（也不可能），带着疑问继续读后面的几个章节。
Vertx应用在很大程度上是通过定义事件处理程序。应用之间通过事件总线传递消息，异步响应。
Vertx的编程框架可以简单的理解为：
verticle是vert.x中可被部署运行的最小代码块，可以理解为一个verticle就是一个最小化的业务处理引擎。 一个应用程序可以是单个verticle或者由EventBus通信的多个verticles构成。
verticle被发布部署后，会调用其内部的start方法，开始业务逻辑处理，完成后会调用stop方法，对该代码块执行销毁动作。
Verticles在Vert.x实例中运行。 一个Vertx可以承载多个verticles，每个Vert.x实例在其私有的JVM实例运行。 一台服务器可以运行一个或多个Vert.x实例（建议运行Vertx实例的数量和CPU核数正相关）。
一个Vert.x实例，保证其承载的verticles实例总是在同一个线程执行， 并发Vert.x 是单线程的。
在内部，Vert.x实例维护一组线程（通常为CPU核心数）是在执行一个事件循环。
虽然你可能会认为，共享数据和可扩展性截然相反。 Vert.x提供了一个共享的MAP和跨在同一Vert.x实例中运行verticles传递不可改变的数据共享一套设施，这时候数据是可变的唯一真正的 。
Vert.x使用相对较少的线程来创建一个事件循环和执行verticles。 但在某些情况下，需要verticle做一些要么昂贵计算，或可能阻塞，如连接到数据库。 当发生这种情况Vert.x可以让你标记verticle实例作为worker verticle 。
Vert.x确保worker verticles将永远不会被同时执行
```
### Verticle也有三种类型
- 
- [worker_verticles](https://vertx.io/docs/vertx-core/java/#worker_verticles)
```
Vert.x中一共有三种类型的contexts
Eventloop Context
Worker Context
Multi-Thread worker Context
//
Vert.x Context中三种Context对应，Verticle也有三种类型，简单说下每种Verticle特点。
# Standard Verticle
这是最常用的Verticle类型, 这种Verticle会被指派到创建和启动时的Eventloop线程上，
Vert.x会保证你在这个Verticle实例上调用任何的handler操作将在同样的eventloop线程上面执行。
# Worker Verticle
Worker Verticle目标是为了执行阻塞的代码，不会在Eventloop上执行，而是从Vert.x worker线程池中拿出一个线程来执行。
这样的话即使进行的是阻塞操作，它也不会让eventloop阻塞挂掉。Worker verticle在同一个时间片内只会被Vert.x执行在一个线程内，
它不会被并发执行，但是不同时间段有可能被不同线程执行。
# Multi-Thread worker Verticle
Multi-Thread worker Verticle跟Worker Verticle很像，不同的是，
它会被多个线程执行。一旦使用了这种Verticle就一定得注意它在多个线程之间共享的状态所带来的问题。
```

### 示例参考
- [https://github.com/vert-x3/vertx-examples](https://github.com/vert-x3/vertx-examples)
- websocket
    - [https://gitee.com/yaozd/vertx-websocket](https://gitee.com/yaozd/vertx-websocket)
    - [vertx-web-client](https://vertx.io/docs/vertx-web-client/java/#_rxjava_2_api)
    - []()
- 综合示例(包括：静态文件服务，TCP,WEBSOCKET)
    - [https://gitee.com/mirren/Orion-Stress-Tester](https://gitee.com/mirren/Orion-Stress-Tester) -压力测试器

### [Vert.x Web Client](https://www.cnblogs.com/heqiyoujing/p/9746253.html)


### [使用Vertx构建微服务](https://www.cnblogs.com/luxiaoxun/p/7693640.html)
```
Vertx 术语
Verticle
Vertx部署和运行的代码。Verticles可以使用多种语言实现。
Vert.x Instance
Vert.x instance运行在JVM里，Verticle运行在Vert.x instance里。多个Verticles在一个Vert.x instance里异步执行。多个Vert.x instances可以通过Event Bus组成集群。
Concurrency
Standard Verticle：始终在同一个Event Loop线程上执行，同一个Verticle 中的逻辑可以避免资源竞争和死锁。
Worker Verticle：在worker threads上执行，Vertx保证最多同时只有一个worker thread在执行逻辑，避免竞争和死锁。但是在不同的时刻，可能由不同的线程在执行。
Multi-threaded worker verticle：和Worker Verticle类似，但是不保证线程安全，在同一时刻，可能由多个线程在执行。
Event-based Programming Model
使用“事件驱动”的模式去编写代码，采用异步回调handler的方式去处理事件，不能被阻塞！
Event Loops
Vert.x的核心线程池，默认每个Verticle运行在自己的Event Loop线程上，不能被阻塞！
Message Passing
不同的Verticle可以通过Event Bus通信，集群模式下不同主机上的Verticle也可以通过Event Bus通信，来实现distributed applications。
Shared data
不同的Verticle之间可以通过 Shared Data 共享数据。
```

### [VERT.X 线程模型揭秘](https://www.cnblogs.com/zhangboyu/p/7452605.html)
```
编程的方式,通过vertx.deployVerticle发布：
public class Main  extends AbstractVerticle {
    public static void main(String[] args) {
        VertxOptions vo = new VertxOptions();
        vo.setEventLoopPoolSize(16);
        Vertx vertx = Vertx.vertx(vo);
        DeploymentOptions options = new DeploymentOptions();
        options.setInstances(100);
        vertx.deployVerticle(Main.class.getName(), options, e -> {
            System.out.println(e.succeeded());
            System.out.println(e.failed());
            System.out.println(e.cause());
            System.out.println(e.result());
        });
    }
    @Override
    public void start() {
        Handler<HttpServerRequest> handler = e -> {
            HttpServerResponse response = e.response();
            response.putHeader("content-type", "application/json").end("Hello world");
        };
        vertx.createHttpServer().requestHandler(handler).listen(8080);
    }
}
```

### 集群编程
- [集群编程](https://www.cnblogs.com/yangykaifa/p/6714139.html) 
- [vert.x笔记：6.vert.x集群化部署](https://www.cnblogs.com/heartlifes/p/6971037.html) 
- [Hazelcast的一些问题](http://www.iigrowing.cn/hazelcast_de_yi_xie_wen_ti.html) 
- [Vertx集群部署实例](https://blog.csdn.net/feinifi/article/details/55007319) 
- []() 
