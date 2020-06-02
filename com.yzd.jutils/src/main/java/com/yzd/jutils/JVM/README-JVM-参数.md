## 参考（HAla）
jvm参数
```
-Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1899 -Dcom.sun.management.jmxremote.ssl=false  -Duser.timezone=GMT+08  -Xms2048m -Xmx8192m -Xmn1024m -Xss512K -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m  -XX:+UseCompressedOops  -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -Xloggc:/home/logs/HeapDump_Gc/${HOSTNAME}-gc.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/logs/HeapDump_Gc/${HOSTNAME}.hprof -Dfile.encoding=UTF-8
//
-Dcom.sun.management.jmxremote.authenticate=false 
-Dcom.sun.management.jmxremote.port=1899 
-Dcom.sun.management.jmxremote.ssl=false  
-Duser.timezone=GMT+08  
-Xms2048m 
-Xmx8192m 
-Xmn1024m 
-Xss512K 
-XX:MetaspaceSize=128m 
-XX:MaxMetaspaceSize=512m  
-XX:+UseCompressedOops  
-XX:+UseConcMarkSweepGC 
-XX:CMSInitiatingOccupancyFraction=75 
-XX:+UseCMSInitiatingOccupancyOnly 
-XX:MaxTenuringThreshold=6 
-XX:+ExplicitGCInvokesConcurrent 
-XX:+ParallelRefProcEnabled 
-Xloggc:/home/logs/HeapDump_Gc/${HOSTNAME}-gc.log 
-XX:+PrintGCDateStamps 
-XX:+PrintGCDetails 
-XX:+PrintGCApplicationStoppedTime 
-XX:+HeapDumpOnOutOfMemoryError 
-XX:HeapDumpPath=/home/logs/HeapDump_Gc/${HOSTNAME}.hprof 
-Dfile.encoding=UTF-8

```
## JVM配置参数如下：
- [Benchmark性能测试报告](https://github.com/wangzheng0822/ratelimiter4j/wiki/2.-Benchmark%E6%80%A7%E8%83%BD%E6%B5%8B%E8%AF%95%E6%8A%A5%E5%91%8A)-google工程师
```
-server -Xmx4g -Xms4g -Xmn256m -XX:PermSize=256m -Xss256k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70 -XX:+UseFastAccessorMethods -XX:+PrintGC -XX:+PrintGCDetails  -XX:+PrintGCDateStamps -Xloggc:./gc.log
//
-server
-Xmx4g 
-Xms4g 
-Xmn256m 
-XX:PermSize=256m 
-Xss256k 
-XX:+DisableExplicitGC 
-XX:+UseConcMarkSweepGC 
-XX:+CMSParallelRemarkEnabled 
-XX:+UseCMSCompactAtFullCollection 
-XX:+UseCMSInitiatingOccupancyOnly 
-XX:CMSInitiatingOccupancyFraction=70 
-XX:+UseFastAccessorMethods 
-XX:+PrintGC 
-XX:+PrintGCDetails  
-XX:+PrintGCDateStamps 
-Xloggc:./gc.log
```
## Container-JVM配置参数如下
```
#!/bin/sh
nohup \
java -server -Xms4G -Xmx4G -XX:+UseConcMarkSweepGC \
-Dlogging.config=/home/apirouter/config/log4j2-spring.xml \
-Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector \
-XX:+HeapDumpOnOutOfMemoryError \
-XX:HeapDumpPath=./java_pid%p.hprof \
-Dspring.config.location=file:/home/apirouter/config/application.yaml  \
-jar  /home//apirouter/yzd-container-0.0.1-SNAPSHOT.jar &

```

### [-Xms和-Xmx的值设置成一样 Java不断地吃内存](https://blog.csdn.net/Truong/article/details/71126868)

> 同一jdk的gc策略也有多种，不能一概而论
  另外，在sun的jdk下，Xms和Xmx设置一样，可以减轻伸缩堆大小带来的压力，但在ibm的jdk下面，设置为一样会增大堆碎片产生的几率，并且这种负面影响足以抵消前者产生的益处 

##　参数说明：
- [【JAVA学习】“-Xmx1024m -Xms1024m -Xmn512m -Xss256k”——Java运行参数(转)](https://blog.csdn.net/a503921892/article/details/39048889)

- [JVM调优总结 -Xms -Xmx -Xmn -Xss等参考详解](https://www.cnblogs.com/likehua/p/3369823.html)-推荐参考-byArvin