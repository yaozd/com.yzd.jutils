# jvm 参数：
- [https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html)
- [http://51gjie.com/java/551.html](http://51gjie.com/java/551.html)
- [Java JVM 参数设置大全](http://51gjie.com/java/551.html)

## 关键词
- [jvm中的新生代Eden和survivor区](https://blog.csdn.net/wy5612087/article/details/52369677)
- [MaxTenuringThreshold](https://blog.csdn.net/ning0323/article/details/75675448) -CMS中默认值为6
```
参数：-XX:MaxTenuringThreshold
含义：
Sets the maximum tenuring threshold for use in adaptive GC sizing.
The largest value is 15.
The default value is 15 for the parallel (throughput) collector, and 6 for the CMS collector.
在可自动调整对象晋升老年代年龄阈值的GC中，该参数用于设置上述年龄阈值的最大值
参数值最大为15
Parallel Scavenge中默认值为15，CMS中默认值为6，G1中默认值为15
默认值：
```
- []()

1. 使用Survivor空间不足的场景
- grpc 长连接模式参数
```
-Xms6g
-Xmx6g
-Xmn2g
-XX:SurvivorRatio=5
-XX:+UseConcMarkSweepGC
-Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
-Dlogging.config=D:/C-Code/C-Code-Hualala/hyperspace-V7/hyperspace-container/src/test/resources/log4j2-spring.xml
-Dspring.config.location=file:D:/C-Code/C-Code-Hualala/hyperspace-V7/hyperspace-container/src/test/resources/application-empty.yaml
-XX:TieredStopAtLevel=1
-Xverify:none
-Dspring.output.ansi.enabled=always
-Dcom.sun.management.jmxremote
-Dspring.jmx.enabled=true
-Dspring.liveBeansView.mbeanDomain
-Dspring.application.admin.enabled=true
-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\lib\idea_rt.jar=3075:C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\bin
-Dfile.encoding=UTF-8
```