### -DNS分类
1. KuberDNS(SkyDNS),解决的问题：service的发现问题
2. dnsmasq,解决的问题：主机的发现问题


- [配置k8s dns](https://www.cnblogs.com/menkeyi/p/7120292.html)
- [部署kubernetes dns 服务](https://www.jianshu.com/p/8fb1b1265ec8)

> etcd配置
```
[root@master ~]# etcdctl mk /skydns/config '{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}'
{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}
[root@master ~]# etcdctl get /skydns/config
{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}

```
> 