## linux-java
```
nohup java -jar -Xms256M -Xmx256M hyperspace-console-0.0.1-SNAPSHOT.jar &

nohup java -jar -Xms256M -Xmx256M hyperspace-console-0.0.1-SNAPSHOT.jar --spring.profiles.active=test &

ps -ef|grep hyperspace-console|grep -v 'grep'|grep hll|head -1|awk -F" " '{print $2}'

kill 26701

jps -l

jps -l|grep console|awk -F " " '{print $1}'

kill -9 `jps -l|grep console|awk -F " " '{print $1}'`

|awk -F '{print $1}'

mv /tmp/hyperspace-console-0.0.1-SNAPSHOT.jar .

cd deploy

cat filename.txt| grep 'console'

1、tail -f filename
说明：监视filename文件的尾部内容（默认10行，相当于增加参数 -n 10），刷新显示在屏幕上。退出，按下CTRL+C。

2、tail -n 20 filename
说明：显示filename最后20行。

3、tail -r -n 10 filename
说明：逆序显示filename最后10行。
```
###  命令组合
```
1.jps -l    （注：只打印jar包名）
2.jstack pid ( 注：pid来自jps -l，打印线程快照-比较实用 )
3.jinfo pid   （注：打印系统启动的参数-（在查看环境系统与jvm配置信息时使用,与验证））
4.jmap -heap pid （注：打印堆信息，内容与visualVM的一样，只不过不是可视化）
  watch -n 3 jmap -heap 30381 （注：第3秒打印一下堆信息）
5.jstat -gcutil 30381 1000 （注：GC信息）
6.top -p pid （注：查看整体内存，单位：kB）
7.pmap pid （注：查看每一部分的内存，单位：kB; pmap 要与jstack相结合） 
```
```
java -jar -Xms40m -Xmx40m -XX:+HeapDumpOnOutOfMemoryError  .\out-of-memory-demo-0.0.1-SNAPSHOT.jar
```

```
java -jar -Xms258m -Xmx258m  back-module.jar --spring.profiles.active=prod --server.port=8004
```
```
java -jar -Xms258m -Xmx258m  back-module.jar --spring.profiles.active=prod --server.port=8004
```

```
java -jar -Xms258m -Xmx258m -XX:PermSize=512M -XX:MaxPermSize=512m back-module.jar --spring.profiles.active=prod --server.port=8004
注：JDK8不在支持PermSize与MaxPermSize参数
```
```
Solr  JVM ，一直FUll  GC，老年代对象一直存活，无法回收，初步分析： 使用 eclipse 插件  Memory  Analysis Tools ( MAT )分析老年代 内存占用情况
2.  进行步骤
A.导出  内存快照
jmap -dump:format=b,file=/tmp/heap.hprof  25477       
B.把  /tmp/heap.hprof  下载到本地，使用  MAT 进行分析
3.结果分析
--------------------- 
原文：https://blog.csdn.net/tianpeng341204/article/details/78953445 
```