### NETTY启动脚本(一点差别，对netty的性能影响会是致命的)
- 测试环境
    ```
    kill  `jps -l|grep "/data/local/apirouter/netty-demo-"|awk -F " " '{print $1}'`
    mv /tmp/netty-demo-0.0.1-SNAPSHOT.jar /data/local/apirouter/
    nohup java -server -Xms1G -Xmx1G -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/local/apirouter/java_pid%p.hprof -Dio.netty.leakDetection.level=PARANOID -Dio.netty.leakDetection.targetRecords=15 -Dlogging.config=/data/local/apirouter/config/log4j2-spring.xml  -Dspring.config.location=file:/data/local/apirouter/config/application.yaml  -jar /data/local/apirouter/netty-demo-0.0.1-SNAPSHOT.jar >/data/local/apirouter/hyperspace.out 2>&1 &
    ```
- 生产环境
    ```
    kill  `jps -l|grep "/data/local/apirouter/netty-demo-"|awk -F " " '{print $1}'`
    mv /tmp/netty-demo-0.0.1-SNAPSHOT.jar /data/local/apirouter/
    nohup java -server -Xms1G -Xmx1G -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/local/apirouter/java_pid%p.hprof -Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -Dlogging.config=/data/local/apirouter/config/log4j2-spring.xml  -Dspring.config.location=file:/data/local/apirouter/config/application.yaml  -jar /data/local/apirouter/netty-demo-0.0.1-SNAPSHOT.jar >/data/local/apirouter/hyperspace.out 2>&1 &

    ```
- PS:区别
    1. 同步写日志，性能下降30%
    2. netty调试模式，性能下降30% (-Dio.netty.leakDetection.level=PARANOID -Dio.netty.leakDetection.targetRecords=15 )