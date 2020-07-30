# nacos

## 官网
- [https://nacos.io/](https://nacos.io/)

## 性能测试

> TPS | RT(响应时长)

- [深度对比三种主流微服务配置中心](http://dockone.io/article/8767) -Apollo与Nacos 推荐参考byArvin
```
目前市面上用的比较多的配置中心有（按开源时间排序）：
Disconf，2014年7月百度开源的配置管理中心，同样具备配置的管理能力，不过目前已经不维护了，最近的一次提交是两年前了。
Spring Cloud Config，2014年9月开源，Spring Cloud 生态组件，可以和Spring Cloud体系无缝整合。
Apollo，2016年5月，携程开源的配置管理中心，具备规范的权限、流程治理等特性。
Nacos，2018年6月，阿里开源的配置中心，也可以做DNS和RPC的服务发现。
```

- [Nacos服务配置性能测试报告](https://nacos.io/zh-cn/docs/nacos-config-benchmark.html)
```
设置启动参数
/opt/taobao/java/bin/java -server -Xms4g -Xmx4g -Xmn2g 
-XX:MetaspaceSize=128m 
-XX:MaxMetaspaceSize=320m 
-Xdebug 
-Xrunjdwp:transport=dt_socket,address=9555,server=y,suspend=n 
-XX:+UseConcMarkSweepGC 
-XX:+UseCMSCompactAtFullCollection 
-XX:CMSInitiatingOccupancyFraction=70 
-XX:+CMSParallelRemarkEnabled -XX:SoftRefLRUPolicyMSPerMB=0 
-XX:+CMSClassUnloadingEnabled -XX:SurvivorRatio=8 
-XX:-UseParNewGC -verbose:gc -Xloggc:/home/admin/nacos/logs/nacos_gc.log 
-XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCApplicationStoppedTime 
-XX:+PrintAdaptiveSizePolicy -Dnacos.home=/home/admin/nacos -XX:-OmitStackTraceInFastThrow 
-XX:-UseLargePages -jar /home/admin/nacos/target/nacos-server.jar 
--spring.config.location=classpath:/,classpath:/config/,file:./,file:./config/,file:/home/admin/nacos/conf/
```

- [Nacos服务发现性能测试报告](https://nacos.io/zh-cn/docs/nacos-naming-benchmark.html)
```
设置启动参数
/opt/taobao/java/bin/java	 -server
-Xms20g
-Xmx20g
-Xmn10g	 -XX:MetaspaceSize=128m
-XX:MaxMetaspaceSize=320m
-XX:-OmitStackTraceInFastThrow
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=/home/admin/nacos/logs/java_heapdump.hprof
-XX:-UseLargePages
-Djava.ext.dirs=/opt/taobao/java/jre/lib/ext:/opt/taobao/java/lib/ext:/home/admin/nacos/plugi
ns/cmdb:/home/admin/nacos/plugins/mysql	 -Xloggc:/home/admin/nacos/logs/nacos_gc.log
-verbose:gc	 -XX:+PrintGCDetails	 -XX:+PrintGCDateStamps	 -XX:+PrintGCTimeStamps
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=10	 -XX:GCLogFileSize=100M	 -Xdebug
-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000
-Dnacos.home=/home/admin/nacos	 -jar	 /home/admin/nacos/target/nacos-server.jar
--spring.config.location=classpath:/,classpath:/config/,file:./,file:./config/,file:/home/admin/naco
s/conf/	--logging.config=/home/admin/nacos/conf/nacos-logback.xml	nacos.nacos
```

## 同类型组件对比
- []()
- [微服务：注册中心ZooKeeper、Eureka、Consul 、Nacos对比](https://blog.csdn.net/fly910905/article/details/100023415)
- [阿里启动新项目：Nacos，比 Eureka 更强](https://blog.csdn.net/youanyyou/article/details/85774066)
- []()

## 生产高可用架构方案
- [Nacos生产高可用架构方案](https://blog.csdn.net/zwjzqqb/article/details/103022348)

## 同步
- [NacosSync 介绍](https://nacos.io/zh-cn/docs/nacos-sync.html)
- [zookeeper到nacos的迁移实践](https://juejin.im/post/5ecb4c83f265da77057e2716)

## 为什么选Nacos 
- [阿里的dubbo注册中心要放弃zookeeper, 而用Nacos](https://my.oschina.net/u/867417/blog/1865971)

## 示例
### Spring boot
- [SpringBoot使用Nacos作为配置中心服务和服务注册中心](https://blog.csdn.net/zjcjava/article/details/88316190)
- [Nacos地图](https://blog.csdn.net/zjcjava/article/details/88316190)


### DNS
- [Docker部署Nacos & CoreDNS 实现动态DNS功能](https://www.jianshu.com/p/fa2ede64fd64)

### grpc nacos
- [nacos注册grpc并实现负载均衡调用](https://blog.csdn.net/qq_37362891/article/details/103920438)
- [Springboot gRpc整合nacos](https://blog.csdn.net/qq_35875671/article/details/106012835)