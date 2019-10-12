### Java的异常处理对于性能的影响
- [Java的异常处理对于性能的影响](https://www.csdn.net/article/a/2015-04-22/15823959)
- [JAVA异常是否对于性能有影响](https://www.aliyun.com/jiaocheng/901079.html)
- [Java 进阶：异常影响性能吗？-byArvin推荐-2018-12-17-1758](https://blog.csdn.net/hustspy1990/article/details/78075394)
- [java exception使用的性能影响，用数据说话](https://blog.csdn.net/yuquan0405/article/details/47007333)

#### -总结

```
1.ex.getMessage()--对性能的影响比较小
2.ex.printStackTrace();获取异常堆栈信息对性能的影响比较大
3.logger.error("fuck", ex);把异常堆栈信息输出到日志对性能的影响特别严重
总结
处理异常的几个步骤里，对性能的耗费从大到小依次为：输出到日志、获取异常堆栈、创建并 catch 异常。
```

#### Java的异常体系
```
Throwable: Java中所有异常和错误类的父类。只有这个类的实例（或者子类的实例）可以被虚拟机抛出或者被java的throw关键字抛出。同样，只有其或其子类可以出现在catch子句里面。
Error: Throwable的子类，表示严重的问题发生了，而且这种错误是不可恢复的。
Exception: Throwable的子类，应用程序应该要捕获其或其子类（RuntimeException例外），称为checked exception。比如：IOException, NoSuchMethodException...
RuntimeException: Exception的子类，运行时异常，程序可以不捕获，称为unchecked exception。比如：NullPointException.

————————————————
```