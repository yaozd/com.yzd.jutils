### webflux实现socket-通过心跳，保持客户端与服务器端长链接
- [https://github.com/yaozd/com.yzd.webflux.root](https://github.com/yaozd/com.yzd.webflux.root)-byArvin
## 使用思路--heart
1. 通过心跳，保持客户端与服务器端长链接
2. 通过 sock.send("Hello-World");每3秒发送一次请求，获取到用户想要的信息-（此处是轮训方式）
3. 使用场景：
    - 可用于活动通知与订单通知。
    - 在线人数与在线时长（用于智能分析）
    - 活动推送等
    
#### WebFlux
- [Spring Boot 实践折腾记（13）：使用WebFlux构建响应式「推送API 」](https://blog.csdn.net/mickjoust/article/details/80241104)
- [springboot2学习-webflux与websocke](https://blog.csdn.net/j903829182/article/details/80545876)

```
使用weblux实现了websocket的异步通信，底层用到了reactor技术
源码地址：https://github.com/wj903829182/springcloud5/tree/master/webflux_websocket
```
> **Jmeter之五：websocket性能测试实践**

- Jmeter之五：websocket测试

> **性能测试实践**

- [一次webflux与webmvc性能测试实践](https://www.jianshu.com/p/b2d53667e7e2)
    ```
    另外，我理解的webflux，确实非常适合做不间断的响应式数据推送的场景，
    例如滚动聊天室，百度贴吧最上面的那种聊天，交易信息推送等等。
    不过这些都是后话，也许以后我会把之前的一些响应式编程的总结记录一下，
    不过当前还是思考下benchmark的问题吧。
    ```