## linux 大量的TIME_WAIT解决办法
- []()
- [linux 大量的TIME_WAIT解决办法](https://www.cnblogs.com/softidea/p/6062147.html)
```
统计在一台前端机上高峰时间TCP连接的情况，统计命令：
netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'

通过调整内核参数解决
vi /etc/sysctl.conf

编辑文件，加入以下内容：
net.ipv4.tcp_syncookies = 1
net.ipv4.tcp_tw_reuse = 1
net.ipv4.tcp_tw_recycle = 1
net.ipv4.tcp_fin_timeout = 30
 
然后执行/sbin/sysctl -p让参数生效。
 
net.ipv4.tcp_syncookies = 1表示开启SYN Cookies。当出现SYN等待队列溢出时，启用cookies来处理，可防范少量SYN攻击，默认为0，表示关闭；
net.ipv4.tcp_tw_reuse = 1表示开启重用。允许将TIME-WAIT sockets重新用于新的TCP连接，默认为0，表示关闭；
net.ipv4.tcp_tw_recycle = 1表示开启TCP连接中TIME-WAIT sockets的快速回收，默认为0，表示关闭。
net.ipv4.tcp_fin_timeout修改系統默认的TIMEOUT时间
```

## [Window-java netty IO非阻塞压测客户端](https://www.jianshu.com/p/edeee9e2a57a)
```
windows客户端
打开注册表：regedit HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\ Services\TCPIP\Parameters
新建 DWORD值，name：TcpTimedWaitDelay，value：0（十进制） –> 设置为0
新建 DWORD值，name：MaxUserPort，value：65534（十进制） –> 设置最大连接数65534 重启系统

```

## Keepalive
```
netstat -ntp --timers |grep ES |grep 8888
//
tcp6       0      0 172.20.132.85:8888      172.20.134.79:58490     ESTABLISHED 28664/java           keepalive (192.79/0/0)
```