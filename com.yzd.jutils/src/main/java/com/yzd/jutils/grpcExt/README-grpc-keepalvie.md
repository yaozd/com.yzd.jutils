# grpc-keepalive
- [grpc keepalive使用指南](https://blog.csdn.net/zhaominpro/article/details/103127023)
- [grpc keepalive-默认值](https://blog.csdn.net/zhaominpro/article/details/103127023)

```
本指南记录了gRPC core中控制keepalive ping行为方式。
keepalive ping由两个重要的通道参数控制：

GRPC_ARG_KEEPALIVE_TIME_MS
此channel参数控制在transport上发送keepalive ping的时间间隔（以毫秒为单位）。
GRPC_ARG_KEEPALIVE_TIMEOUT_MS
此channel参数控制keepalive ping的发送方等待确认的时间（以毫秒为单位）。如果在此时间内未收到确认，它将关闭连接。
上面的两个通道参数对于大多数用户来说应该足够了，但是以下参数在某些用例中也很有用。

GRPC_ARG_KEEPALIVE_PERMIT_WITHOUT_CALLS
如果将此通道参数设置为1（0：false; 1：true），则即使没有请求进行，也可以发送keepalive ping。
GRPC_ARG_HTTP2_MAX_PINGS_WITHOUT_DATA
当没有其他数据（数据帧或标头帧）要发送时，此通道参数控制可发送的最大ping数。如果超出限制，GRPC Core将不会继续发送ping。将其设置为0将允许在不发送数据的情况下发送ping命令。
GRPC_ARG_HTTP2_MIN_SENT_PING_INTERVAL_WITHOUT_DATA_MS
如果transport中没有接收到数据帧，则此channel参数控制gRPC Core在连续的ping之间等待的最短时间（以毫秒为单位）。
GRPC_ARG_HTTP2_MIN_RECV_PING_INTERVAL_WITHOUT_DATA_MS
如果transport中没有发送数据帧，则服务器端的此channel参数控制gRPC Core在接收连续ping之间期望的最短时间（以毫秒为单位）。如果连续两次ping之间的时间少于此时间，则该ping将被视为对等端的不良ping。这样的ping算作“ ping strike”。在客户端，这没有任何效果。
GRPC_ARG_HTTP2_MAX_PING_STRIKES
此arg控制在发送HTTP2 GOAWAY帧并关闭传输之前，服务器允许的错误ping的最大数量。将其设置为0允许服务器接受任意数量的错误ping。(注：也就是达到这个数量的ping strike就会发送GOWAY帧–用于发起关闭连接的请求，或者警示严重错误。GOAWAY 会停止接收新流，并且关闭连接前会处理完先前建立的流)
```