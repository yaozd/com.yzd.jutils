## channel
- [Netty核心概念(5)之Channel](https://www.cnblogs.com/lighten/p/8950347.html) -首要参考byArvin
- [深入理解 Netty-Channel架构体系](https://www.cnblogs.com/ZhuChangwu/p/11204057.html)
```
id()：获取该channel的标识
eventloop()：获取该channel注册的线程池
parent()：获取该channel的父channel，NIO没有父channel，一般为null
config()：获取该channel的配置
isOpen()：该channel是否打开状态 (PS:open状态先于active状态)
isRegistered()：该channel是否注册到线程池中
isActive()：该channel是否可用
metadata()：该channel的元数据
localAddress()：该channel的本地绑定地址端口
remoteAddress()：该channel连接的对端的地址端口
closeFuture()：该channel关闭时触发的future
isWritable()：该channel当前是否可写，只有IO线程会处理可写状态
bytesBeforeUnwritable()：该channel还能写多少字节
unsafe()：获取该channel的unsafe操作对象，对于channel的读写，一般不直接操作channel，而是转交给unsafe对象处理，channel本身通常只做查询状态，获取相关字段内容的操作。
alloc()：获取分配的缓冲区
read()：进行read操作
write()：进行write操作
recvBufAllocHandle()：获取处理读取channel数据之后处理的handler
localAddress()：本地地址端口
remoteAddress()：远程地址端口
register()：将channel注册到线程池中
bind()：服务端绑定本地端口
connect()：客户端连接远程端口
disconnect()：断开连接
close()：关闭channel
closeForcibly()：强制关闭
deregister()：移除线程池的注册
beginRead()：准备读取数据
write()：写入数据
flush()：强制刷新
voidPromise()：特殊的promise
outboundBuffer()：获取输出数据的buffer操作类
```