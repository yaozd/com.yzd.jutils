
### OOM常用命令
- 命令组合

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

- jps -列出所有的jvm实例
```
1.打印详细信息
jps -mlvV
-------------------
2.只打印jar包名
jps -l
-------------------
```
- [jstack-用于生成java虚拟机当前时刻的线程快照](http://www.cnblogs.com/kongzhongqijing/articles/3630264.html)
```
打印线程快照-比较实用
jstack pid
-------------------
native+java栈:
jstack -m pid (注：目前用不到)
-------------------
线程状态
想要通过jstack命令来分析线程的情况的话，首先要知道线程都有哪些状态，下面这些状态是我们使用jstack命令查看线程堆栈信息时可能会看到的线程的几种状态：

NEW,未启动的。不会出现在Dump中。

RUNNABLE,在虚拟机内执行的。运行中状态，可能里面还能看到locked字样，表明它获得了某把锁。

BLOCKED,受阻塞并等待监视器锁。被某个锁(synchronizers)給block住了。

WATING,无限期等待另一个线程执行特定操作。等待某个condition或monitor发生，一般停留在park(), wait(), sleep(),join() 等语句里。

TIMED_WATING,有时限的等待另一个线程的特定操作。和WAITING的区别是wait() 等语句加上了时间限制 wait(timeout)。

TERMINATED,已退出的。
-------------------
```
- jinfo -打印系统启动的参数-（在查看环境系统与jvm配置信息时使用）
```
jinfo pid
-------------------
```
- jmap -heap pid （注：打印堆信息，内容与visualVM的一样，只不过不是可视化）
```
jmap -heap pid （注：打印堆信息，内容与visualVM的一样，只不过不是可视化）
-------------------
watch -n 3 jmap -heap 30381 （注：第3秒打印一下堆信息）
-------------------
```

- jstat -GC信息
```
jstat -gcutil 30381 1000 
jstat -gcutil pid 1秒 
-------------------
```

- top 
```
top -p pid
按f键查看每个参数的单位
1. 敲击“f”键，top进入另一个视图，在这里可以编排基本视图中的显示字段：
```

#### 参考
- [Java8 jvm参数](https://www.cnblogs.com/milton/p/6134251.html)
- [linux之pmap命令](https://www.cnblogs.com/wangkangluo1/archive/2012/06/04/2535121.html)
- [进制转换 - 在线工具](https://tool.lu/hexconvert/)