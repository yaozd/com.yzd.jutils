# Nginx优化详解 来自网络
- [Nginx优化详解(超详细)](https://www.cnblogs.com/mzhaox/p/11215036.html) 推荐参考-byArvin
```
关于内核参数的优化：
            net.ipv4.tcp_max_tw_buckets = 6000

               timewait 的数量，默认是180000。

            net.ipv4.ip_local_port_range = 1024 65000

               允许系统打开的端口范围。

            net.ipv4.tcp_tw_recycle = 1

               启用timewait 快速回收。

            net.ipv4.tcp_tw_reuse = 1

               开启重用。允许将TIME-WAIT sockets 重新用于新的TCP 连接。

            net.ipv4.tcp_syncookies = 1

               开启SYN Cookies，当出现SYN 等待队列溢出时，启用cookies 来处理。

            net.core.somaxconn = 262144

               web 应用中listen 函数的backlog 默认会给我们内核参数的net.core.somaxconn 限制到128，而nginx 定义的NGX_LISTEN_BACKLOG 默认为511，所以有必要调整这个值。

            net.core.netdev_max_backlog = 262144

               每个网络接口接收数据包的速率比内核处理这些包的速率快时，允许送到队列的数据包的最大数目。

            net.ipv4.tcp_max_orphans = 262144

               系统中最多有多少个TCP 套接字不被关联到任何一个用户文件句柄上。如果超过这个数字，孤儿连接将即刻被复位并打印出警告信息。这个限制仅仅是为了防止简单的DoS 攻击，不能过分依靠它或者人为地减小这个值，更应该增加这个值(如果增加了内存之后)。

            net.ipv4.tcp_max_syn_backlog = 262144

               记录的那些尚未收到客户端确认信息的连接请求的最大值。对于有128M 内存的系统而言，缺省值是1024，小内存的系统则是128。

            net.ipv4.tcp_timestamps = 0

               时间戳可以避免序列号的卷绕。一个1Gbps 的链路肯定会遇到以前用过的序列号。时间戳能够让内核接受这种“异常”的数据包。这里需要将其关掉。

            net.ipv4.tcp_synack_retries = 1

               为了打开对端的连接，内核需要发送一个SYN 并附带一个回应前面一个SYN 的ACK。也就是所谓三次握手中的第二次握手。这个设置决定了内核放弃连接之前发送SYN+ACK 包的数量。

            net.ipv4.tcp_syn_retries = 1

               在内核放弃建立连接之前发送SYN 包的数量。

            net.ipv4.tcp_fin_timeout = 1

               如 果套接字由本端要求关闭，这个参数决定了它保持在FIN-WAIT-2 状态的时间。对端可以出错并永远不关闭连接，甚至意外当机。缺省值是60 秒。2.2 内核的通常值是180 秒，3你可以按这个设置，但要记住的是，即使你的机器是一个轻载的WEB 服务器，也有因为大量的死套接字而内存溢出的风险，FIN- WAIT-2 的危险性比FIN-WAIT-1 要小，因为它最多只能吃掉1.5K 内存，但是它们的生存期长些。

            net.ipv4.tcp_keepalive_time = 30

               当keepalive 起用的时候，TCP 发送keepalive 消息的频度。缺省是2 小时。
```

- [【nginx笔记】系统参数设置-使Nginx支持更多并发请求的TCP网络参数](https://www.cnblogs.com/dpf-10/p/7478254.html)