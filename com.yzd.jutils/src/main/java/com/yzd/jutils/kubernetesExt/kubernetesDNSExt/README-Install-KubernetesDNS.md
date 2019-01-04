
- [kubernetes搭建-超哥推荐-里面DNS简单实用](https://www.cnblogs.com/xiaogongzi/articles/8334516.html)

> etcd配置
```
[root@master ~]# etcdctl mk /skydns/config '{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}'
{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}
[root@master ~]# etcdctl get /skydns/config
{"dns-addr":"10.254.10.2:53","ttl":3600,"domain":"sky."}

```