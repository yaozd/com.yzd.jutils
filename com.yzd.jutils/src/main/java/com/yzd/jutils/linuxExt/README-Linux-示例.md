- HYPERSPACE-CONSOLE示例
- 
```
jps -l
kill -9 `jps -l|grep hyperspace-console|awk -F " " '{print $1}'`
jps -l
cd /home/hll/deploy/
mv /tmp/hyperspace-console-0.0.1-SNAPSHOT.jar .
rm /tmp/log/hyperspace-console/hyperspace-console.log 
nohup java -jar -Xms256M -Xmx256M hyperspace-console-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
jps -l
tail -f /tmp/log/hyperspace-console/hyperspace-console.log 
```

- API-ROUTER测试脚本
```
watch "curl http://localhost:9311|grep testDemo"
watch "> localhost.localdomain-hyperspace-warn.log;> localhost.localdomain-hyperspace-route.log;ls -lh"
//java 内存分析 OOM
glances （PS:entry java）
curl -O https://alibaba.github.io/arthas/arthas-boot.jar
java -jar arthas-boot.jar
dashboard --help
dashboard -i 10000 -n 10
```
