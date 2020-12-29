## [性能分析工具JMH](https://blog.csdn.net/xiaolong_hui/article/details/78492108)
```
JMH是一个微基准测试框架，什么是微基准测试？ 
一、注解进行梳理和总结：
@BenchmarkMode
基准测试类型
名称	描述
Mode.Throughput	计算一个时间单位内操作数量
Mode.AverageTime	计算平均运行时间
Mode.SampleTime	计算一个方法的运行时间(包括百分位)
Mode.SingleShotTime	方法仅运行一次(用于冷测试模式)。或者特定批量大小的迭代多次运行(具体查看后面的“`@Measurement“`注解)——这种情况下JMH将计算批处理运行时间(一次批处理所有调用的总时间)
这些模式的任意组合	可以指定这些模式的任意组合——该测试运行多次(取决于请求模式的数量)
Mode.All	所有模式依次运行

@OutputTimeUnit
指定时间单位，它需要一个标准Java类型java.util.concurrent.TimeUnit作为参数。可是如果在一个测试中指定了多种测试模式，给定的时间单位将用于所有的测试(比如，测试SampleTime适宜使用纳秒，但是throughput使用更长的时间单位测量更合适)。

@Warmup
上面我们提到了，进行基准测试前需要进行预热。一般我们前几次进行程序测试的时候都会比较慢，所以要让程序进行几轮预热，保证测试的准确性。其中的参数iterations也就非常好理解了，就是预热轮数。

@State
注解定义了给定类实例的可用范围。JMH可以在多线程同时运行的环境测试，因此需要选择正确的状态。当使用@Setup参数的时候，必须在类上加这个参数，不然会提示无法运行。
名称	描述
Scope.Thread	默认状态。实例将分配给运行给定测试的每个线程。
Scope.Benchmark	运行相同测试的所有线程将共享实例。可以用来测试状态对象的多线程性能(或者仅标记该范围的基准)。
Scope.Group	实例分配给每个线程组(查看后面的线程组部分)
除了将单独的类标记@State，也可以将你自己的benchmark类使用@State标记。上面所有的规则对这种情况也适用。
@Measurement
度量，其实就是一些基本的测试参数。iterations进行测试的轮次，time每轮进行的时长，timeUnit时长单位。都是一些基本的参数，可以根据具体情况调整。一般比较重的东西可以进行大量的测试，放到服务器上运行。

方法注解总是优先于类的注解
名称	描述
@Fork	需要运行的试验(迭代集合)数量。每个试验运行在单独的JVM进程中。也可以指定(额外的)JVM参数。
@Measurement	提供真正的测试阶段参数。指定迭代的次数，每次迭代的运行时间和每次迭代测试调用的数量(通常使用@BenchmarkMode(Mode.SingleShotTime)测试一组操作的开销——而不使用循环)
@Warmup	与@Measurement相同，但是用于预热阶段
@Threads	该测试使用的线程数。默认是Runtime.getRuntime().availableProcessors()

————————————————

```