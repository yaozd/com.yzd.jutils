## JVM调优总结
- [JVM调优总结 -Xms -Xmx -Xmn -Xss](https://www.cnblogs.com/likehua/p/3369823.html)-推荐参考byArvin

## java中什么样的对象能够进入老年代
- [java中什么样的对象能够进入老年代](https://blog.csdn.net/zzb5682119/article/details/102393147)
```
1.大对象：所谓的大对象是指需要大量连续内存空间的java对象，
最典型的大对象就是那种很长的字符串以及数组，大对象对虚拟机的内存分配就是坏消息，
尤其是一些朝生夕灭的短命大对象，写程序时应避免。

2.长期存活的对象：虚拟机给每个对象定义了一个对象年龄(Age)计数器，
如果对象在Eden出生并经过第一次Minor GC后仍然存活，并且能被Survivor容纳的话，
将被移动到Survivor空间中，并且对象年龄设为1,。
对象在Survivor区中每熬过一次Minor GC，年龄就增加1，当他的年龄增加到一定程度(默认是15岁)， 
就将会被晋升到老年代中。对象晋升到老年代的年龄阈值，可以通过参数-XX:MaxTenuringThreshold设置。

3.动态对象年龄判定：为了能更好地适应不同程度的内存状况，
虚拟机并不是永远地要求对象的年龄必须达到了MaxTenuringThreshold才能晋升到老年代，
如果在Survivor空间中相同年龄的所有对象大小的总和大于Survivor空间的一半，
年龄大于或等于年龄的对象就可以直接进入老年代，无须等到MaxTenuringThreshold中要求的年龄。

```