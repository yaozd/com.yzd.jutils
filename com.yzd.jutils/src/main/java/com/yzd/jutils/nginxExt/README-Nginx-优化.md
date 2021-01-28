## 1.Nginx优化参考：
- [nginx 优化](https://blog.csdn.net/tianyaxingge/article/details/8491233)
- [nginx连接数优化](https://www.cnblogs.com/vicowong/p/9560967.html) -推荐参考byArvin
- [ab压力测试 和 nginx 配置优化 及 用户打开的最大进程数](https://blog.csdn.net/wudinaniya/article/details/86064797)  -推荐参考byArvin
- [Nginx的一些优化 突破十万并发（转）](https://www.cnblogs.com/vicowong/p/11663667.html)
- [缓解DDoS && cc 的最佳Linux内核设置 (转)](https://www.cnblogs.com/vicowong/p/11748142.html)

## 2.Nginx策略设置
- [Nginx限制访问次数和并发数](https://blog.51cto.com/13293070/2453274?source=dra)

## [Tengine & Nginx性能测试](http://tengine.taobao.org/document_cn/benchmark_cn.html)
```
系统配置:
net.ipv4.tcp_mem = 3097431 4129911 6194862
net.ipv4.tcp_rmem = 4096 87380 6291456
net.ipv4.tcp_wmem = 4096 65536 4194304
net.ipv4.tcp_max_tw_buckets = 262144
net.ipv4.tcp_tw_recycle = 0
net.ipv4.tcp_tw_reuse  = 1
net.ipv4.tcp_syncookies  = 1
net.ipv4.tcp_fin_timeout = 15
net.ipv4.ip_local_port_range = 1024 65535
net.ipv4.tcp_max_syn_backlog = 65535
net.core.somaxconn  = 65535
net.core.netdev_max_backlog  = 200000
```