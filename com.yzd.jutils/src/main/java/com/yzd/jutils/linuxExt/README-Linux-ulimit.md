# ulimit
- [Linux资源限制配置](https://blog.csdn.net/q283614346/article/details/84489467)
```
1.
可以打开的端口范围
sysctl net.ipv4.ip_local_port_range
2.
ulimit -a
-a      显示所有限制
-c      core文件大小的上限
-d      进程数据段大小的上限
-f      shell所能创建的文件大小的上限
-m     驻留内存大小的上限
-s      堆栈大小的上限
-t      每秒可占用的CPU时间上限
-p     管道大小
-n     打开文件数的上限
-u     进程数的上限
-v     虚拟内存的上限

```
- [linux下通过/proc查看进程的limit](https://www.cnblogs.com/helloweblogic/articles/12787476.html)
```
cat /proc/14453/limits

Limit Soft Limit Hard Limit Units
Max cpu time unlimited unlimited seconds
Max file size unlimited unlimited bytes
Max data size unlimited unlimited bytes
Max stack size 8388608 unlimited bytes
Max core file size unlimited unlimited bytes
Max resident set unlimited unlimited bytes
Max processes unlimited unlimited processes
Max open files 65536 65536 files
Max locked memory 65536 65536 bytes
Max address space unlimited unlimited bytes
Max file locks unlimited unlimited locks
Max pending signals 64055 64055 signals
Max msgqueue size 819200 819200 bytes
Max nice priority 0 0
Max realtime priority 0 0
Max realtime timeout unlimited unlimited us
```


# linux 实现目标
```
基于centos7.4
support 50K+ connections
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
net.ipv4.ip_local_port_range = 1024 65535
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

## 文件打开数调优
```
sudo root user
/etc/security/limits.conf
  
root soft nofile 655350
root hard nofile 655350
* soft nofile 655350
* hard nofile 655350
  
apply: relogin
```