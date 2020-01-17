### visualvm
- [jvisualvm安装Visual GC插件](https://blog.csdn.net/shuai825644975/article/details/78970371)

```
给jdk自带的jvisualvm安装Visual GC插件，遇到We're sorry the java.net site has closed（我们很抱歉java.net网站已经关闭）
1、找到新的更新地址

visualvm新访问地址：https://visualvm.github.io/index.html

http://visualvm.github.io/pluginscenters.html

https://visualvm.github.io/archive/uc/8u40/updates.xml.gz

```
- [visualvm新访问地址](https://visualvm.github.io/index.html)
- [http://visualvm.github.io/pluginscenters.html](http://visualvm.github.io/pluginscenters.html)
- [https://visualvm.github.io/archive/uc/8u40/updates.xml.gz](https://visualvm.github.io/archive/uc/8u40/updates.xml.gz)

###　Visual插件
- Threads Inspector 插件
    - 方便thread dump的调试
    
- GC 插件 PS:[visualvm 插件 visual gc 使用介绍](https://www.cnblogs.com/reycg-blog/p/7805075.html)
    - [jvisualvm远程监控 visualgc插件 不受此jvm支持问题](https://www.cnblogs.com/rgqancy/p/10104886.html)

- VisualVM-BufferMonitor 插件
 - 方便调试netty的堆外内存
- Startup Profiler
- KillApplication
- Tracer-Jvmstat Probes
- Tracer-Monitor Probes (PS:方便查看是否会发生内存溢出) heap=堆、PermGen=永久代
- 抽样器=》内存 (PS:方便查看实例个数)


### jvisualVM 监控远程 spring boot程序
- [jconsole 和jvisualVM 监控远程 spring boot程序](https://www.cnblogs.com/shengs/p/10796518.html)
```
java -Djava.rmi.server.hostname=192.168.56.112 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar http-demo-1.0-SNAPSHOT.jar
//
监控java 程序 增加启动参数 
java  \
-Djava.rmi.server.hostname=192.168.2.39 \
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=1099 \
-Dcom.sun.management.jmxremote.authenticate=false \
-Dcom.sun.management.jmxremote.ssl=false \
-jar /root/app/data-test-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev  >nohup 2>&1 & 

//
由于启动参数过长，我们可以定义系统环境变量来表示 在/etc/profile 最后一行增加 
export JAVA_OPTS='-Djava.rmi.server.hostname=192.168.2.39 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1099 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false'
然后 source /etc/profile 是文件生效。
启动时我们就可以用  nohup java $JAVA_OPTS -jar /root/app/data-center-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev  >nohup 2>&1 & 这个脚本。 
PS:
JMX端口为1099 ；
如果不设置服务器主机名 -Djava.rmi.server.hostname 到时候可能连不上。

```