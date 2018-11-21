
#### Jmeter压力测试
- [压力测试Jmeter+badboy](https://blog.csdn.net/luosaosao/article/details/72900072)
- [Jmeter + Grafana + InfluxDB 性能测试监控](https://www.cnblogs.com/yyhh/p/5990228.html)

### 第一步：下载badboy和Jmeter
```
第一步：下载badboy和Jmeter
badboy：http://www.badboy.com.au/ 
Jmeter：http://jmeter.apache.org/
```

### [Jmeter做web压力测试时设置持续时间注意点](http://www.51testing.com/html/04/n-861004.html)

```
头一回使用jmeter做web的压力测试，遇到个很莫名其妙的问题，不管我的线程组怎么设置，它就是执行一次就结束了。
设置循环次数为300，不使用调度器－－〉执行一次就结束了，循环次数未生效
设置循环次数永远，设置高度器的开始和结束时间，不使用延迟和持续－－〉开始时间有效，执行一次就结束了，结束时间未生效
设置循环次数永远，设置延迟1秒，持续1200秒－－〉延迟1秒执行，执行一次就结束了，持续时间未生效
不管我怎么设置，都是执行一次就结束了，也没有错误发生，就是正常的结束
jmeter版本2.9和2.11都一个德行的
脚本是压测web脚本，利用badboy录制的，应该不会有问题，然后我就跟以前的脚本一个个比对，终于被我发现了！
在step里还有个设置，需要勾选上永远才行
===
在step里还有个设置，需要勾选上永远才行
```

### JMeter基本概念
- [Jmeter基础之---jmeter基础概念](http://www.cnblogs.com/fnng/archive/2012/12/21/2828440.html)

```
从性能工具的原理划分：

Jmeter工具和其他性能工具在原理上完全一致，工具包含4个部分：

（1）负载发生器：用于产生负载，通常以多线程或是多进程的方式模拟用户行为。

（2）用户运行器：通常是一个脚本运行引擎，用户运行器附加在线程或进程上，根据脚本要求模拟指定的用户行为。

（3）资源生成器：用于生成测试过程中服务器、负载机的资源数据。

（4）报表生成器：根据测试中霍地的数据生成报表，提供可视化的数据显示方式。
```

- [JMeter基础之--元件的作用域与执行顺序](https://www.cnblogs.com/fnng/archive/2012/12/27/2836506.html)

```
配置元件（config elements ） 
元件会影响其作用范围内的所有元件。
前置处理程序（Per-processors）
元件在其作用范围内的每一个sampler元件之前执行。
定时器（timers ）
元件对其作用范围内的每一个sampler 有效
后置处理程序（Post-processors）
元件在其作用范围内的每一个sampler元件之后执行。
断言（Assertions）
元件对其作用范围内的每一个sampler 元件执行后的结果执行校验。
监听器（Listeners）
元件收集其作用范围的每一个sampler元件的信息并呈现。
在jmeter中，元件的作用域是靠测试计划的的树型结构中元件的父子关系来确定的，作用域的原则是：
 取样器（sampler）元件不和其它元件相互作用，因此不存在作用域的问题。
 逻辑控制器（Logic Controller）元件只对其子节点中的取样器 和 逻辑控制器作用。
 除取样器 和逻辑控制器 元件外，其他6类元件，如果是某个sampler的子节点，则该元件公对其父子节点起作用。
 除取样器和逻辑控制器元件外的其他6类元件，如果其父节点不是sampler ，则其作用域是该元件父节点下的其他所有后代节点（包括子节点，子节点的子节点等）。
```


- [JMeter之Ramp-up Period（in seconds）说明（可同时并发）](https://blog.csdn.net/sunwangdian/article/details/50738870?utm_source=blogxgwz1)

```
【1】决定多长时间启动所有线程。如果使用10个线程，ramp-up period是100秒，那么JMeter用100秒使所有10个线程启动并运行。每个线程会在上一个线程启动后10秒（100/10）启动。Ramp-up需要要充足长以避免在启动测试时有一个太大的工作负载，并且要充足小以至于最后一个线程在第一个完成前启动。  一般设置ramp-up=线程数启动，并上下调整到所需的。

【2】用于告知JMeter 要在多长时间内建立全部的线程。默认值是0。如果未指定ramp-up period ，也就是说ramp-up period 为零， JMeter 将立即建立所有线程。假设ramp-up period 设置成T 秒， 全部线程数设置成N个， JMeter 将每隔T/N秒建立一个线程。

【3】Ramp-Up Period(in-seconds)代表隔多长时间执行，0代表同时并发
Delay Thread creation until needed         

延迟创建线程，直到该线程开始采样，即之后的任何线程组延迟和加速时间为线程本身。这样可以支持更多的线程，但不会有太多是同时处于活动状态。
```

- [聚合报告(Aggregate Report)](https://blog.csdn.net/sd4015700/article/details/50348978)

```
其中：

Label：标签，即我们上面的请求名称

#Samples：本次场景中一共发出了多少个请求

Average：平均响应时间

Median：中位数，也就是50%的用户的响应时间

90%Line：表示90%的用户的响应时间，如果最小值和最大值相差很大的话，我们一般选择这个作为最终测试结果

Min：最小响应时间

Max：最大响应时间

Error%：出错率，本次测试中出现错误的请求的数量/请求的总数

Throughput：吞吐量

KB/sec：每秒从服务器端接受到的数据量
```

- [Jmeter之六：定时器 之 常数吞吐量定时器](https://blog.csdn.net/shuimengzhen/article/details/57075437?utm_source=blogxgwz4)
- [07-性能测试之JMeter定时器](https://blog.csdn.net/duzilonglove/article/details/79615083?utm_source=blogkpcl8)