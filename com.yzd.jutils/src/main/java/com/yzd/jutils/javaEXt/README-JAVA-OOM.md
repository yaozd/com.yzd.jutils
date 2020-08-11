## OOM总结
- [内存问题排查套路](https://blog.csdn.net/chongshuang2128/article/details/101006203)
```
关于OOM，官方文档有关于OOM的说明（https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/memleaks002.html）， 主要分为以下几大类：
//
java.lang.OutOfMemoryError: Java heap space，堆的内存占用已经达到-Xmx设置的最大值，无法创建新对象，简单的可以考虑通过调整-Xmx参数来解决。
java.lang.OutOfMemoryError: GC Overhead limit exceeded，表示GC一直在执行且java进程运行很慢，通常会抛出此异常，java堆的分配的空间很小以至于新数据无法放到堆中。考虑调整堆大小，如果想关闭此输出，可用参数来关闭-XX:-UseGCOverheadLimit。
java.lang.OutOfMemoryError: Requested array size exceeds VM limit，java应用尝试分配大于堆大小的数组，如堆大小是256M，却要分配512M的数组，则会报错。考虑调整堆大小或者修改代码
java.lang.OutOfMemoryError: Metaspace，当类元数据所需的本机内存量超过时MaxMetaSpaceSize时报出，考虑调整MaxMetaSpaceSize。
java.lang.OutOfMemoryError: request size bytes for reason. Out of swap space?当来自本机堆的分配失败并且本机堆可能接近耗尽时会报此错误，需要查看日志来处理。
java.lang.OutOfMemoryError: Compressed class space，JVM的非堆结构中，类指针存放空间不足，考虑使用CompressedClassSpaceSize来调整。
java.lang.OutOfMemoryError: reason stack_trace_with_native_method，JVM的本地方法区不足，在Java本机接口（JNI）或本机方法中检测到分配失败，需要查找对应堆栈信息来查询。
```
- [错误java.lang.OutOfMemoryError：超过GC开销限制](http://www.imooc.com/wenda/detail/561353)
```
此消息意味着，由于某种原因，垃圾收集器占用了过多的时间(默认为进程所有CPU时间的98%)，每次运行时恢复的内存非常少(默认为堆的2%)。
这实际上意味着您的程序停止执行任何进度，并且一直忙于只运行垃圾收集。
为了防止应用程序占用CPU时间而不做任何事情，JVM抛出以下内容Error所以你有机会诊断这个问题。
我看到这种情况的罕见情况是，一些代码在已经非常受内存限制的环境中创建了大量临时对象和大量弱引用对象。
当垃圾收集花费太多的时间而返回的时间太少时，GC就会抛出这个异常。98%的CPU时间用于GC，不到2%的堆被恢复。
此功能旨在防止应用程序长时间运行，同时由于堆太小而几乎没有进展。
//
可以使用命令行选项关闭此选项。-XX:-UseGCOverheadLimit (PS:但我不建议关闭此选项byArvin)
//
如果你确定没有内存泄漏在您的程序中，尝试：
增加堆大小，例如
-Xmx1g.
```

- [Java 内存溢出（OOM）异常完全指南](https://blog.csdn.net/qq_35246620/article/details/77898295) -推荐参考byArvin