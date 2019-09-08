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