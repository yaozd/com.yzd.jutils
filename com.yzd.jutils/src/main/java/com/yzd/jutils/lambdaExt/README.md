## Lambda性能测试
```
展示的是for循环外部迭代耗时为基准的时间比值。分析如下：
对于基本类型Stream串行迭代的性能开销明显高于外部迭代开销（两倍）；
Stream并行迭代的性能比串行迭代和外部迭代都好。
```
### Stream并行迭代的性能比串行迭代和外部迭代都好。
1.[Java 8 Lambda（类库篇——Streams API，Collector和并行）](https://blog.csdn.net/jw598527338/article/details/80973638)
```
并行
java 8的Stream流的并行处理是以java 7推出的fork/join框架来处理的。
流水线既可以串行执行也可以并行执行，并行或串行是流的属性。
虽然需要显示的指定并行流，但是是非侵入式的（侵入式一般需要实现某个接口，重写某个方法等），不需要开发人员手动实现并行代码。
//
并行
int sum = shapes.parallelStream()
.filter(s -> s.getColor = BLUE)
.mapToInt(s -> s.getWeight())
.sum();
//
串行
int sum = shapes.stream()
.filter(s -> s.getColor = BLUE)
.mapToInt(s -> s.getWeight())
.sum();
因为并行的存在，所以stream流的操作应保证无干扰性：
不要干扰数据源（这个条件和遍历集合时所需的条件相似，如果集合在遍历时被修改，绝大多数的集合实现都会抛出ConcurrentModificationException）。
不要干扰其它lambda表达式，当一个lambda在修改某个可变状态而另一个lambda在读取该状态时就会产生这种干扰（所以在lambda表达式只允许使用有效只读的变量，对应用开放，对值封闭）。
```

### Java8 lambda表达式10个示例
- [Java8 lambda表达式10个示例](http://www.importnew.com/16436.html)
- [深入理解Java函数式编程和Streams API](https://github.com/CarpenterLee/JavaLambdaInternals)
- [深入理解Java函数式编程和Streams API](https://github.com/yaozd/JavaLambdaInternals)