### webflux实现socket-通过心跳，保持客户端与服务器端长链接
- [https://github.com/yaozd/com.yzd.webflux.root](https://github.com/yaozd/com.yzd.webflux.root)-byArvin

#### WebFlux
- [Spring Boot 实践折腾记（13）：使用WebFlux构建响应式「推送API 」](https://blog.csdn.net/mickjoust/article/details/80241104)
- [springboot2学习-webflux与websocke](https://blog.csdn.net/j903829182/article/details/80545876)

```
使用weblux实现了websocket的异步通信，底层用到了reactor技术
源码地址：https://github.com/wj903829182/springcloud5/tree/master/webflux_websocket
```

> **性能测试实践**

- [一次webflux与webmvc性能测试实践](https://www.jianshu.com/p/b2d53667e7e2)
    ```
    另外，我理解的webflux，确实非常适合做不间断的响应式数据推送的场景，
    例如滚动聊天室，百度贴吧最上面的那种聊天，交易信息推送等等。
    不过这些都是后话，也许以后我会把之前的一些响应式编程的总结记录一下，
    不过当前还是思考下benchmark的问题吧。
    ```