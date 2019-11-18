## ContiPerf是一个轻量级的测试工具，基于JUnit 4 开发，可用于效率测试等。
- [ContiPerf介绍](https://blog.csdn.net/tomato__/article/details/22060449)
- [使用contiperf进行压测](https://blog.csdn.net/linsongbin1/article/details/51304349)
- [SpringBoot 使用ContiPerf测试工具](https://blog.csdn.net/lx1309244704/article/details/83781955)

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



