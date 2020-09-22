# jvm 参数：
- [https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/java.html)
- [http://51gjie.com/java/551.html](http://51gjie.com/java/551.html)
- [Java JVM 参数设置大全](http://51gjie.com/java/551.html)

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