### [java遍历Map的4种方法及效率对比](https://blog.csdn.net/fox_bert/article/details/87744728)

```
总结效率
entrySet的方式整体都是比keySet方式要高一些；
单纯的获取key来说，两者的差别并不大，但是如果要获取value，还是entrySet的效率会更好，
因为keySet需要从map中再次根据key获取value，而entrySet一次都全部获取出来；
iterator的迭代器方式比foreach的效率高。
```