###　Ｓｅｎｔｉｎｅｌ流量控制方案
- [Sentinel]()
- [Sentinel-WIKI]()

#### Sentinel流量控制方案生产设计思路-byArvin-V201809211055

```
1.sentinel控制台的实时监控仅存储 5 分钟以内的数据-生产调整为30分钟或1小时
2.sentinel控制台负载均衡-nginx
3.sentinel控制台扩充内存-通过Hazelcast(分布式内存数据网格实现)，建议使用Redis
注：还是不要使用Hazelcast，建议使用Redis，java程序本身最好不要缓存任何数据，把数据放在外置存储器中，可提高稳定性。
4.sentinel控制台的实时监控数据持久化-influxDB，CrateDB（CrateDB是商业软件），ES等
注：推荐存储节点使用influxDB-byArvin-201809211138
5.grafna图表可视化
```
#### [CrateDB-nosql数据库](https://crate.io/pricing/)

```
[CrateDB-nosql数据库](https://crate.io/pricing/)
Crate：实现了数据同步、分片、缩放、复制的分布式数据存储
CrateDB是商业软件
http://hao.jobbole.com/crate/
SQL for ElasticSearch—Crate.io简介
https://blog.csdn.net/xgjianstart/article/details/55057044
cratedb已知的最大集群
为了特定的目标，已经有集群部署了数百台节点。典型的生产环境的节点可以达到100台
https://crate.io/faq/what-is-the-maximum-known-cluster-size-in-production/
```
#### 社区用户的一些扩展和解决方案
- [ Awesome Sentinel](https://github.com/alibaba/sentinel-awesome)

- [Sentinel 为 Dubbo 服务保驾护航](http://dubbo.apache.org/zh-cn/blog/sentinel-introduction-for-dubbo.html) by [Eric Zhao](https://github.com/sczyh30)
- [Sentinel一体化监控解决方案 CrateDB+Grafana](https://blog.csdn.net/huyong1990/article/details/82392386) by [Young Hu](https://github.com/YoungHu)

　GITHUB-YoungHu-Sentinel一体化监控解决方案 CrateDB+Grafana
```
https://github.com/YoungHu/Sentinel
```

####　Sentinel 控制台

```
1.
**注意:** 集群资源汇总仅支持 500 台以下的应用集群，有大概 1 - 2 秒的延时。
2.
**注意:** 实时监控仅存储 5 分钟以内的数据，如果需要持久化，需要通过调用[实时监控](https://github.com/alibaba/Sentinel/wiki/%E5%AE%9E%E6%97%B6%E7%9B%91%E6%8E%A7)接口来定制。
```
[接入 Sentinel 控制台的步骤如下（缺一不可）：](http://dubbo.apache.org/zh-cn/blog/sentinel-introduction-for-dubbo.html)
```  
按照 Sentinel 控制台文档 启动控制台
应用引入 sentinel-transport-simple-http 依赖，以便控制台可以拉取对应应用的相关信息
给应用添加相关的启动参数，启动应用。需要配置的参数有：
-Dcsp.sentinel.api.port：客户端的 port，用于上报相关信息（默认为 8719）
-Dcsp.sentinel.dashboard.server：控制台的地址
-Dproject.name：应用名称，会在控制台中显示
注意某些环境下本地运行 Dubbo 服务还需要加上 -Djava.net.preferIPv4Stack=true 参数。比如中 Service Provider 的启动参数可以配成：

-Djava.net.preferIPv4Stack=true -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=dubbo-provider-demo
``` 

#### [Sentinel 更多场景](https://github.com/alibaba/Sentinel/wiki/%E4%B8%BB%E9%A1%B5)

```
 Sentinel 的一个最简单的场景 —— 限流。
 Sentinel 还可以处理更复杂的各种情况，比如超时熔断、冷启动、请求匀速等。
 可以参考 Sentinel 文档，更多的场景等待你去挖掘！
```
#### [Sentinel 与 Hystrix 的对比](https://github.com/alibaba/Sentinel/wiki/Sentinel-%E4%B8%8E-Hystrix-%E7%9A%84%E5%AF%B9%E6%AF%94)
