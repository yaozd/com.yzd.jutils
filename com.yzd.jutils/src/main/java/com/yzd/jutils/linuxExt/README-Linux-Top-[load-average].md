## top load average
- [top命令输出解释以及load average 详解及排查思路](https://blog.csdn.net/zhangchenglikecc/article/details/52103737)

```
load average  
load average对于压力测试的意义：可以平估是否CPU处理出现瓶颈。
以下命令均可获取load average系统平均负载
# top
# uptime
# w 
# cat /proc/loadavg
————————————————
3个数值分别表示系统在过去1分钟、5分钟、15分钟内运行进程队列中的平均进程数量。
运行队列嘛，没有等待IO，没有WAIT，没有KILL的进程通通都进这个队列。
　　- 它没有在等待I/O操作的结果
　　- 它没有主动进入等待状态(也就是没有调用'wait')
　　- 没有被停止(例如：等待终止)
————————————————
第一种是满负荷但CPU时间片不用排队等待正好够用，第二种是%50空闲，第三个是超负荷50%，后面的就有队列等待了
1.单核CPU， 数字在0.00-1.00之间正常
0.00-1.00        之间的数字表示此时路况非常良好，没有拥堵，车辆可以毫无阻碍地通过。
1.00  表示道路还算正常，但有可能会恶化并造成拥堵。此时系统已经没有多余的资源了，管理员需要进行优化。
1.00以上 表示路况不太好了，如果到达2.00表示有桥上车辆一倍数目的车辆正在等待。这种情况你必须进行检查了。
2.多核CPU - 多车道      数字/CPU核数 在0.00-1.00之间正常
  用双核举例，双核的负载已经比单核提高一倍了，那多核的也是同理。
  多核CPU的话，满负荷状态的数字为 "1.00 * CPU核数"，即双核CPU为2.00，四核CPU为4.00。

```