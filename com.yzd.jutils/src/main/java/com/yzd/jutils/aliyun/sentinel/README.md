###　Ｓｅｎｔｉｎｅｌ流量控制方案
- [Sentinel](https://github.com/alibaba/Sentinel)
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
### 教程
- [SpringBoot 2.0 + 阿里巴巴 Sentinel 动态限流实战](https://www.cnblogs.com/smallSevens/p/11531534.html)
- [SpringBoot 2.0 + InfluxDB+ Sentinel 实时监控数据存储](https://www.cnblogs.com/smallSevens/p/11576263.html)
- [SpringBoot 2.0 + Nacos + Sentinel 流控规则集中存储](https://www.cnblogs.com/smallSevens/p/11553695.html)

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

> [Sentinel 控制台-帮助文档](https://github.com/alibaba/Sentinel/wiki/控制台)
```
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar

java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.4.2.jar

java -jar  -Dserver.port=8080 -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard .\sentinel-dashboard-1.4.2.jar
----------------
sentinel-dashboard-1.4.2.jar=》百度云=》软件开发-JAVA=》J-S-Sentinel-流量控制=》sentinel-dashboard-1.4.2-bak-2019-02-27-1156.zip
```
> [sentinel控制台监控数据持久化【InfluxDB】-代码实现-github-地址](https://github.com/yaozd/Sentinel/tree/dev-yzd)
```
分支：dev-yzd
https://github.com/yaozd/Sentinel/tree/dev-yzd
https://github.com/yaozd/Sentinel/tree/dev-yzd
参考：
sentinel控制台监控数据持久化【InfluxDB】-推荐参考byArvin
https://www.cnblogs.com/cdfive2018/p/9914838.html
```

[接入 Sentinel 控制台的步骤如下（缺一不可）：](http://dubbo.apache.org/zh-cn/blog/sentinel-introduction-for-dubbo.html)
```  
按照 Sentinel 控制台文档 启动控制台
PS:例如控制台的启动
java -jar -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard .\sentinel-dashboard-1.4.2.jar
--------------------
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

#### [Spring Cloud Alibaba Sentinel 的所有配置信息](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Sentinel)

> 
[Sentinel-Wiki-中文](https://github.com/alibaba/Sentinel/wiki/主流框架的适配)<br>
[Sentinel-如何使用](https://github.com/alibaba/Sentinel/wiki/如何使用)<br>
[Spring Cloud Alibaba Sentinel 的所有配置信息](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/Sentinel)<br>
[Sentinel-动态规则扩展](https://github.com/alibaba/Sentinel/wiki/动态规则扩展)<br>
[Sentinel-主流框架的适配](https://github.com/alibaba/Sentinel/wiki/主流框架的适配)

> 示例-01
```
按照 Sentinel 控制台文档 启动控制台
PS:例如控制台的启动
java -jar -Dcsp.sentinel.api.port=8720 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard .\sentinel-dashboard-1.4.2.jar
--------------------

```

>
[自定义限流处理逻辑](https://blog.csdn.net/qq_36081696/article/details/86128487)<br>
[利用Spring Cloud Aalibaba Sentinel完成 Spring Cloud 应用的限流管理示例](https://blog.csdn.net/qq_36081696/article/details/86128487)<br>
[Dubbo使用Sentinel来对服务进行降级与限流-推荐参考byArvin](https://blog.csdn.net/pwh19920920/article/details/85252203)<br>
[sentinel-dubbo](https://github.com/pwh19920920/sentinel-dubbo)<br>
[sentinel-dubbo-备份yzd](https://github.com/yaozd/sentinel-dubbo)<br>
[sentinel控制台监控数据持久化【InfluxDB】-推荐参考byArvin](https://www.cnblogs.com/cdfive2018/p/9914838.html)<br>
[cdfive/Sentinel-数据持久化InfluxDB](https://github.com/cdfive/Sentinel/tree/winxuan_develop/sentinel-dashboard)<br>
[cdfive/Sentinel-数据持久化InfluxDB-备份yzd](https://github.com/yaozd/Sentinel)<br>

- 参考使用案例
    - [阿里 sentinel 在项目中的使用](https://blog.csdn.net/tang_jian_dong/article/details/86759714)
    - [阿里开源Sentinel流控框架基本介绍与简单使用](https://blog.csdn.net/wk52525/article/details/103729703)

