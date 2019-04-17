
## kettle-开源免费数据同步
- [kettle的下载、安装和初步使用（windows平台下）（图文详解）](https://www.cnblogs.com/littlehb/p/9253855.html)
- [使用ktl工具实现mysql向mysql同步数据](https://jingyan.baidu.com/article/47a29f2493b0b2c015239941.html?from=singlemessage)
- [KETTLE使用教程](https://blog.csdn.net/Install_/article/details/80252565)
- [Kettle从入门到放弃，值得收藏-使用教程](https://blog.csdn.net/Mr_Tanga/article/details/81010973)
- [Kettle spoon 工具实战分享](https://blog.csdn.net/weixin_39824695/article/details/80412250)
- [使用Kettle实现数据实时增量同步](https://blog.csdn.net/dora_310/article/details/80511793)
- []()
- []()


### kettle-新手入门-hello world
- [spoon新手入门教程](https://blog.csdn.net/wuzhangweiss/article/details/78408029)
- [KETTLE使用教程](https://blog.csdn.net/Install_/article/details/80252565)
- [kettle教程（1） 简单入门、kettle简单插入与更新。打开kettle](https://blog.csdn.net/yangschfly/article/details/79204862)

### 循环抽取数据
- [Kettle循环抽取数据做增量](https://blog.csdn.net/hzp666/article/details/76625482)
- [KETTLE实现循环批量多表抽取添加字段](https://blog.csdn.net/qq_41704358/article/details/79519133)
- []()
- []()

### 一些Kettle使用的小示例-kettle_demo-master-学习示例
- [https://github.com/happyapple668/kettle_demo](https://github.com/happyapple668/kettle_demo)
- [https://github.com/yaozd/kettle_demo](https://github.com/yaozd/kettle_demo)-备份
- [百度云盘-开发工具>S-数据同步-Kettle]
```
一些Kettle的小示例程序，每个文件夹下面都是单独的示例。run_main.kjb或run_main.ktr是每个示例的入口
```
### kettle循环分页导入数据
- [kettle循环分页导入数据](https://my.oschina.net/jgy/blog/908272)
- [Kettle实现循环控制分批次抽取数据](https://ask.hellobi.com/blog/hql15/4298)
- []()
- []()
### Kettle变量参数传递介绍
- [【Kettle从零开始】第八弹之Kettle变量参数传递介绍](https://blog.csdn.net/rotkang/article/details/21008271)

### Kettle命令行使用说明
- [Kettle命令行使用说明](http://www.kettle.net.cn/1349.html)

### kettle-问题集合
- [kettle连接数据库，官方jdbc jar包下载以及布置安装](https://blog.csdn.net/Mr_Tanga/article/details/81014889)
- [Kettle连接MySQL数据库出现问题，连接不上](https://blog.csdn.net/a15020059230/article/details/72823039)
```
Driver class 'org.gjt.mm.mysql.Driver' could not be found, make sure the 'MySQL' driver (jar file) is installed.
org.gjt.mm.mysql.Driver
-----------------------------------
错误信息显示是jar包未安装，有问题找百度：

下载jar包：mysql-connector-java-5.1.15-bin.jar

放到Kettle安装目录下：D:\kettle\data-integration\lib

重启Spoon.bat问题解决：
-----------------------------------
下载地址：
开发工具>S-数据同步-Kettle>mysql-connector-java-5.1.46-bin.jar
-----------------------------------
链接：https://pan.baidu.com/s/1ARjQw_z-Ybd1CSQGHg-oCA 密码：prj0
```

### 变量的的使用
- [kettle学习笔记及最佳实践](https://my.oschina.net/jim19770812/blog/2251131?from=timeline)

```
${Internal.Entry.Current.Directory}/test.ktr可以表示当前目录下的test.ktr，同时适配repository模式和local文件模式
关于变量的使和编程语言中的变量不太一样，无法使用在同一个转换中定义和获取当前转换内修改过的变量，变通方法是拆成两个转换来使用，这问题卡了好几天才找到原因。
在job/转换通过-param:varname=value的方式传参时，如果发现变量无法解析，那么一定是job和转换的命名参数里没有配置（双击空白处，有个命名参数页签....）
在job/转换开始执行的时候通过日志输出一下用到的变量是个很好的习惯
作业和转换都要有命名参数startrow,pagesize,startdate,enddate几个，这样可以在调用的时候灵活控制分页以及起止时间，灵活实现全量和增量迁移
对变量冲突的问题要小心，特别是同一个job并行处理多个转换时更是如此，因此在job里并行执行转换时要格外小心。

写变量时有对变量作用域的设置:
推荐设置成Valid in the root job，不推荐Valid in the Java Virtual Matchine。
```