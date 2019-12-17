## 监控神器-普罗米修斯Prometheus
- [监控神器-普罗米修斯Prometheus的安装](https://blog.csdn.net/csolo/article/details/82460539)
- [监控神器普罗米修斯Prometheus安装配置](https://blog.csdn.net/ywd1992/article/details/85989259)

## Prometheus架构图
- [使用Prometheus+grafana打造高逼格监控平台](https://blog.csdn.net/weixin_33738982/article/details/89903432)
- []()

### 目标
- 读取Prometheus采集的数据，然后把数据放在influxdb中，再通过grafana进行数据展示

### Prometheus数据结果提取
```
1.
 过滤注释信息
 #[^\n\r]+
 # TYPE java_lang_MemoryPool_PeakUsage_committed untyped
 2.
 提取指标信息
 [\d\.]+$
 提取指标-2019-07-01-1201-byArvin
 [\d.E]+[\n\r]
 Tomcat_ProtocolHandler_maxExtensionSize{port="2001",} 8192.0
 3.
 java进行正则数据提取
 
```

### 监控场景分析
```
Zabbix 主要是服务器方面监控（磁盘、带宽等）；
pinpoint、Skywalking、CAT主要是做分布式链路跟踪+应用性能，其中分布式链路跟踪是主要特点；
如果你侧重应用应用性能（如：redis,tomcat,mysql,jvm等）可以使用Prometheus+influxdb+grafana
```
### ==github项目
- [spring-boot-metrics-to-influxdb-(dev-yzd)-springboot监控](https://github.com/yaozd/spring-boot-metrics-to-influxdb.git)
