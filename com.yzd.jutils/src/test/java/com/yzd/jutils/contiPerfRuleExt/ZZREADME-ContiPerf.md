## ContiPerf是一个轻量级的测试工具，基于JUnit 4 开发，可用于效率测试等。
- [ContiPerf介绍](https://blog.csdn.net/tomato__/article/details/22060449)
- [使用contiperf进行压测](https://blog.csdn.net/linsongbin1/article/details/51304349)
- [SpringBoot 使用ContiPerf测试工具](https://blog.csdn.net/lx1309244704/article/details/83781955)
- [https://github.com/lucaspouzac/contiperf](https://github.com/lucaspouzac/contiperf)

> 在junit执行完毕，会在target/contiperf-report中有相关的执行结果，可以使用浏览器打开查看

###　主要参数介绍
```
3、主要参数介绍
1）PerfTest参数
@PerfTest(invocations = 300)：执行300次，和线程数量无关，默认值为1，表示执行1次；
@PerfTest(threads=30)：并发执行30个线程，默认值为1个线程；
@PerfTest(duration = 20000)：重复地执行测试至少执行20s。
2）Required参数
@Required(throughput = 20)：要求每秒至少执行20个测试；
@Required(average = 50)：要求平均执行时间不超过50ms；
@Required(median = 45)：要求所有执行的50%不超过45ms； 
@Required(max = 2000)：要求没有测试超过2s；
@Required(totalTime = 5000)：要求总的执行时间不超过5s；
@Required(percentile90 = 3000)：要求90%的测试不超过3s；
@Required(percentile95 = 5000)：要求95%的测试不超过5s； 
@Required(percentile99 = 10000)：要求99%的测试不超过10s; 
@Required(percentiles = "66:200,96:500")：要求66%的测试不超过200ms，96%的测试不超过500ms。
4、测试结果展示
测试的结果可以展示在浏览器中，如下：
--------------------- 
作者：tomato__ 
来源：CSDN 
原文：https://blog.csdn.net/tomato__/article/details/22060449 
版权声明：本文为博主原创文章，转载请附上博文链接！
```

## 高级使用示例
- [并发测试](https://blog.csdn.net/weixin_30408165/article/details/97924032)
```
import org.databene.*;
import org.junit.*;
@RunWith(ContiPerfSuiteRunner.class)
@Suite.SuiteClasses({UtilTest.class})
@PerfTest(invocations =5, threads = 1, rampUp = 1000)   
public class PerformanceTest{
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
//  Invocations：方法的执行次数，例：@PerfTest(invocations = 300)重复执行300次；
//  Threads：同时执行的线程数，例：@PerfTest(invocations = 30, threads = 2)两个线程并发执行，每个线程执行15次，总共执行30次；
//  Duration：在指定时间范围内一直执行测试，例：@PerfTest(duration = 300)在300毫秒内反复执行。
//  三个属性可以组合使用，其中Threads必须和其他两个属性组合才能生效。当Invocations和Duration都有指定时，以执行次数多的为准。
//  例，@PerfTest(invocations = 300, threads = 2, duration = 100)，如果执行方法300次的时候执行时间还没到100ms，
//  则继续执行到满足执行时间等于100ms，如果执行到50次的时候已经100ms了，则会继续执行之100次。
      
//  如果你不想让测试连续不间断的跑完，可以通过注释设置等待时间，
//  例，@PerfTest(invocations = 1000, threads = 10, timer = RandomTimer.class, timerParams = { 30, 80 }) ，每执行完一次会等待30~80ms然后才会执行下一次调用。
      
//  在开多线程进行并发压测的时候，如果一下子达到最大进程数有些系统可能会受不了，ContiPerf还提供了“预热”功能，
//  例，@PerfTest(threads = 10, duration = 60000, rampUp = 1000) ，启动时会先起一个线程，然后每个1000ms起一线程，到9000ms时10个线程同时执行，那么这个测试实际执行了69s，
//  如果只想衡量全力压测的结果，那么可以在注释中加入warmUp，即@PerfTest(threads = 10, duration = 60000, rampUp = 1000, warmUp = 9000) ，
//  那么统计结果的时候会去掉预热的9s。
}
```



