## wireshark 抓包HTTP2
- [详解http-2头部压缩算法](https://segmentfault.com/a/1190000017011816)

## 抓包HTTP2-示例
```
tcpdump抓包：
tcpdump -i eth0 port 80 -w http2-12_14_1026.pcap > /dev/null 2>&1 &
//
Wireshark解析：
1.
调整解码类型为:HTTP2
PS:分析-》解码为-》调整为HTTP2
2.
必须为完整的HTTP2的请求包（因为：http-2头部压缩算法，如果不完整则header的名称显示不正常为unknow）
HTTP2请求包头为：Magic,SETTINGS[0]
3.
分析数据即可

```