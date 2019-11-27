#　内核调优-网关
> 基于centos7.4 support 50K+ connections

##　文件打开数调优
```
sudo root user
/etc/security/limits.conf
  
root soft nofile 655350
root hard nofile 655350
* soft nofile 655350
* hard nofile 655350
  
apply: relogin
```

## 内核参数调优
```
/etc/sysctl.conf
#The maximum number of file handles for the entire system
fs.file-max = 999999
 
#Enable reuse of TIME-WAIT sockets for new connections when it is safe from protocol viewpoint
net.ipv4.tcp_tw_reuse = 1
#Maximal number of timewait sockets held by system simultaneously
net.ipv4.tcp_max_tw_buckets = 5000
#The length of time an orphaned (no longer referenced by any application) connection will remain in the FIN_WAIT_2 state before it is aborted at the local end, unit seconds
net.ipv4.tcp_fin_timeout = 30
#How often TCP sends out keepalive messages when keepalive is enabled.unit second
net.ipv4.tcp_keepalive_time = 600
#Maximal number of TCP sockets not attached to any user file handle,held by system.
net.ipv4.tcp_max_orphans=262114
#Maximal number of remembered connection requests, which have not received an acknowledgment from connecting client.
net.ipv4.tcp_max_syn_backlog = 8192
#Send out syncookies when the syn backlog queue of a socket overflows. This is to prevent against the common 'SYN flood attack'
net.ipv4.tcp_syncookies = 1
#The default range of IP port numbers that are allowed for TCP and UDP traffic
net.ipv4.ip_local_port_range = 1024 65000
#Size of receive buffer used by TCP sockets, min/default/max
net.ipv4.tcp_rmem = 10240 87380 12582912
#Size of send buffer used by TCP sockets, min/default/max
net.ipv4.tcp_wmem = 10240 87380 12582912
 
#Maximum number  of  packets,  queued  on  the  INPUT  side, when the interface receives packets faster than kernel can process them
net.core.netdev_max_backlog = 16384
#Limit of socket listen() backlog, known in userspace as SOMAXCONN.Defaults to 128.
net.core.somaxconn = 16384
#The default setting in bytes of the socket receive buffer
net.core.rmem_default = 6291456
#The default setting in bytes of the socket send buffer
net.core.wmem_default = 6291456
#The maximum socket receive buffer size which may be set by using the SO_RCVBUF socket option
net.core.rmem_max = 12582912
#The maximum socket send buffer size which may be set by using the SO_SNDBUF socket option
net.core.wmem_max = 12582912
 
 
  
apply：sysctl -p
```

##　参考案例：
- [压测介绍-go实现的压测工具【单台机器100w连接压测实战】](https://blog.csdn.net/link_km/article/details/100130784)-推荐参考
- []()