## http2-frame-WINDOW_UPDATE
- [http2-frame-WINDOW_UPDATE](https://blog.csdn.net/weixin_34349320/article/details/89609644)
- [理解HTTP/2流量控制(一)](https://blog.csdn.net/liujiyong7/article/details/60151838)-主要参考byArvin
- [HTTP/2 Flow Control](https://medium.com/coderscorner/http-2-flow-control-77e54f7fd518)
  ```
  WINDOW_UPDATE Frame
  WINDOW_UPDATE frame indicates the number of octets the sender can transmit in addition to the existing flow control window. The legal range is 1 to 2³¹ -1 octets.
  Initial Flow Control Window Size
  According to the HTTP/2 specification, the initial value for the flow control window is 65,535 octets for both the new streams and the overall connection.
  ```
- []()
## 算法描述
-  [HTTP/2 Flow Control](https://medium.com/coderscorner/http-2-flow-control-77e54f7fd518)
```
Maintaining Flow Control Window Size
Since the flow control is directional, both endpoints maintain stream level and connection level windows. The basic mechanism to maintaining the flow control windows, defined by the spec, is as follows.
1.When the sender sends a flow controlled frame(DATA frame), it reduces the space in both windows(stream and connection window) by the length of the transmitted frame.
2.When the receiver consumes data, it can send WINDOW_UPDATE frames to the sender to free up flow control windows. The receiver should send separate WINDOW_UPDATE frames for the stream and the connection.
3.When the sender receives the WINDOW_UPDATE frames it should update the corresponding windows by the specified value in the frame.
====================================
保持流量控制窗口的大小
由于流控制是定向的，两个端点都维护流级和连接级窗口。维护规范定义的流控制窗口的基本机制如下所示。
1.当发送方发送一个流控制帧(数据帧)时，它减少了两个窗口(流和连接窗口)的空间由传输帧的长度。
2.当接收方使用数据时，它可以向发送方发送WINDOW_UPDATE帧来释放流控制窗口。接收方应该为流和连接发送单独的WINDOW_UPDATE帧。
3.当发送方接收到WINDOW_UPDATE帧时，它应该按照帧中指定的值更新相应的窗口
```
## 算法描述
```
送端保有一个流量控制窗口（window）初始值。初始值的设定请参考SETTING 帧的 SETTINGS_INITIAL_WINDOW_SIZE
发送端每发送一个DATA帧，就把window递减，递减量为这个帧的大小。如果当前window小于帧大小，那么这个帧就必须被拆分到不大于window，如果window等于0，就不能发送任何帧
接收端可以发送 WINDOW_UPDATE帧给发送端，发送端以帧内指定的Window Size Increment作为增量，加到window上

```

## nginx-window_update实现
- [理解HTTP/2流量控制(一)](https://blog.csdn.net/liujiyong7/article/details/60151838)
```
init_window;        // 默认65535

HTTP/2没有规定接收方如何决定何时发送WINDOW_UPDATE帧、发送什么样的值，具体算法依赖服务器具体实现。
nginx选择在接收窗口小于窗口最大值1/4时发送WINDOW_UPDATE帧，并且将窗口大小增长到最大值2^31-1
//
发送DATA帧
h2c->send_window -= frame_size; 
stream->send_window -= frame_size;
```