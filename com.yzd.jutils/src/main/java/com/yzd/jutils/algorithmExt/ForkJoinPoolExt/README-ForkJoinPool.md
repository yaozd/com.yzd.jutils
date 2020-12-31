## Fork / Join是使用递归分解（也称为分治制
- [死磕 java线程系列之ForkJoinPool深入解析](https://www.cnblogs.com/tong-yuan/p/11824018.html)
- [基于ForkJoin构建一个简单易用的并发组件](https://my.oschina.net/u/566591/blog/1791994)

```
ForkJoinPool最佳实践
（1）最适合的是计算密集型任务，本文由公从号“彤哥读源码”原创；

（2）在需要阻塞工作线程时，可以使用ManagedBlocker；

（3）不应该在RecursiveTask的内部使用ForkJoinPool.invoke()/invokeAll()；

总结
（1）ForkJoinPool特别适合于“分而治之”算法的实现；

（2）ForkJoinPool和ThreadPoolExecutor是互补的，不是谁替代谁的关系，二者适用的场景不同；

（3）ForkJoinTask有两个核心方法——fork()和join()，有三个重要子类——RecursiveAction、RecursiveTask和CountedCompleter；

（4）ForkjoinPool内部基于“工作窃取”算法实现；

（5）每个线程有自己的工作队列，它是一个双端队列，自己从队列头存取任务，其它线程从尾部窃取任务；

（6）ForkJoinPool最适合于计算密集型任务，但也可以使用ManagedBlocker以便用于阻塞型任务；

（7）RecursiveTask内部可以少调用一次fork()，利用当前线程处理，这是一种技巧；
```