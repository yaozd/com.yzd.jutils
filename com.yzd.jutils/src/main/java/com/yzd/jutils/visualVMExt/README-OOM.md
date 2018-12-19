### -使用nohup记录在jar出现内存溢出的错误信息的前提条件，测试与生产环境不要在控件台上打印日志信息。

### -重点：OOM的日志打印

```
1.tomcat的应用，OOM的日志可能会在log中打印出，最好还是要用nohup做一下记录
2.控制台的应用，OOM的日志不会在log中打印，一定要用nohup做一下记录
```
### -重点：通过nohup记录在jar出现内存溢出时，控制台输出的错误信息，会比HeapDumpOnOutOfMemoryError生成的.hprof文件，更加直接。

```
nohup java -jar -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError  .\out-of-memory-demo-0.0.1-SNAPSHOT.jar &
```

### -执行jar 包时，设置执行内存

```
java -jar -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError  .\out-of-memory-demo-0.0.1-SNAPSHOT.jar
```

```
java -jar -verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError  .\out-of-memory-demo-0.0.1-SNAPSHOT.jar
(verbose:gc)暂时不需要此配置-2018-12-18-1638
–verbose:gc 在虚拟机发生内存回收时在输出设备显示信息，格式如下： [Full GC 268K->168K(1984K), 0.0187390 secs]该参数用来监视虚拟机内存回收的情况。 
-Xms20M 设置应用程序的初始内存大小为20M 
-Xmx20M 设置应用程序的能够使用的最大内存为20M（与-Xms设置大小相同可以避免堆自动扩展） 
-XX:+HeapDumpOnOutOfMemoryError 可以让虚拟机在出现内存异常的时候自动Dump出当前的内存对转储快照以便日后进行分析
```
- -[HeapDumpOnOutOfMemoryError堆转储实践和一些分析](https://epy.iteye.com/blog/1914455)
- -[Java内存分析工具MAT(Memory Analyzer Tool)安装使用实例](https://blog.csdn.net/jin_kwok/article/details/80326088)
- -[内存问题的分析与内存分析工具MAT(Memory Analyzer Tool)的使用](https://blog.csdn.net/m0_37450089/article/details/81368785)