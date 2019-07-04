### [快速解读GC日志](https://blog.csdn.net/renfufei/article/details/49230943)-特别推荐参考-byArvin-2019-07-04

下面我们来看，如何解读第一次GC事件，发生在年轻代中的小型GC(Minor GC):

2015-05-26T14:45:37.987-0200<sup>1</sup>:151.126<sup>2</sup>:

[GC<sup>3</sup>(Allocation Failure<sup>4</sup>)151.126: 

[DefNew<sup>5</sup>:629119K->69888K<sup>6</sup>(629120K)<sup>7</sup>, 0.0584157 secs]

1619346K->1273247K<sup>8</sup>(2027264K)<sup>9</sup>,0.0585007 secs<sup>10</sup>]

[Times: user=0.06 sys=0.00, real=0.06 secs]<sup>11</sup>

1. 2015-05-26T14:45:37.987-0200 – GC事件(GC event)开始的时间点.

2. 151.126 – GC事件的开始时间,相对于JVM的启动时间,单位是秒(Measured in seconds).

3. GC – 用来区分(distinguish)是 Minor GC 还是 Full GC 的标志(Flag). 这里的 GC 表明本次发生的是 Minor GC.

4. Allocation Failure – 引起垃圾回收的原因. 本次GC是因为年轻代中没有任何合适的区域能够存放需要分配的数据结构而触发的.

5. DefNew – 使用的垃圾收集器的名字. DefNew 这个名字代表的是: 单线程(single-threaded), 采用标记复制(mark-copy)算法的, 使整个JVM暂停运行(stop-the-world)的年轻代(Young generation) 垃圾收集器(garbage collector).

6. 629119K->69888K – 在本次垃圾收集之前和之后的年轻代内存使用情况(Usage).

7. (629120K) – 年轻代的总的大小(Total size).

8. 1619346K->1273247K – 在本次垃圾收集之前和之后整个堆内存的使用情况(Total used heap).

9. (2027264K) – 总的可用的堆内存(Total available heap).

10. 0.0585007 secs – GC事件的持续时间(Duration),单位是秒.

11. [Times: user=0.06 sys=0.00, real=0.06 secs] – GC事件的持续时间,通过多种分类来进行衡量:

- user – 此次垃圾回收, 垃圾收集线程消耗的所有CPU时间(Total CPU time).

- sys – 操作系统调用(OS call) 以及等待系统事件的时间(waiting for system event)

- real – 应用程序暂停的时间(Clock time). 由于串行垃圾收集器(Serial Garbage Collector)只会使用单个线程, 所以 real time 等于 user 以及 system time 的总和.

通过上面的分析, 我们可以计算出在垃圾收集期间, JVM 中的内存使用情况。在垃圾收集之前, 堆内存总的使用了 1.54G (1,619,346K)。其中, 年轻代使用了 614M(629,119k)。可以算出老年代使用的内存为: 967M(990,227K)。

下一组数据( -> 右边)中蕴含了更重要的结论, 年轻代的内存使用在垃圾回收后下降了 546M(559,231k), 但总的堆内存使用(total heap usage)只减少了 337M(346,099k). 通过这一点,我们可以计算出, 有 208M(213,132K) 的年轻代对象被提升到老年代(Old)中。

这个GC事件可以用下面的示意图来表示, 上方表示GC之前的内存使用情况, 下方表示结束后的内存使用情况:

