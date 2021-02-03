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
    - [由于net.ipv4.tcp_tw_recycle和net.ipv4.tcp_timestamps引发的请求无响应问题](https://blog.csdn.net/top_explore/article/details/107633854)
        ```
      net.ipv4.tcp_tw_recycle 作用：能够更快地回收TIME-WAIT套接字
      net.ipv4.tcp_tw_recycle =1  配合net.ipv4.tcp_timestamps=1
      把tcp_tw_recycle设置为1，则60s内同一源ip主机的socket connect请求中的timestamp必须是递增的。
      也就是说服务器打开了 tcp_tw_reccycle了，就会检查时间戳，如果对方发来的包的时间戳是乱跳的或者说时间戳是滞后的，
      这样服务器肯定不会回复，所以服务器就把带了“倒退”的时间戳的包当作是“recycle的tw连接的重传数据，不是新的请求”，
      于是丢掉不回包，就出现了开始说的syn不响应。这个开关，需要net.ipv4.tcp_timestamps（默认开启的）这个开关开启才有效果。
      ```
    - [tcp_tw_reuse、tcp_tw_recycle 使用场景及注意事项](https://www.cnblogs.com/lulu/p/4149312.html)
        ```
      1. tw_reuse，tw_recycle 必须在客户端和服务端 timestamps 开启时才管用（默认打开）
      2. tw_reuse 只对客户端起作用，开启后客户端在1s内回收
      3. tw_recycle 对客户端和服务器同时起作用，开启后在 3.5*RTO 内回收，RTO 200ms~ 120s 具体时间视网络状况。
      　　内网状况比tw_reuse 稍快，公网尤其移动网络大多要比tw_reuse 慢，优点就是能够回收服务端的TIME_WAIT数量
      对于客户端
      1. 作为客户端因为有端口65535问题，TIME_OUT过多直接影响处理能力，打开tw_reuse 即可解决，不建议同时打开tw_recycle，帮助不大；
      2. tw_reuse 帮助客户端1s完成连接回收，基本可实现单机6w/s短连接请求，需要再高就增加IP数量；
      3. 如果内网压测场景，且客户端不需要接收连接，同时 tw_recycle 会有一点点好处；
      4. 业务上也可以设计由服务端主动关闭连接
      
      对于服务端
      1. 打开tw_reuse无效
      2. 线上环境 tw_recycle 不建议打开
        服务器处于NAT 负载后，或者客户端处于NAT后（基本公司家庭网络基本都走NAT）；
      　公网服务打开就可能造成部分连接失败，内网的话到时可以视情况打开；
        像我所在公司对外服务都放在负载后面，负载会把 timestamp 都给清空，就算你打开也不起作用。
      3. 服务器TIME_WAIT 高怎么办
        不像客户端有端口限制，处理大量TIME_WAIT Linux已经优化很好了，每个处于TIME_WAIT 状态下连接内存消耗很少，
        而且也能通过tcp_max_tw_buckets = 262144 配置最大上限，现代机器一般也不缺这点内存。
        下面像我们一台每秒峰值1w请求的 http 短连接服务，长期处于tw_buckets 溢出状态，
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

## tcp连接的建立时间
- [如何查看tcp连接的建立时间？](https://blog.csdn.net/stpeace/article/details/104651624/)
    ```
  stat /proc/12335/fd/6
  ```
- [Linux系统stat指令用法](https://www.cnblogs.com/klb561/p/9241228.html)
    ```
  stat命令主要用于显示文件或文件系统的详细信息
  ```
- []()