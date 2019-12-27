## *Nginx-故障*
- [Nginx常见的错误及解决方法](https://blog.51cto.com/nanchunle/1657410)
```
错误信息

错误说明

"upstream prematurely（过早的） closed connection"

请求uri的时候出现的异常，是由于upstream还未返回应答给用户时用户断掉连接造成的，对系统没有影响，可以忽略

"recv() failed (104: Connection reset by peer)"

（1）服务器的并发连接数超过了其承载量，服务器会将其中一些连接Down掉； 

（2）客户关掉了浏览器，而服务器还在给客户端发送数据； 

（3）浏览器端按了Stop

"(111: Connection refused) while connecting to upstream"

用户在连接时，若遇到后端upstream挂掉或者不通，会收到该错误

"(111: Connection refused) while reading response header from upstream"

用户在连接成功后读取数据时，若遇到后端upstream挂掉或者不通，会收到该错误

"(111: Connection refused) while sending request to upstream"

Nginx和upstream连接成功后发送数据时，若遇到后端upstream挂掉或者不通，会收到该错误

"(110: Connection timed out) while connecting to upstream"

nginx连接后面的upstream时超时

"(110: Connection timed out) while reading upstream"

nginx读取来自upstream的响应时超时

 

"(110: Connection timed out) while reading response header from upstream"

nginx读取来自upstream的响应头时超时

"(110: Connection timed out) while reading upstream"

nginx读取来自upstream的响应时超时

"(104: Connection reset by peer) while connecting to upstream"

upstream发送了RST，将连接重置

"upstream sent invalid header while reading response header from upstream"

upstream发送的响应头无效

"upstream sent no valid HTTP/1.0 header while reading response header from upstream"

upstream发送的响应头无效

"client intended to send too large body"

用于设置允许接受的客户端请求内容的最大值，默认值是1M，client发送的body超过了设置值

"reopening logs"

用户发送kill  -USR1命令

"gracefully shutting down",

用户发送kill  -WINCH命令

"no servers are inside upstream"

upstream下未配置server

"no live upstreams while connecting to upstream"

upstream下的server全都挂了

"SSL_do_handshake() failed"

SSL握手失败

"ngx_slab_alloc() failed: no memory in SSL session shared cache"

ssl_session_cache大小不够等原因造成

"could not add new SSL session to the session cache while SSL handshaking"

ssl_session_cache大小不够等原因造成
```
- Nginx-499
    - [nginx 499 错误的解决](https://blog.csdn.net/zzhongcy/article/details/88988001)