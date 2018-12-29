###　kubernetes搭建
- [kubernetes搭建-超哥推荐](https://www.cnblogs.com/xiaogongzi/articles/8334516.html)
- [CentOS上手工部署kubernetes集群](https://www.cnblogs.com/xzkzzz/p/8979808.html)
- [Kubernetes安装配置与服务部署-推荐byArvin](https://blog.csdn.net/u013760355/article/details/68061976)
- [在CentOS7.3下yum安装Kubernetes1.4傻瓜教程-推荐byArvin](https://blog.csdn.net/lic95/article/details/55015284)
- [Centos7使用yum方式部署Kubernetes1.5集群](https://blog.csdn.net/bbwangj/article/details/81701300)
- [搭建docker私有仓库，建立k8s集群](https://www.cnblogs.com/djhull/archive/2016/12/02/6125130.html)
- [支撑大规模公有云的Kubernetes改进与优化 (3)](https://www.cnblogs.com/menkeyi/p/7279236.html)

### 
- [kubernetes的eviction机制](https://blog.csdn.net/redenval/article/details/84237654)
- [解决kubectl create -f 如下报错](https://blog.csdn.net/weixin_39963973/article/details/80737055)

#### -查看日志
```
tailf /var/log/message
journalctl -u kubelet -r
journalctl -u docker -r
---------------------
PS:journalctl工具是CentOS-7才有的日志查看工具
```

#### kubernetes安装

- 设置主机名
```
hostnamectl --static set-hostname  master
hostnamectl --static set-hostname  node1
执行完成，需要重新启动机器
reboot
```
- 每台主机设置host
```
192.168.1.230 master 
192.168.1.230 etcd 
192.168.1.226 node1
------------------------
echo '192.168.1.230 master 
192.168.1.230 etcd 
192.168.1.226 node1' >> /etc/hosts
```
- 关闭每台机器上防火墙
```
systemctl disable firewalld.service
systemctl stop firewalld.service
```
- 永久关闭SELinux 
```
修改/etc/selinux/config 
SELINUX=disabled
```
- 测试机器之间是否连通
```
ping master
ping etcd
ping node1
```
- 安装和配置etcd
```
1.安装
yum install etcd -y
------------------------
2.查看配置
grep -v '^#' /etc/etcd/etcd.conf
------------------------
3.修改/etc/etcd/etcd.conf中的部分属性 
ETCD_LISTEN_CLIENT_URLS="http://0.0.0.0:2379" 
ETCD_ADVERTISE_CLIENT_URLS="http://etcd:2379" 
PS：其中etcd表示etcd服务器主机名
------------------------
4.运行etcd
systemctl start etcd 
systemctl enable etcd
------------------------
5.检测是否成功
netstat -ntpl
------------------------
6.检查etcd cluster状态
etcdctl cluster-health
------------------------
7.检查etcd集群成员列表，这里只有一台
etcdctl member list
------------------------
```
- 安装和配置kubernetes-master
```
1.安装
yum install kubernetes-master
------------------------
2.
grep -v '^#' /etc/kubernetes/config
修改/etc/kubernetes/config

KUBE_LOGTOSTDERR="--logtostderr=true"
KUBE_LOG_LEVEL="--v=0"
KUBE_ALLOW_PRIV="--allow-privileged=false"
KUBE_MASTER="--master=http://master:8080"
------------------------
3.
grep -v '^#' /etc/kubernetes/apiserver
修改/etc/kubernetes/apiserver 
KUBE_API_ADDRESS="--insecure-bind-address=0.0.0.0" 
KUBE_ETCD_SERVERS="--etcd-servers=http://etcd:2379" 
KUBE_ADMISSION_CONTROL="--admission-control=NamespaceLifecycle,NamespaceExists,LimitRanger,ResourceQuota" 
PS：测试时需要把KUBE_ADMISSION_CONTROL中的SecurityContextDeny和ServiceAccount去掉，这是权限相关的
------------------------
4.
grep -v '^#' /etc/kubernetes/controller-manager
KUBE_CONTROLLER_MANAGER_ARGS=""
------------------------
5.
grep -v '^#' /etc/kubernetes/scheduler
KUBE_SCHEDULER_ARGS="--address=0.0.0.0"
------------------------
6.设置开机启动并启动master服务
systemctl enable kube-apiserver kube-scheduler kube-controller-manager 
systemctl start kube-apiserver kube-scheduler kube-controller-manager
------------------------
7.
curl http://master:8080
------------------------

```
- 配置网络
```
1.配置etcd
etcdctl set /atomic.io/network/config '{"Network": "10.255.0.0/16"}'
2.etcdctl get /atomic.io/network/config
{"Network": "10.255.0.0/16"}
PS：
```
- 配置node1网络，本实例采用flannel方式来配置
```
1.安装 flannel
yum install -y flannel
--------------------
[root@centos-7 ~]# grep -v '^#' /etc/sysconfig/flanneld
FLANNEL_ETCD_ENDPOINTS="http://etcd:2379"
FLANNEL_ETCD_PREFIX="/atomic.io/network"
------------------------
1.启动
systemctl start flanneld
------------------------
grep -v '^#' /etc/sysconfig/flanneld
systemctl restart flanneld
------------------------
参考：
etcd安装和所遇到的坑
http://www.cnblogs.com/devilwind/p/8880677.html
```

- 配置docker网络
```
PS:必须先安装flannel的网络，然后再安装docker
yum install -y docker
systemctl start docker
设置完 flanneld的网络覆盖后，
再重启docker就可以了
ip a
查看网终是否正常

------------------------
暂时不使用
Docker修改Docker0网桥默认网段
http://blog.51cto.com/lisea/1940023
------------------------
暂时不使用
[root@centos-7 ~]# cat /etc/docker/daemon.json
{"bip": "10.255.0.0/16"}
------------------------
```
- node1网络
```

[root@node1 ~]# ip a
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: eth0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether fa:38:1b:f0:11:00 brd ff:ff:ff:ff:ff:ff
    inet 192.168.1.243/24 brd 192.168.1.255 scope global noprefixroute eth0
       valid_lft forever preferred_lft forever
    inet6 fe80::f838:1bff:fef0:1100/64 scope link 
       valid_lft forever preferred_lft forever
3: flannel0: <POINTOPOINT,MULTICAST,NOARP,UP,LOWER_UP> mtu 1472 qdisc pfifo_fast state UNKNOWN group default qlen 500
    link/none 
    inet 10.255.61.0/16 scope global flannel0
       valid_lft forever preferred_lft forever
    inet6 fe80::b03:627e:6a68:b1ce/64 scope link flags 800 
       valid_lft forever preferred_lft forever
4: docker0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default 
    link/ether 02:42:17:37:e6:df brd ff:ff:ff:ff:ff:ff
    inet 10.255.61.1/24 scope global docker0
       valid_lft forever preferred_lft forever

PS:flannel的覆盖网络配置成功
------------------------

```

- 配置node1节点服务器
```
1.安装
yum install -y kubernetes-node
------------------------
[root@node1 ~]# grep -v '^#' /etc/kubernetes/config
KUBE_LOGTOSTDERR="--logtostderr=true"
KUBE_LOG_LEVEL="--v=0"
KUBE_ALLOW_PRIV="--allow-privileged=false"
KUBE_MASTER="--master=http://master:8080"
------------------------
[root@node1 ~]# grep -v '^#' /etc/kubernetes/proxy
KUBE_PROXY_ARGS=""
------------------------
[root@node1 ~]# grep -v '^#' /etc/kubernetes/kubelet
KUBELET_ADDRESS="--address=127.0.0.1"
KUBELET_HOSTNAME="--hostname-override=node1"
KUBELET_API_SERVER="--api-servers=http://master:8080"
KUBELET_POD_INFRA_CONTAINER="--pod-infra-container-image=registry.access.redhat.com/rhel7/pod-infrastructure:latest"
KUBELET_ARGS=""
------------------------
systemctl start kube-proxy
systemctl start kubelet
------------------------
systemctl status flanneld
systemctl status docker
systemctl status kube-proxy
systemctl status kubelet

```
- master主机上查看node1节点
```
[root@master ~]# kubectl get nodes
NAME      STATUS    AGE
node1     Ready     1d
------------------------
PS:node1节点已成功，其他node节点都可以参考node1的方法
```
- 解决kubernetes启动容器时，容器一直是ContainerCreating不能running
```
解决kubernetes启动容器时，容器一直是ContainerCreating不能running
https://blog.csdn.net/gezilan/article/details/80011905

docker pull registry.access.redhat.com/rhel7/pod-infrastructure:latest

1.
Package python-rhsm-certificates-1.19.10-1.el7_4.x86_64 is obsoleted by subscription-manager-rhsm-certificates-1.21.10-3.el7.centos.x86_64 which is already installed
Nothing to do
解决方法：
rm -rf /etc/docker/certs.d/registry.access.redhat.com/redhat-ca.crt

docker pull registry.access.redhat.com/rhel7/pod-infrastructure:latest

```
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

- 3.清除iptables规则
```
第三个要点是要清除iptables规则..这个是最新遇到的坑,什么检查都对,但是ping就是不通,使用如下命令执行删除防火墙规则,注意不是关闭防火墙就万事大吉哦,我以前也这么想的,不清除防火墙规则关闭是没用的,因为Kubernetes依赖iptables所以是不会被关闭的

iptables -P INPUT ACCEPT
iptables -P FORWARD ACCEPT
iptables -F
iptables -L -n
--------------------- 
```