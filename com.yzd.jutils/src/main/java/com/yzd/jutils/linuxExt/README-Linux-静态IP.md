## 静态IP
- [linux相关知识（一）在VirtualBox中配置Linux版虚拟机的静态地址](https://blog.51cto.com/12721734/2419550)
```
root:
cd /etc/sysconfig/network-scripts/
vim ifcfg-enp0s3
//
BOOTPROTO=static
IPADDR=192.168.56.111
//
systemctl restart network
```