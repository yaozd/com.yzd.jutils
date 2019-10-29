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