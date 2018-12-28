
- 1.[linux系统打开路由转发功能](https://blog.csdn.net/z1143709608/article/details/52694479)
```
1.一次性配置，重启后需再次打开： 
将文件/proc/sys/net/ipv4/ip_forward值改为1

  echo "1">/proc/sys/net/ipv4/ip_forward
1
2.永久配置 
将文件/etc/sysctl.conf里面的net.ipv4.ip_forward=1的注释去除

sysctl -p
1
让上述配置生效
--------------------- 
原文：https://blog.csdn.net/z1143709608/article/details/52694479 
```
- 2.[Kubernetes使用flannel做跨主机通讯的注意要点](https://blog.csdn.net/onlonely/article/details/80265004)
```
第三个要点是要清除iptables规则..这个是最新遇到的坑,什么检查都对,但是ping就是不通,使用如下命令执行删除防火墙规则,注意不是关闭防火墙就万事大吉哦,我以前也这么想的,不清除防火墙规则关闭是没用的,因为Kubernetes依赖iptables所以是不会被关闭的

iptables -P INPUT ACCEPT
iptables -P FORWARD ACCEPT
iptables -F
iptables -L -n
--------------------- 
```
- 3.清除iptables规则
```
第三个要点是要清除iptables规则..这个是最新遇到的坑,什么检查都对,但是ping就是不通,使用如下命令执行删除防火墙规则,注意不是关闭防火墙就万事大吉哦,我以前也这么想的,不清除防火墙规则关闭是没用的,因为Kubernetes依赖iptables所以是不会被关闭的

iptables -P INPUT ACCEPT
iptables -P FORWARD ACCEPT
iptables -F
iptables -L -n
--------------------- 
```