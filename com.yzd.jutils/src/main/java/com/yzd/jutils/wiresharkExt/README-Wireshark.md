## Wireshark
- [Wireshark 的基本使用](https://blog.csdn.net/bestcxx/article/details/81212056)
- 安装 Wireshark
    - 官网下载地址 https://www.wireshark.org/#download
## 常用功能
- 对话过滤器
- 对话着色
- 作为过滤器应用

## 常用命令
```
tcp.stream eq 5586

1.过滤不正确的TCP请求
frame.coloring_rule.name == "Bad TCP"
2.过滤重新连接
frame.coloring_rule.name == "TCP RST"
2.
http
http2
http.request.version=="HTTP/1.0"
http.response.version == "HTTP/1.0"
3.
tcp.stream eq 5586
//过滤：建立连接失败，tcp retransmission （超时重试）
tcp.flags == 0x002 and frame.coloring_rule.name == "Bad TCP"
PS:0x002:SYN 建立连接
//
tcp.flags == 0x002 and frame.coloring_rule.name == "Bad TCP" and tcp.stream eq 8549
PS:0x002:SYN 建立连接

```

## 参考：
- [Wireshark常用过滤使用方法](https://www.cnblogs.com/nmap/p/6291683.html)-推荐使用“对话过滤器”来代替手写过滤规则
- [tcp协议中syn ack fin各有什么作用](https://zhidao.baidu.com/question/495480267.html)
- [Tcp抓包以及tcp状态解释](https://blog.csdn.net/zouqingfang/article/details/44452153)
- [TCP RETRANSMISSION 连接超时](https://www.cnblogs.com/bobo-wq/p/11645256.html)
- [关于抓包出现TCP DUP ACK问题](https://blog.csdn.net/doitsjz/article/details/73457447)
- []()

## Tcp抓包以及tcp状态解释
- [Tcp抓包以及tcp状态解释](https://blog.csdn.net/zouqingfang/article/details/44452153)
- TPC状态码
    ```
    SYN表示建立连接
    FIN表示关闭连接
    ACK表示响应
    PSH表示有 DATA数据传输
    RST表示连接重置
    URG：紧急标志。紧急(The urgent pointer) 标志有效。紧急标志置位
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
    TCP Out-Of-Order(出现这个信息的原因就是因为数据在传输过程中顺序乱了，也就是后一个package的seq会小于前一个package的seq+len。)
    ```
- tcpdump抓包命令 [Tcp抓包以及tcp状态解释](https://blog.csdn.net/zouqingfang/article/details/44452153)
    ```
    tcpdump tcp -i eth2 -s 0 and port 20058  -w /home/pjroot/attence.cap
    tcpdump tcp -i eth2 -t -s 0 -c 100 and port 20058  -w /home/pjroot/attence.cap
    tcpdump tcp -i eth2 -s 0 and port 20058 and host 125.77.252.211 -w ./attence.cap
    ————————————————
    -i eth2 指定数据包经过的网卡
    -s 0 抓取数据包时默认抓取长度为68字节。加上-S 0 后可以抓到完整的数据包
    port  指定端口 可以加上src 和dst表示现在为源端口还是目的端口
    host 指定主机可以加上src和dst表示源地址还是目的地址
    -w 表示要写入到文件中
    -t 表示不显示时间戳
    -c 100 表示只抓取初始的100个数据包
    ————————————————
    - 抓取所有经过端口80的交互存入一个文件
    # tcpdump -s 1514 port 80 -w capture_file
    - 以后可以再读回来
    # tcpdump -r capture_file
    
    更多的例子
    # 从10.5.2.3到端口3389的tcp交互
    tcpdump -nnvvS and src 10.5.2.3 and dst port 3389
    # 从网络192.168到网络10或172.16的交互
    tcpdump -nvX src net 192.168.0.0/16 and dst net 10.0.0.0/8 or 172.16.0.0/16
    # 从192.168.0.2到网络172.16的非icmp交互
    tcpdump -nvvXSs 1514 dst 192.168.0.2 and src net and not icmp
    # 从Mars或Pluto到非SSH端口的交互
    tcpdump -vv src Mars or Pluto and not dst port 22
    
    分组
    如果你试图运行这个本来非常有用的命令，因为括号的原因会报错，可以对括号进行转义(前面加"/")或者将整个命令放在单引号中:
    # 从10.0.2.4到端口3389或22的交互(正确的表达)
    tcpdump 'src 10.0.2.4 and (dst port 3389 or 22)'
    ————————————————
    原文链接：https://blog.csdn.net/zouqingfang/article/details/44452153
    ```