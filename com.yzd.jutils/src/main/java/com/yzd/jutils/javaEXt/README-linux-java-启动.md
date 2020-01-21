```
#!/bin/sh

nohup java -jar -Xms256M -Xmx256M yzd-demo-console-0.0.1-SNAPSHOT.jar &

nohup java -jar -Xms256M -Xmx256M yzd-demo-console-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &

ps -ef|grep yzd-demo-console|grep -v 'grep'|grep hll|head -1|awk -F" " '{print $2}'

kill 26701

jps -l

jps -l|grep console|awk -F " " '{print $1}'

kill -9 `jps -l|grep console|awk -F " " '{print $1}'`

|awk -F '{print $1}'

mv /tmp/yzd-demo-console-0.0.1-SNAPSHOT.jar .

cd deploy

cat filename.txt| grep 'console'

A - 关闭系统且关闭电源
shutdown -h now #立即关闭系统和电源
B - 利用shutdown重启电脑
shutdown -r now
```
```
> =============================================================================

kill -9 `jps -l|grep yzd-demo-console|awk -F " " '{print $1}'`

=============================================================================
```

- yzd-demo-CONSOLE示例
- 
```
jps -l
kill -9 `jps -l|grep yzd-demo-console|awk -F " " '{print $1}'`
jps -l
cd /home/hll/deploy/
mv /tmp/yzd-demo-console-0.0.1-SNAPSHOT.jar .
rm /tmp/log/yzd-demo-console/yzd-demo-console.log 
nohup java -jar -Xms256M -Xmx256M yzd-demo-console-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
jps -l
tail -f /tmp/log/yzd-demo-console/yzd-demo-console.log 

```
- yzd-demo-CONSOLE示例2
- 
```
jps -l
kill -9 `jps -l|grep yzd-demo-console|awk -F " " '{print $1}'`
jps -l
cd /home/hll/deploy/
mv /tmp/yzd-demo-console-0.0.1-SNAPSHOT.jar .
rm /tmp/log/yzd-demo-console/yzd-demo-console.log 
nohup java -jar -Xms256M -Xmx256M -Dspring.config.location=file:/home/hll/deploy/application-19312.yaml yzd-demo-console-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &
jps -l
tail -f /tmp/log/yzd-demo-console/yzd-demo-console.log 

```
```
tar -xf xx.tar
tar -xfv xx.tar (不推荐使用，PS:tar: v: Cannot open: No such file or directory)
```

- yzd-demo-CONTAINER示例
-
```

nohup java -jar -Xms256M -Xmx256M -Dlog4j2.contextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -Dlogging.config=/home/hll/deploy/container/log4j2-spring.xml -Dspring.config.location=file:/home/hll/deploy/container/application.yaml ./yzd-demo-container-0.0.1-SNAPSHOT.jar & 
```

- 开发环境-demo-CONTAINER示例-推荐参考-byArvin 
    - netty调试模式，内存检测，对性能有一定影响，对应配置：PS:-Dio.netty.leakDetection.level=PARANOID -Dio.netty.leakDetection.targetRecords=15
```
#!/bin/sh
nohup java -server -Xms1G -Xmx1G -XX:+UseConcMarkSweepGC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/data/local/apirouter/java_pid%p.hprof -Dio.netty.leakDetection.level=PARANOID -Dio.netty.leakDetection.targetRecords=15 -Dlogging.config=/data/local/apirouter/config/log4j2-spring.xml  -Dspring.config.location=file:/data/local/apirouter/config/application.yaml  -jar /data/local/apirouter/yzd-demo-container-0.0.1-SNAPSHOT.jar >/data/local/apirouter/yzd-demo.out 2>&1 &
```
- 生产环境-demo-CONTAINER示例-推荐参考-byArvin 
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
- HTTP-DEMO示例
- 
```
jps -l
kill -9 `jps -l|grep yzd-demo-console|awk -F " " '{print $1}'`
jps -l
cd /home/hll/deploy/
mv /tmp/yzd-demo-console-0.0.1-SNAPSHOT.jar .
rm /tmp/log/yzd-demo-console/yzd-demo-console.log 
nohup java -jar -Xms256M -Xmx256M ./http-demo-1.0-SNAPSHOT.jar &
jps -l
tail -f /tmp/log/yzd-demo-console/yzd-demo-console.log 

```
- 显示IP地址

```
 netstat -ntp
tcp        0      0 172.20.134.79:22        192.168.21.55:60354     ESTABLISHED -                   
tcp        0     64 172.20.134.79:22        192.168.21.55:55660     ESTABLISHED -   
=============================================================================
 netstat -ntpl
tcp        0      0 0.0.0.0:8080            0.0.0.0:*               LISTEN      -                   
tcp        0      0 0.0.0.0:8051            0.0.0.0:*               LISTEN      -       
=============================================================================
```
- 目录分类
- 
```
/data/software/
/data/deploy/
/data/scripts/（脚本存放目录）
/data/application/（应用程序）
/data/tools/（工具目录）

=============================================================================
```
- [linux下 如何切换到root用户](https://www.cnblogs.com/xinjie10001/p/6295020.html)
- sudo -i (切换到root用户)

```
默认安装完成之后并不知道root用户的密码，那么如何应用root权限呢？

(1)sudo 命令  
xzm@ubuntu:~$  sudo

这样输入当前管理员用户密码就可以得到超级用户的权限。但默认的情况下5分钟root权限就失效了。

(2)sudo -i
xzm@ubuntu:~$  sudo -i

通过这种方法输入当前管理员用户的密码就可以进到root用户。

(3)如果想一直使用root权限，要通过su切换到root用户。
那我们首先要重设置root用户的密码：

xzm@ubuntu:~$  sudo passwd root

这样就可以设置root用户的密码了。


(4)之后就可以自由的切换到root用户了
xzm@ubuntu:~$  su

输入root用户的密码即可。

su "king" 或者 exit回到用户权限
```
- 数据库清空-yzd-demo-console
- 
> truncate table tb_name;
```

