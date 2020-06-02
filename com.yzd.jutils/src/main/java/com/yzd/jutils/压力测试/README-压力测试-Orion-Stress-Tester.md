# 基础-Vert.x
- [Vert.x(vertx) 简明介绍](https://blog.csdn.net/king_kgh/article/details/80772657)
- [vertx HttpClient使用的各种坑](https://blog.csdn.net/looook/article/details/83856693)

## Orion-Stress-Tester
- [https://gitee.com/mirren/Orion-Stress-Tester](https://gitee.com/mirren/Orion-Stress-Tester)
- [https://www.oschina.net/p/Orion-Stress-Tester](https://www.oschina.net/p/Orion-Stress-Tester)
- []()

> 一个简易,高效,精准的压力测试器,支持HTTP,WebSocket,TCP

## 如何运行
该项目基于 [vert.x 3.8.5](https://vertx.io/) 创建,

运行环境要求&gt;= java 1.8([在没有java的环境中运行Orion-Stress-Tester](./WithoutJavaEnvironment.zh.md)),

编码与测试环境java 1.8.0_121

方式一:

```
在releases中下载最新版发行版,解压后执行对应的start.bat或start.sh
```

方式二:

```
mvn clean package
将根目录的data文件夹、webroot文件夹与target中的 Orion-Stress-Tester-fat.jar复制到一个文件夹中执行
java -jar  Orion-Stress-Tester-fat.jar
```

如果要进行大量的测试任务,推荐根据自己电脑的配置设置JVM的 -Xms -Xmx 

在浏览器访问: http://127.0.0.1:7090

## 配置文件说明
OST的配置文件为data文件夹中的config.json
```
httpPort(int): OST运行的端口号,默认7090
instances(int): 测试任务运行的实例数量,默认0=处理器数量,如果你不了解vert.x你可以不用关心这个配置
```

## 客户端说明
客户端基于vue 编写,你可以通过客户端项目[Orion-Stress-Tester-Client](https://github.com/MirrenTools/Orion-Stress-Tester-Client)进行修改