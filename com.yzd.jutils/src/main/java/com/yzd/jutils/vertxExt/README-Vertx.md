## vert.x
- [用vertx实现高吞吐量的站点计数器](https://my.oschina.net/jianglibo/blog/215890)
    - [https://github.com/jianglibo/visitrank](https://github.com/jianglibo/visitrank)
 - []()

### vertx 网关
- [https://gitee.com/yaozd/Gravitee-API-Gateway](https://gitee.com/yaozd/Gravitee-API-Gateway) 参考
```
vert.x网关设计参考：
1.
类：io.gravitee.gateway.core.invoker.EndpointInvoker
方法：invoker
2.
io.gravitee.gateway.http.connector.http.HttpProxyConnection
connect
3.
io.gravitee.gateway.http.connector.AbstractConnector
request
final HttpClient client = httpClients.computeIfAbsent(Vertx.currentContext(), createHttpClient());
4.
```
- 
- 
### vertx 调用redis
- [https://github.com/lygoing/redis-benchmark](https://github.com/lygoing/redis-benchmark)