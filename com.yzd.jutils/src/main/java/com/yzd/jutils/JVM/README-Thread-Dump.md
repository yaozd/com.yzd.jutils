## [Java Thread Dump]()
- [Java Thread Dump](https://blog.csdn.net/why_still_confused/article/details/82352552)
> Java线程转储（Thread dump）是JVM中运行中的所有线程的列表。

> Java线程转储十分有助于分析应用程序的瓶颈和死锁情况。
>

## java获取内存dump的几种方式
```
java获取内存dump的几种方式

1、获取内存详情：jmap -dump:format=b,file=heapdump-demo.bin pid
这种方式可以用 jvisualvm.exe 进行内存分析，或者采用 Eclipse Memory Analysis Tools (MAT)这个工具

//出发fullgc
2. 获取内存dump：  jmap -histo:live pid
这种方式会先出发fullgc，所有如果不希望触发fullgc 可以使用jmap -histo pid

3.第三种方式：jdk启动加参数：
-XX:+HeapDumpBeforeFullGC 
-XX:HeapDumpPath=/httx/logs/dump
这种方式会产生dump日志，再通过jvisualvm.exe 或者Eclipse Memory Analysis Tools 工具进行分析
```

## jstack(查看线程)、jmap(查看内存)和jstat(性能分析)命令
- [jstack(查看线程)、jmap(查看内存)和jstat(性能分析)命令](https://blog.csdn.net/junlinbo/article/details/48734011)