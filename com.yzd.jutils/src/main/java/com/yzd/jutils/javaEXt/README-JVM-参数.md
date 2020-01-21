## 参考（HAla）
jvm参数
```
-Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1899 -Dcom.sun.management.jmxremote.ssl=false  -Duser.timezone=GMT+08  -Xms2048m -Xmx8192m -Xmn1024m -Xss512K -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m  -XX:+UseCompressedOops  -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -Xloggc:/home/logs/HeapDump_Gc/${HOSTNAME}-gc.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/logs/HeapDump_Gc/${HOSTNAME}.hprof -Dfile.encoding=UTF-8

```
### [-Xms和-Xmx的值设置成一样 Java不断地吃内存](https://blog.csdn.net/Truong/article/details/71126868)

> 同一jdk的gc策略也有多种，不能一概而论
  另外，在sun的jdk下，Xms和Xmx设置一样，可以减轻伸缩堆大小带来的压力，但在ibm的jdk下面，设置为一样会增大堆碎片产生的几率，并且这种负面影响足以抵消前者产生的益处 

##　参数说明：
- [【JAVA学习】“-Xmx1024m -Xms1024m -Xmn512m -Xss256k”——Java运行参数(转)](https://blog.csdn.net/a503921892/article/details/39048889)

- [JVM调优总结 -Xms -Xmx -Xmn -Xss等参考详解](https://www.cnblogs.com/likehua/p/3369823.html)-推荐参考-byArvin

- [命令java -server什么意思](https://zhidao.baidu.com/question/454583244.html)
    ```
    JVM的参数配置
    分别是 服务器模式 和客户端模式
    因为JVM里面很多机制，在这两种模式下是不同的，
    比如垃圾回收机制，客户端模式下，要求的是用户体验流程，无明显滞留感（就是没有卡的现象）
    而服务端，要求的是吞吐量，就是单位时间内执行的代码要求越多越好，
    ```
- JVM参数调优001
    - [JVM参数调优001](https://blog.csdn.net/iteye_254/article/details/82519144)
    - [JVM参数](https://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html)
    - [JVM系列三:JVM参数设置、分析](https://www.cnblogs.com/redcreen/archive/2011/05/04/2037057.html) -首要参考byArvin 参数说明比较详细
    - [快速上手jvm调优：GC调优思路及参数设置](https://blog.csdn.net/wk52525/article/details/94899432)