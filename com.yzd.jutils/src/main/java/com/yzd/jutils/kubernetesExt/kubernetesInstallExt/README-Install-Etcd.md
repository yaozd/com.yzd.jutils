### Etcd安装

```
tar zxvf etcd-v3.3.10-linux-amd64.tar.gz
cd etcd-v3.3.10-linux-amd64
cp etcd /usr/bin/etcd
cp etcdctl /usr/bin/etcdctl
etcd -name etcd -data-dir /var/lib/etcd -listen-client-urls http://0.0.0.0:2379,http://0.0.0.0:4001 -advertise-client-urls http://0.0.0.0:2379,http://0.0.0.0:4001 >> /var/log/etcd.log 2>&1 &
----------------------------------------
验证：
netstat -ntpl
etcdctl -C http://etcdmaster:4001 cluster-health

```
参考：
632180 Kubernetes实战 ,吴龙辉 ,P272.pdf