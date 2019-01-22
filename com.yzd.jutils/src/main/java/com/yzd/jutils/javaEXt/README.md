### JDK安装
```
要求1.8以上版本，我使用的是jdk1.8.0_40，安装完JDK后写入环境变量。

  新建系统变量JAVA_HOME 和CLASSPATH

变量名：JAVA_HOME
   变量值：C:\Program Files\Java\jdk1.8.0_140

变量名：CLASSPATH
   变量值：.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;

变量名：Path
   变量值：%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;
```
> [docker+tomcat 启动时非常慢原因之JRE /dev/random阻塞](http://www.mamicode.com/info-detail-1964289.html)
```
java -Djava.security.egd=file:/dev/./urandom -jar test.jar

```
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD my-site-1.0.0.RELEASE.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
> java -Xmx100m  -jar hb.eureka.server-1.0-SNAPSHOT.jar --spring.profiles.active=peer1

> java  --server.port=9090 -jar app.jar

> java -Djava.security.egd=file:/dev/./urandom -jar test.jar
