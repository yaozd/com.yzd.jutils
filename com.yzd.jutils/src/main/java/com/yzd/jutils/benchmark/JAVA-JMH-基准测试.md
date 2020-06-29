## 使用JMH进行基准性能测试
- [使用JMH进行基准性能测试](https://blog.csdn.net/cndmss/article/details/93771981)-推出参考byArvin
- [使用JMH做吞吐量测试](https://blog.csdn.net/yh_zeng2/article/details/83716888)
- [JMH Samples](http://hg.openjdk.java.net/code-tools/jmh/file/fbe1b55eadf8/jmh-samples/src/main/java/org/openjdk/jmh/samples)
- [JMicrobenchmarking with Java](https://www.baeldung.com/java-microbenchmark-harness)
- [JMH使用指南](https://blog.csdn.net/xiandafu/article/details/94029094)

- [在java中使用JMH（Java Microbenchmark Harness）做性能测试](https://www.cnblogs.com/flydean/p/12680265.html)
    - [https://github.com/ddean2009/learn-java-concurrency/tree/master/benchmark](https://github.com/ddean2009/learn-java-concurrency/tree/master/benchmark)

## [相关概念](https://blog.csdn.net/superfjj/article/details/105110660)
- BenchmarkMode
```
@BenchmarkMode(Mode.Throughput)，Throughput的意思是整体吞吐量，表示给定的时间内执行的次数。
这里我们通过 @OutputTimeUnit(TimeUnit.SECONDS)来指定时间单位。
Mode除了Throughput还有如下几种模式：
Throughput-的意思是整体吞吐量，表示给定的时间内执行的次数
AverageTime - 调用的平均时间
SampleTime - 随机取样，最后输出取样结果的分布
SingleShotTime - 只会执行一次，通常用来测试冷启动时候的性能。
All - 所有的benchmark modes。
```
- Fork和Warmup
```
上面的例子中我们通过代码来显式的制定Fork和Warmup，我们也可以使用注解来实现：
    @Fork(value = 1, warmups = 2)
    @Warmup(iterations = 5)
上面的例子中value表示该benchMark执行多少次，warmups表示fork多少个进程来执行。iterations表示warmup的iterations个数。
如果你同时在代码中和注解中都配置了相关的信息，那么注解将会覆盖掉代码中的显示配置

```
- State和Scope
```
如果我们在多线程环境中使用beachMark,那么多线程中用到的类变量是共享还是每个线程一个呢？
Scope有三种：

Scope.Thread：默认的State，每个测试线程分配一个实例；
Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能；
Scope.Group：每个线程组共享一个实例；
```