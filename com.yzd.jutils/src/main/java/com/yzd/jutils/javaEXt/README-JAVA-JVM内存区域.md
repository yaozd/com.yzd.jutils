## JVM内存区域
- [JVM内存区域详解（Eden Space、Survivor Space、Old Gen、Code Cache和Perm Gen）](https://blog.csdn.net/shiyong1949/article/details/52585256/)
- [【深入Java虚拟机】之内存区域详解（Eden Space、Survivor Space、Old Gen、Code Cache和Perm Gen）](https://blog.csdn.net/qq_32534441/article/details/86307997)
- [JVM调优中，压缩类空间(Compressed Class space)如何理解，有多重要？](https://www.zhihu.com/question/268392125)
```
JVM区域总体分两类，heap区和非heap区。
heap区又分为：

Eden Space（伊甸园）、
Survivor Space(幸存者区)、
Old Gen（老年代）。
非heap区又分：

Code Cache(代码缓存区)；
Perm Gen（永久代）；
Jvm Stack(java虚拟机栈)；
Local Method Statck(本地方法栈)；
CompressedClassSpace(压缩类空间)
————————————————
原文链接：https://blog.csdn.net/shiyong1949/article/details/52585256/
```