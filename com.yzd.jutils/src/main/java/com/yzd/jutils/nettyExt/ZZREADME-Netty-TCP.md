### [tcp协议中syn ack fin各有什么作用](https://zhidao.baidu.com/question/495480267.html)
> 1-建立连接
```
SYN是TCP建立连接时包内设置的标记，例如（C是客户机，S是服务器。）：
C: SYN
S: SYN, ACK
C: ACK
就建立了一条连接
```
> 2-关闭连接
```
FIN是TCP试图关闭连接时包内设置的标记，例如（C是客户机，S是服务器。）：
C: FIN, ACK
S: ACK
S: FIN, ACK
C: ACK
就关闭了一条连接。
```

### TCP的状态 (SYN, FIN, ACK, PSH, RST, URG)
- [TCP的状态 (SYN, FIN, ACK, PSH, RST, URG)](https://www.cnblogs.com/azraelly/archive/2012/12/25/2832393.html)
- [TCP报文段中URG和PSH的区别](https://blog.csdn.net/wenqiang1208/article/details/72669378)
```
在TCP层，有个FLAGS字段，这个字段有以下几个标识：SYN, FIN, ACK, PSH, RST, URG.

其中，对于我们日常的分析有用的就是前面的五个字段。

 它们的含义是：

SYN表示建立连接，

FIN表示关闭连接，

ACK表示响应，

PSH表示有 DATA数据传输，

RST表示连接重置。

URG表示紧急数据不进入接收缓冲区直接交给上层进程, 当URG = 1时表明紧急指针字段有效，他告诉系统此报文段中有紧急数据，应尽快传送，而不要按原来的排队顺序来传送，发送方的TCP就把紧急数据放到本报文段数据的最前面。
```