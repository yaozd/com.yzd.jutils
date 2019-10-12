## 随机数
- [java中ThreadLocalRandom类和Random类的使用](https://www.cnblogs.com/wp456/p/3359531.html)
- [为什么要使用ThreadLocalRandom代替Random生成随机数](https://www.cnblogs.com/shamo89/p/8052161.html)
- []()
- []()
- []()
## 总结：
```
如果两个Random对象种子数相同，那么他们生成的结果将是一样。
可以使用当前时间最为种子：System.currentTimeMillis()
任何情况下都不要在多个线程间共享一个Random实例，而该把它放入ThreadLocal之中
java7在所有情形下都更推荐使用ThreadLocalRandom，它向下兼容已有的代码且运营成本更低
```