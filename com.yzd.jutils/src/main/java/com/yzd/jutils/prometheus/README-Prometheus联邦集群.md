## Prometheus联邦集群
- [prometheus学习4：多集群高可用](https://blog.csdn.net/login_sonata/article/details/89891844)
```
远程存储解决了prometheus数据持久化和可扩展性的问题，联邦解决了单台prometheus数据采集任务量过大的问题。它们的组合可以作为高可用方案。
```

## Prometheus数据采集能力不足解决方案
```
Prometheus开启推送模式
可以专门开发有一组抓取程序，定时间推送到prometheus上。
```