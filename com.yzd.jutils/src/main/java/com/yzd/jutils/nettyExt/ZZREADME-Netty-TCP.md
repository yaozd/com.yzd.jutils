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