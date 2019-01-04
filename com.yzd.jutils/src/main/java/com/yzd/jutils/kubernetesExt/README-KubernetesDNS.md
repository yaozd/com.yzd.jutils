### -DNS分类
1. KuberDNS(SkyDNS),解决的问题：service的发现问题
2. dnsmasq,解决的问题：主机的发现问题


- [配置k8s dns](https://www.cnblogs.com/menkeyi/p/7120292.html)
- [部署kubernetes dns 服务](https://www.jianshu.com/p/8fb1b1265ec8)
- [k8s入门系列之扩展组件(一)DNS安装篇-推荐by田亮](https://www.cnblogs.com/xkops/p/6232392.html)
- [k8s入门系列之扩展组件(二)kube-ui安装篇](https://www.cnblogs.com/xkops/p/6233470.html)
> etcd配置
```
[root@master ~]# etcdctl mk /skydns/config '{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}'
{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}
[root@master ~]# etcdctl get /skydns/config
{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}

```
> [kube2sky Waiting for service: default/kubernetes timeout](https://github.com/kubernetes/kubernetes/issues/24842)
```
不要把etcd的监听地址配置为：http://0.0.0.0:2379 
------------
Thanks for your answer @thockin I found the problem through debug the kube2sky source code. something is wrong with my etcd configuration. my etcd configuration is like this ：
./etcd --listen-client-urls=http://0.0.0.0:2379  --listen-peer-urls=http://0.0.0.0:2378 --advertise-client-urls=ht
the machines will get the value like this http://0.0.0.0:2379 and the http-client will use this address to post value ，however the etcd-server deploys on the host its ip is 192.168.46.40 .
Then I change the configuration like this
./etcd --listen-client-urls=http://192.168.46.40:2379  --listen-peer-urls=http://192.168.46.40:2378 --advertise-client-u
```
> 解决docker无法正常拉取google的镜像问题
```
http://docker.gaoxiaobang.com/
可以通过“docker.gaoxiaobang.com”上查找镜像，把google的镜像替换为docker.gaoxiaobang的镜像
eg:
 image: docker.gaoxiaobang.com/kubernetes/etcd-amd64:2.2.1  
 image: docker.gaoxiaobang.com/kubernetes/kube2sky:1.14
 image: docker.gaoxiaobang.com/kubernetes/exechealthz:1.0
--------------
参考：
k8s入门系列之扩展组件(一)DNS安装篇--目前田亮已经测试成功
https://www.cnblogs.com/xkops/p/6232392.html
```