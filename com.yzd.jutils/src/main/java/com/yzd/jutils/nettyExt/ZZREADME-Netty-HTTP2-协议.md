## HTTP2-协议
- [HTTP2 详解](https://www.jianshu.com/p/e57ca4fec26f)
- [https://www.rfc-editor.org/rfc/rfc7540.txt](https://www.rfc-editor.org/rfc/rfc7540.txt)
- [https://github.com/abbshr/rfc7540-translation-zh_cn](https://github.com/abbshr/rfc7540-translation-zh_cn) 中文翻译-推荐byArvin
- [https://httpwg.org/specs/rfc7540.html](https://httpwg.org/specs/rfc7540.html)
- []()

### streamId
- [https://httpwg.org/specs/rfc7540.html#StreamIdentifiers](https://httpwg.org/specs/rfc7540.html#StreamIdentifiers)
- [HTTP2 Stream标识符](https://blog.csdn.net/yangguosb/article/details/80640264)
```
Stream Dependency: 指定一个 stream identifier，代表当前流所依赖的流的 id，存在则代表 PRIORITY flag 被设置
//
Stream标识符
作用：唯一标识连接内的某个流，用31位无符号整型标识，范围2~2^31-1；
注意：

0x0用来表示连接控制信息流，不能用来标识流；
0x1用于标识升级到HTTP/2的客户端流，不能用来标识流；(stream id=1,表示升级)
新建流第一次被使用时，低于此标识符的并且处于空闲”idle”状态的流都会被关闭；
创建规则：

已使用的流标识符不能被再次使用；
新建流的标识符要大于已有流和预留的流的标识符；
客户端创建的流以奇数表示，服务器端创建流以偶数表示；
终端的流标识符被耗尽时的处理规则：

若是客户端，需要关闭连接，创建新的连接创建新流；
若是服务器端，需要发送一个GOAWAY帧通知客户端，强迫其打开一个新连接；
```

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

### Error Codes
```
https://www.rfc-editor.org/rfc/rfc7540.txt
Error Codes
//
NO_ERROR(0L),		        				没有错误
PROTOCOL_ERROR(1L),                         协议错误
INTERNAL_ERROR(2L),                         内部错误
FLOW_CONTROL_ERROR(3L),                     流控制错误
SETTINGS_TIMEOUT(4L),                       设置超时
STREAM_CLOSED(5L),                          流关闭
FRAME_SIZE_ERROR(6L),                       帧大小错误
REFUSED_STREAM(7L),                         拒绝流
CANCEL(8L),                                 取消
COMPRESSION_ERROR(9L),                      压缩错误
CONNECT_ERROR(10L),                         连接错误
ENHANCE_YOUR_CALM(11L),                     提高你的平静
INADEQUATE_SECURITY(12L),                   安全不足
HTTP_1_1_REQUIRED(13L);                     HTTP_1_1要求
```

## END_HEADERS
- [https://www.rfc-editor.org/rfc/rfc7540.txt](https://www.rfc-editor.org/rfc/rfc7540.txt)
- [HTTP2 详解](https://www.jianshu.com/p/e57ca4fec26f)
```
A receiving endpoint reassembles the header block by concatenating
its fragments and then decompresses the block to reconstruct the
header list.
//
A complete header block consists of either:
a single HEADERS or PUSH_PROMISE frame, with the END_HEADERS flag
set, or
a HEADERS or PUSH_PROMISE frame with the END_HEADERS flag cleared
and one or more CONTINUATION frames, where the last CONTINUATION
frame has the END_HEADERS 
```

## flow-control window
```
https://www.rfc-editor.org/rfc/rfc7540.txt      page-22
HTTP/2 provides for flow control through use of the WINDOW_UPDATE frame
Deployments that do not require this capability can advertise a flow-
control window of the maximum size (2^31-1) and can maintain this
window by sending a WINDOW_UPDATE frame when any data is received.

默认：2^20     1048576
- 524288次调用 是2的19次方
最大值：2^31-1        2147483647
```