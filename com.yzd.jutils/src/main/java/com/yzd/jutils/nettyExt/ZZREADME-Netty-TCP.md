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

Tcp retransmission:发送方推断之前发送的TCP片段丢失，因此重新发送之前的TCP片段。这个计时等待的时间叫做重新发送超时时间(RTO, retransmission timeout)。
PS:tcp retransmission原因(如：路由器太过拥挤，导致一些IP包被丢弃等。)
https://blog.csdn.net/lemontree1945/article/details/88581516

```
- tcp常见错误状态
    ```
    Tcp previous segment lost（tcp先前的分片丢失）
    Tcpacked lost segment（tcp应答丢失）
    Tcp window update（tcp窗口更新）
    Tcp dup ack（tcp重复应答）
    Tcp keep alive（tcp保持活动）
    Tcp retransmission（tcp重传）
    Tcp ACKed unseen segument （tcp看不见确认应答）
    tcp port numbers reused（tcp端口重复使用）
    tcp retransmission（tcp重传）
    tcp fast retransmission (tcp快速重传)
    TCP  Previoussegment lost（发送方数据段丢失）
    tcp spurious retransmission(tcp伪重传)
    ```
- [几种TCP连接中出现RST的场景分析](https://blog.csdn.net/chenlycly/article/details/76383198)
```
1 端口未打开
PS:服务器程序端口未打开而客户端来连接
2.请求超时
PS:程序认为接收超时，所以发送了RST拒绝进一步发送数据
3.提前关闭
PS:TCP是一种可靠的连接'。 而这可靠有这样一种含义，那就是操作系统接收到的来自TCP连接中的每一个字节，我都会让应用程序接收到。如果应用程序不接收怎么办？你猜对了，RST
4.在一个已关闭的socket上收到数据
PS:如果某个socket已经关闭，但依然收到数据也会产生RST。

```