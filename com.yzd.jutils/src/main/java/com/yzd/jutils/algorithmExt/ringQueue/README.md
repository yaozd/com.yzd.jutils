# 环形队列
## 问题：算法-大数据量的定时超时
- [10w定时任务，如何高效触发超时](https://blog.csdn.net/shenjian58/article/details/89849908)
- [算法-大数据量的定时超时](https://blog.csdn.net/aBOUNTWINTER/article/details/79407768)
- 方法一：轮询扫描法
- 方法二：多timer触发
- 方法三：环形队列法

### github-环形队列实现代码
- [RingQueue](https://github.com/a870368162/RingQueue/tree/master/RingQueue) -58到家
- [Dubbo/Netty中时间轮算法的原理-HashedWheelTimer](https://blog.csdn.net/dbqb007/article/details/90740839)

### 大环-通过两个环组成一个大环（分钟环+秒环）=小时环
- 小时环=分钟环+秒环

###　应用场景：
- 轻量级延一次性延迟触发,如电商系统订单超时未支付自动取消

> 源码解析： Steper-秒级扫描器 StepSlot-环形队列每一格插槽，一个环形队列包含3600个slot Task- 继承Runable，需要执行的具体任务 RingQueue-环形队列主体实现