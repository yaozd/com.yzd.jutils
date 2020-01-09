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
##　[Tcp抓包以及tcp状态解释](https://blog.csdn.net/zouqingfang/article/details/44452153)－首要参考byArvin
- tcpdump抓包命令
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