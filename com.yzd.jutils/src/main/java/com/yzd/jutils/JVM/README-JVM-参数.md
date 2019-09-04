## 参考（HAla）
jvm参数
```
-Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.port=1899 -Dcom.sun.management.jmxremote.ssl=false  -Duser.timezone=GMT+08  -Xms2048m -Xmx8192m -Xmn1024m -Xss512K -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m  -XX:+UseCompressedOops  -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -Xloggc:/home/logs/HeapDump_Gc/${HOSTNAME}-gc.log -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/home/logs/HeapDump_Gc/${HOSTNAME}.hprof -Dfile.encoding=UTF-8

```

##　参数说明：
- [【JAVA学习】“-Xmx1024m -Xms1024m -Xmn512m -Xss256k”——Java运行参数(转)](https://blog.csdn.net/a503921892/article/details/39048889)