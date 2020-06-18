## connection reset 
- [Linux-TCP 出现 RST 的几种情况]()
- [一次"Connection Reset"的根因和修改方式调查](https://blog.csdn.net/fishjam/article/details/84259679)

## netstat 的 -o | --timers 选项
- [netstat 的 -o | --timers 选项](https://blog.csdn.net/fishjam/article/details/84259679)
```
eg:
netstat -ant --timers
//
4.2.2. netstat 的 -o | --timers 选项
注意,这个选项似乎只有Linux上有，Win/Mac都没有，其作用是显示连接中网络时间相关的部分(Include information related to networking timers). 其中Timer列主要有以下几种类型:

off: 表示没有启用TCP层的 KeepAlive
keepalive : 启用了 TCP层的 KeepAlive,第一个参数就是倒计时,减到0时会发送 [TCP Keep-Alive] 包;
timewait: 对应等待(TIME_WAIT)时间计时
on: 重发(retransmission)的时间计时

```

### 正常情况tcp四层握手关闭连接，rst基本都是异常情况，整理如下
```
0.使用 ping 可以看到丢包情况

1. GFW 

2. 对方端口未打开，发生在连接建立

 　　如果对方sync_backlog满了的话，sync简单被丢弃，表现为超时，而不会rst

3. close Socket 时recv buffer 不为空

　　例如，客户端发了两个请求，服务器只从buffer 读取第一个请求处理完就关闭连接，tcp层认为数据没有正确提交到应用，使用rst关闭连接。

3. 移动链路

      移动网络下，国内是有5分钟后就回收信令，也就是IM产品，如果心跳>5分钟后服务器再给客户端发消息，就会收到rst。也要查移动网络下IM 保持<5min 心跳。

4. 负载等设备

      负载设备需要维护连接转发策略，长时间无流量，连接也会被清除，而且很多都不告诉两层机器，新的包过来时才通告rst。

　　 Apple push 服务也有这个问题，而且是不可预期的偶发性连接被rst；rst 前第一个消息write 是成功的，而第二条写才会告诉你连接被重置，

　　曾经被它折腾没辙，因此打开每2秒一次tcp keepalive，固定5分钟tcp连接回收，而且发现连接出错时，重发之前10s内消息。

5. SO_LINGER 应用强制使用rst 关闭

    该选项会直接丢弃未发送完毕的send buffer，可能造成业务错误，慎用； 当然内网服务间http client 在收到应该时主动关闭，使用改选项，会节省资源。

　 好像曾经测试过haproxy 某种配置下，会使用rst关闭连接，少了网络交互而且没有TIME_WAIT 问题

6. 超过超时重传次数、网络暂时不可达

7. TIME_WAIT 状态

　　tw_recycle = 1 时，sync timestamps 比上次小时，会被rst

7. 设置 connect_timeout

     应用设置了连接超时，sync 未完成时超时了，会发送rst终止连接。

8. 非正常包

　 连接已经关闭，seq 不正确等

9. keepalive 超时

    公网服务tcp keepalive 最好别打开；移动网络下会增加网络负担，切容易掉线；非移动网络核心ISP设备也不一定都支持keepalive，曾经也发现过广州那边有个核心节点就不支持。

10. 数据错误，不是按照既定序列号发送数据

11.在一个已关闭的socket上接收数据

12.服务器关闭或异常终止了连接，由于网络问题，客户端没有收到服务器的关闭请求，这称为TCP半打开连接。就算重启服务器，也没有连接信息。如果客户端向提其写入数据，对方就会回应一个RST报文段。 
```