##　Logback日志扩展
- [https://github.com/yaozd/com.yzd.logback.extend.root](https://github.com/yaozd/com.yzd.logback.extend.root)

#### log4j自定义appender
```
log4j2自定义Appender（输出到文件/RPC服务中）
https://www.jianshu.com/p/c753644bb2f6
Log4j扩展使用--自定义输出
https://www.cnblogs.com/LinkinPark/p/5232837.html
```
#### log4j2 扩展日志级别-支持将系统日志与业务处理日志拆分
- [log4j2 扩展日志级别,支持将系统日志与业务处理日志拆分](https://www.cnblogs.com/jessezeng/p/5446518.html)
- [使用log4j2分离系统日志与业务日志](https://www.cnblogs.com/jessezeng/p/5446589.html)
- [扩展log4j日志级别](http://blog.51cto.com/bzlring/1359292)
- [log4j增加日志级别](http://jn-nian.iteye.com/blog/1457325)


#### log4j2 扩展日志级别-业务处理日志处理思路->byArvin2018-09-20

```
业务处理日志->Filebeat->Logstash->Redis(list)->业务处理逻辑
```

#### Filebeat的高级配置-Filebeat部分
- [Filebeat的高级配置-Filebeat部分](https://blog.csdn.net/a464057216/article/details/51233375)
```
1.
paths：指定要监控的日志，目前按照Go语言的glob函数处理。没有对配置目录做递归处理，比如配置的如果是：
/var/log/* /*.log
则只会去/var/log目录的所有子目录中寻找以”.log”结尾的文件，而不会寻找/var/log目录下以”.log”结尾的文件。
2.
scan_frequency：Filebeat以多快的频率去prospector指定的目录下面检测文件更新（比如是否有新增文件），如果设置为0s，则Filebeat会尽可能快地感知更新（占用的CPU会变高）。默认是10s。
3.
ignore_older：可以指定Filebeat忽略指定时间段以外修改的日志内容，比如2h（两个小时）或者5m(5分钟)。

close_older：如果一个文件在某个时间段内没有发生过更新，则关闭监控的文件handle。默认1h。

force_close_files：Filebeat会在没有到达close_older之前一直保持文件的handle，如果在这个时间窗内删除文件会有问题，所以可以把force_close_files设置为true，只要filebeat检测到文件名字发生变化，就会关掉这个handle。

scan_frequency：Filebeat以多快的频率去prospector指定的目录下面检测文件更新（比如是否有新增文件），如果设置为0s，则Filebeat会尽可能快地感知更新（占用的CPU会变高）。默认是10s。

document_type：设定Elasticsearch输出时的document的type字段，也可以用来给日志进行分类。

harvester_buffer_size：每个harvester监控文件时，使用的buffer的大小。

max_bytes：日志文件中增加一行算一个日志事件，max_bytes限制在一次日志事件中最多上传的字节数，多出的字节会被丢弃。

multiline：适用于日志中每一条日志占据多行的情况，比如各种语言的报错信息调用栈。这个配置的下面包含如下配置：
```