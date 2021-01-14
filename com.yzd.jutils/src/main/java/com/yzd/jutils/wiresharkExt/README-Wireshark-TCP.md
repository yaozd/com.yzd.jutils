## tcp

### 理解TCP序列号（Sequence Number）和确认号（Acknowledgment Number）
- [理解TCP序列号（Sequence Number）和确认号（Acknowledgment Number）](https://blog.csdn.net/dingxie1963/article/details/101259503)
- []()

###  wireshark里面过滤规则tcp.stream eq 是什么意思
- [请问wireshark的Follow Tcp stream功能原理是？那个stream index怎么计算出来的？](https://bbs.csdn.net/topics/380104879)
- [Follow tcp stream - Where does field “Stream index” come from?](https://stackoverflow.com/questions/6076897/follow-tcp-stream-where-does-field-stream-index-come-from)
    ```
  the stream index is an internal Wireshark mapping to: [IP address A, TCP port A, IP address B, TCP port B]
  
  All the packets for the same tcp.stream value should have the same values for these fields (though the src/dest will be switched for A->B and B->A packets)
  
  see the Statistics/Conversations/TCP tab in Wireshark to show a summary of these streams
  ```
- [tcp stream理解](https://blog.csdn.net/dingxie1963/article/details/101259503)
    ```
  根据 IP_1:Port_1 - IP_2:Port_2的唯一标识，可能dns或者udp或者其协议也用。
  但是可以理解到的重点应该是，从一个连接的握手到keep alive 到fin，
  这个tcp stream index是不变的。比如下面的三次握手和四次挥手 tcp stream 都为10 （用 tcp stream index eq 10即可），
  这个与右击某个包数据-追踪流，效果一致。
  ```