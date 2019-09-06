## volatile
- [jvm volatile 实现并发 解决副本缓存变量在工作内存中获取最后的值](https://blog.csdn.net/daimengs/article/details/80496241)
```
说明关键的地方 

vt.flag = true;

主线程将vt.flag的值同样 从主内存中拷贝到自己的线程工作内存 然后修改flag=true. 然后再将新值回到主内存。

这就解释了为什么在主线程（main）中设置了vt.flag = true; 而vt线程在进行判断flag的时候拿到的仍然是false。那就是因为vt线程每次判断flag标记的时候是从它自己的“工作内存中”取值，而并非从主内存中取值！

这也是JVM为了提供性能而做的优化。那我们如何能让vt线程每次判断flag的时候都强制它去主内存中取值呢。这就是volatile关键字的作用。
```