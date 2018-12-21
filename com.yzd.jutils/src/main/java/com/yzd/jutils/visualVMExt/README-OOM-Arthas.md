
### [Arthas-阿尔萨斯](https://alibaba.github.io/arthas/index.html) 

```
Arthas-阿尔萨斯是Alibaba开源的Java诊断工具，深受开发者喜爱
当你遇到以下类似问题而束手无策时，Arthas可以帮助你解决：

这个类从哪个 jar 包加载的？为什么会报各种类相关的 Exception？
我改的代码为什么没有执行到？难道是我没 commit？分支搞错了？
遇到问题无法在线上 debug，难道只能通过加日志再重新发布吗？
线上遇到某个用户的数据处理有问题，但线上同样无法 debug，线下无法重现！
是否有一个全局视角来查看系统的运行状况？
有什么办法可以监控到JVM的实时运行状态？
```
### [Arthas-快速入门](https://alibaba.github.io/arthas/quick-start.html)

```
java -jar arthas-boot.jar 
1.输入”编号“，再输入回车/enter
2.输入dashboard
dashboard 说明：https://alibaba.github.io/arthas/dashboard.html
```
### [查看dashboard](https://alibaba.github.io/arthas/dashboard.html)

###　Arthas-常用命令
```
1.dashboard (相当于：visualVM)
2.thread (相当于：jstack,查看线程情况-用于CPU 占用率过高或都线程阻塞)
参考：
记一次线上Java程序导致服务器CPU占用率过高的问题排除过程
https://www.jianshu.com/p/3667157d63bb
3.jvm （查看当前JVM信息，包括线程相关统计信息与VM进程最大可以打开的文件描述符数）
4.jad （反编译指定已加载类的源码）
5.redefine （可以通过redefine命令来达到线上不重启，动态更新代码的效果）
5.monitor （方法执行监控）
6.watch （能观察到的范围为：返回值、抛出异常、输入参数）
7.tt (测试重放，减少测试小姐姐重复发请求)
分享及其资料：当DUBBO遇上Arthas - 排查问题的实践
https://github.com/alibaba/arthas/issues/327
```
### tt-怎样减少测试小姐姐重复发请求的麻烦?
```
1.tt -t命令捕获到了3个请求
tt -t com.example.UserServiceImpl findUser
2.tt --play可以重放请求
tt --play -i 1000
```

### [手动安装Arthas](https://alibaba.github.io/arthas/manual-install.html)

### -Arthas使用重点，必须先启动Arthas，然后再启动jar程序。(这个理论是不对的，但是执行下面的debug后，再可以任意启动不分先后，目前暂不知为什么)

```
Arthas使用重点，必须先启动Arthas，然后再启动jar程序。
1.先启动Arthas,主要是对后面启动的jar程序，做拦截注入操作。
2.然后再启动jar程序
```

###　[SpringBoot程序远程debug](https://www.cnblogs.com/senlinyang/p/8516867.html)
```
java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 out-of-memory-web-demo-0.0.1-SNAPSHOT.jar 
```