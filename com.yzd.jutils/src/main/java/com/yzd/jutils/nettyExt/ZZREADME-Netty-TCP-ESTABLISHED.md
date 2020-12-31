# TCP ESTABLISHED

## 应用
```
解决：通过修改keepalive配置为合适的值（如改为200秒）可以快速释放端口连接。

临时修改，通过echo设置tcp_keepalive_time。
echo "200" >  /proc/sys/net/ipv4/tcp_keepalive_time
或
sysctl -w fs.file-max=2000500
sysctl -w fs.nr_open=2000500
sysctl -w net.nf_conntrack_max=2000500

永久生效，编辑/etc/sysctl.conf，新增或修改为
net.ipv4.tcp_keepalive_time = 200

然后执行命令使修改后的配置生效
sysctl -p
```

## 参考
- Nginx系统内核优化
    - [Nginx优化详解(超详细)](https://www.cnblogs.com/mzhaox/p/11215036.html)
    - [【nginx笔记】系统参数设置-使Nginx支持更多并发请求的TCP网络参数](https://www.cnblogs.com/dpf-10/p/7478254.html)
- [Linux下TCP连接断开后不释放的解决办法](https://www.cnblogs.com/chinaops/p/9469214.html)
    ```
  sysctl -a |grep keepalive
  发现为默认的配置
  net.ipv4.tcp_keepalive_time = 7200
  net.ipv4.tcp_keepalive_probes = 9
  net.ipv4.tcp_keepalive_intvl = 75
  ```
- [linux tcp 端口连接不释放](https://blog.csdn.net/xiewenbo/article/details/103131125)
    ```
  #打开重用
  net.ipv4.tcp_tw_reuse = 1
  #打开快速回收
  net.ipv4.tcp_tw_recycle = 1
  保存后执行 sysctl -p 生效
  ```
- [不要启用 net.ipv4.tcp_tw_recycle](https://blog.csdn.net/enweitech/article/details/79261439)
    ```
   启用TIME-WAIT状态sockets的快速回收，这个选项不推荐启用。在NAT(Network Address Translation)网络下，会导致大量的TCP连接建立错误。
  ```
- [网络优化之net.ipv4.tcp_tw_recycle参数](https://blog.csdn.net/enweitech/article/details/79261439)
    ```
  sysctl -a | grep time | grep wait
  net.ipv4.netfilter.ip_conntrack_tcp_timeout_time_wait = 120
  net.ipv4.netfilter.ip_conntrack_tcp_timeout_close_wait = 60
  net.ipv4.netfilter.ip_conntrack_tcp_timeout_fin_wait = 120
  这样的现象实际是正常的，有时和访问量大有关，设置这两个参数： reuse是表示是否允许重新应用处于TIME-WAIT状态的socket用于新的TCP连接； recyse是加速TIME-WAIT sockets回收。
  net.ipv4.tcp_syncookies=1 打开TIME-WAIT套接字重用功能，对于存在大量连接的Web服务器非常有效。 
  net.ipv4.tcp_tw_recyle=1 
  net.ipv4.tcp_tw_reuse=1 减少处于FIN-WAIT-2连接状态的时间，使系统可以处理更多的连接。 
  net.ipv4.tcp_fin_timeout=30 减少TCP KeepAlive连接侦测的时间，使系统可以处理更多的连接。 
  net.ipv4.tcp_keepalive_time=1800 增加TCP SYN队列长度，使系统可以处理更多的并发连接。 
  net.ipv4.tcp_max_syn_backlog=8192
  net.ipv4.tcp_syncookies = 1
  #表示开启SYN Cookies。当出现SYN等待队列溢出时，启用cookies来处理，可防范少量SYN攻击，默认为0，表示关闭；
  net.ipv4.tcp_tw_reuse = 1
  #表示开启重用。允许将TIME-WAIT sockets重新用于新的TCP连接，默认为0，表示关闭；
  net.ipv4.tcp_tw_recycle = 1
  #表示开启TCP连接中TIME-WAIT sockets的快速回收，默认为0，表示关闭。
  net.ipv4.tcp_fin_timeout = 30
  #表示如果套接字由本端要求关闭，这个参数决定了它保持在FIN-WAIT-2状态的时间。
  net.ipv4.tcp_keepalive_time = 1200 
  #表示当keepalive起用的时候，TCP发送keepalive消息的频度。缺省是2小时，改为20分钟。
  net.ipv4.ip_local_port_range = 1024    65000 
  #表示用于向外连接的端口范围。缺省情况下很小：32768到61000，改为1024到65000。
  net.ipv4.tcp_max_tw_buckets = 5000
  #表示系统同时保持TIME_WAIT套接字的最大数量，如果超过这个数字，
  #TIME_WAIT套接字将立刻被清除并打印警告信息。默认为180000，改为5000。
  ```
- [https://github.com/smallnest/1m-go-tcp-server](https://github.com/smallnest/1m-go-tcp-server) 百万 Go TCP 连接的思考
    ```
  tune the linux:
  
  sysctl -w fs.file-max=2000500
  sysctl -w fs.nr_open=2000500
  sysctl -w net.nf_conntrack_max=2000500
  ulimit -n 2000500
  
  sysctl -w net.ipv4.tcp_tw_recycle=1
  sysctl -w net.ipv4.tcp_tw_reuse=1
  ```
- [linux netstat tcp（全连接半连接）详解](https://blog.csdn.net/weixin_40139740/article/details/84034390)
- []()