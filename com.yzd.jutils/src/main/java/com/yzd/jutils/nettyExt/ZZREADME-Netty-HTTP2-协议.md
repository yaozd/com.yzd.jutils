## HTTP2-协议
- [HTTP2 详解](https://www.jianshu.com/p/e57ca4fec26f)
- [https://www.rfc-editor.org/rfc/rfc7540.txt](https://www.rfc-editor.org/rfc/rfc7540.txt)
- [https://github.com/abbshr/rfc7540-translation-zh_cn](https://github.com/abbshr/rfc7540-translation-zh_cn)
- []()

### [帧的结构](https://www.jianshu.com/p/e57ca4fec26f)
```
Frame Payload 是主体内容，由帧类型决定
共分为十种类型的帧:

HEADERS: 报头帧 (type=0x1)，用来打开一个流或者携带一个首部块片段
DATA: 数据帧 (type=0x0)，装填主体信息，可以用一个或多个 DATA 帧来返回一个请求的响应主体
PRIORITY: 优先级帧 (type=0x2)，指定发送者建议的流优先级，可以在任何流状态下发送 PRIORITY 帧，包括空闲 (idle) 和关闭 (closed) 的流
RST_STREAM: 流终止帧 (type=0x3)，用来请求取消一个流，或者表示发生了一个错误，payload 带有一个 32 位无符号整数的错误码 (Error Codes)，不能在处于空闲 (idle) 状态的流上发送 RST_STREAM 帧
SETTINGS: 设置帧 (type=0x4)，设置此 连接 的参数，作用于整个连接
PUSH_PROMISE: 推送帧 (type=0x5)，服务端推送，客户端可以返回一个 RST_STREAM 帧来选择拒绝推送的流
PING: PING 帧 (type=0x6)，判断一个空闲的连接是否仍然可用，也可以测量最小往返时间 (RTT)
GOAWAY: GOWAY 帧 (type=0x7)，用于发起关闭连接的请求，或者警示严重错误。GOAWAY 会停止接收新流，并且关闭连接前会处理完先前建立的流
WINDOW_UPDATE: 窗口更新帧 (type=0x8)，用于执行流量控制功能，可以作用在单独某个流上 (指定具体 Stream Identifier) 也可以作用整个连接 (Stream Identifier 为 0x0)，只有 DATA 帧受流量控制影响。初始化流量窗口后，发送多少负载，流量窗口就减少多少，如果流量窗口不足就无法发送，WINDOW_UPDATE 帧可以增加流量窗口大小
CONTINUATION: 延续帧 (type=0x9)，用于继续传送首部块片段序列，见

```