## watermarks-Netty设置高低水位
- [Netty设置高低水位](https://www.cnblogs.com/eaglediao/p/6959793.html)
- [2014-twitter-meetup-netty](http://normanmaurer.me/presentations/2014-twitter-meetup-netty/slides.html#10.0) -推荐设置参考
```
Configure high and low write watermarks
//
Server
ServerBootstrap bootstrap = new ServerBootstrap();
bootstrap.childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
bootstrap.childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024);
Client
Bootstrap bootstrap = new Bootstrap();
bootstrap.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
bootstrap.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024)
//
思想二 byArvin：
如API-ROUTER,通过max pending size控制请求的上限
```
## 使用案例：
- [Netty使用案例 -发送队列积压导致内存泄漏（一）](https://blog.csdn.net/u013642886/article/details/86632752) -首要参考-byArvin
- [Netty使用案例 -发送队列积压导致内存泄漏（二）](https://blog.csdn.net/u013642886/article/details/86633786)
- [Netty 那些事儿 ——— Netty实现“流量整形”原理分析及实战](https://www.jianshu.com/p/bea1b4ea8402)
```
环境配置:
-Xmx1000m  -XX:+PrintGC -XX:+PrintGCDetails 
//

```

## Netty高级功能
- [Netty高级功能（一）：流控和流量整形](https://www.jianshu.com/p/6c4a7cbbe2b5)