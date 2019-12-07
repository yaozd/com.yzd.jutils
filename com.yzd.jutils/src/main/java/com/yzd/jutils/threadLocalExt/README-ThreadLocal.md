- [使用ThreadLocal要注意](https://my.oschina.net/u/4007037/blog/3048015)

> ThreadLocalMap使用ThreadLocal的弱引用作为key，如果一个ThreadLocal没有外部强引用来引用它，那么系统 GC 的时候，这个ThreadLocal势必会被回收，这样一来，ThreadLocalMap中就会出现key为null的Entry，就没有办法访问这些key为null的Entry的value，如果当前线程再迟迟不结束的话，这些key为null的Entry的value就会一直存在一条强引用链：Thread Ref -> Thread -> ThreaLocalMap -> Entry -> value永远无法回收，造成内存泄漏。

### [ThreadLocal应用和那些“坑”-推荐阅读-byArvin](https://blog.csdn.net/fly910905/article/details/78869251)

```
补充：在不论什么异步程序中（包含异步I/O、非堵塞I/O），ThreadLocal的參数传递是不靠谱的，由于线程将请求发送后。
就不再等待远程返回结果继续向下运行了，真正的返回结果得到后，处理的线程可能是还有一个。
-----------------
ThreadLocal使用场合主要解决多线程中数据数据因并发产生不一致问题。
-----------------
Synchronized用于线程间的数据共享，而ThreadLocal则用于线程间的数据隔离。
-----------------

-----------------

```