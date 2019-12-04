##　测试方法
－[Jmeter集群梯度发压]()

##　参考案例：
- [压测介绍-go实现的压测工具【单台机器100w连接压测实战】](https://blog.csdn.net/link_km/article/details/100130784)-推荐参考
- [如何生成每秒百万级别的 HTTP 请求？](https://my.oschina.net/qiangzigege/blog/519340)
- [几乎所有的WEB压力测试工具 WEB压力测试工具大全](https://www.iamle.com/archives/2173.html)

##　 压测名词解释:
```

压测名词	解释
并发(Concurrency)	指一个处理器同时处理多个任务的能力(逻辑上处理的能力)
并行(Parallel)	多个处理器或者是多核的处理器同时处理多个不同的任务(物理上同时执行)
QPS(每秒钟查询数量 Query Per Second)	服务器每秒钟处理请求数量 (req/sec 请求数/秒 一段时间内总请求数/请求时间)
事务(Transactions)	是用户一次或者是几次请求的集合
TPS(每秒钟处理事务数量 Transaction Per Second)	服务器每秒钟处理事务数量(一个事务可能包括多个请求)
请求成功数(Request Success Number)	在一次压测中，请求成功的数量
请求失败数(Request Failures Number)	在一次压测中，请求失败的数量
错误率(Error Rate)	在压测中，请求成功的数量与请求失败数量的比率
最大响应时间(Max Response Time)	在一次事务中，从发出请求或指令系统做出的反映(响应)的最大时间
最少响应时间(Mininum Response Time)	在一次事务中，从发出请求或指令系统做出的反映(响应)的最少时间
平均响应时间(Average Response Time)	在一次事务中，从发出请求或指令系统做出的反映(响应)的平均时间
2.3.3 机器性能指标解释
机器性能	解释
CUP利用率(CPU Usage)	CUP 利用率分用户态、系统态和空闲态，CPU利用率是指:CPU执行非系统空闲进程的时间与CPU总执行时间的比率
内存使用率(Memory usage)	内存使用率指的是此进程所开销的内存。
IO(Disk input/ output)	磁盘的读写包速率
网卡负载(Network Load)	网卡的进出带宽,包量
2.3.4 访问指标解释
访问	解释
PV(页面浏览量 Page View)	用户每打开1个网站页面，记录1个PV。用户多次打开同一页面，PV值累计多次
UV(网站独立访客 Unique Visitor)	通过互联网访问、流量网站的自然人。1天内相同访客多次访问网站，只计算为1个独立访客
————————————————
版权声明：本文为CSDN博主「link_km」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/link_km/article/details/100130784
```