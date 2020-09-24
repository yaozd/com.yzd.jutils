# Envoy

## Github
- [https://github.com/envoyproxy/envoy](https://github.com/envoyproxy/envoy)

## envoy的连接模型是走的池形式-要扛大连接的话，只能走池模型
- [https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/upstream/connection_pooling#http-2](https://www.envoyproxy.io/docs/envoy/latest/intro/arch_overview/upstream/connection_pooling#http-2)
```
HTTP/2
The HTTP/2 connection pool multiplexes multiple requests over a single connection, 
up to the limits imposed by max concurrent streams and max requests per connection. 
The HTTP/2 connection pool establishes as many connections as are needed to serve requests. 
With no limits, this will be only a single connection. 
If a GOAWAY frame is received or if the connection reaches the maximum requests per connection limit, 
the connection pool will drain the affected connection. 
Once a connection reaches its maximum concurrent stream limit, 
it will be marked as busy until a stream is available. 
New connections are established anytime there is a pending request without a connection that can be dispatched to
 (up to circuit breaker limits for connections). HTTP/2 is the preferred communication protocol, as connections rarely, if ever, get severed.
```

## [Envoy为什么能战胜Ngnix——线程模型分析篇](https://blog.csdn.net/weixin_45583158/article/details/100143421)