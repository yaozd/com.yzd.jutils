## tcpdump-抓包
- 
```
tcpdump -i eth0 -s 0 -w a.acp
常用： tcpdump -i eth0 -s 0 -w a.acp
 -i eth0： 用eth0 接口进行抓包； -s 0：表示包有多大，抓取的数据多大； -w a.cap 表示存取到 a.cap 文件中
tcpdump -i enp0s3 -s 0 -w a.acp
抓取指定端口
tcpdump -i eth0 -s 0 -w 80.acp port 80
```