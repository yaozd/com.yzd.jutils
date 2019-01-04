

### 事务逻辑-本质状态流转

```
2018-12-05-1106
-------------------------------------
1.交易--根节点
-------------------------------------
2.日志节点
-------------------------------------
3.正向处理逻辑--根据日志节点的信息
-------------------------------------
4.反向处理逻辑--根据日志节点的信息
-------------------------------------
```
### 事务表的设计
```
1.主事务表
2.主事务表详情
----
3.事务确认表（PS:主要用于做本地事务）
```
### 事务设计的关键问题
```
1.事务对性能的影响
2.如何缩小事务产生的锁的影响范围 （where 主键ID）
3.如何控制事务的关联的范围。 （通过REQUIRES_NEW把大事务切割为小事务）
```

### 分布式事务-通过REQUIRES_NEW把大事务切割为小事务
```
分布式事务中，建议使用REQUIRES_NEW把大事务切割为小事务
TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
Spring中声明式事务的注解@Transactional的参数的总结（REQUIRED和REQUIRES_NEW的与主方法的回滚问题
原文：https://blog.csdn.net/gpf951101/article/details/77751025 
```

### [基本的设计思想是将远程分布式事务拆分成一系列的本地事务-思路是源于ebay](https://www.cnblogs.com/frankyou/p/7238099.html)
```
常用的分布式技术说明
1. 本地消息表
这种实现方式的思路是源于ebay，其基本的设计思想是将远程分布式事务拆分成一系列的本地事务。
举个经典的跨行转账的例子来描述。
第一步伪代码如下，扣款1W，通过本地事务保证了凭证消息插入到消息表中。
Begin transaction
    update A set amount = amount - 10000 where userId = 1;
    insert into message(userId, price, status) values(1, 10000, 1);
End transaction
commit;
第二步，通知对方银行账户上加1W了，通常采用两种方式：
采用时效性高的MQ，由对方订阅消息并监听，有消息时自动触发事件。
采用定时轮询扫描的方式，去检查消息表的数据。
```
### -分布式系统事务参考：
- [分布式系统事务一致性解决方案大对比，谁最好使](https://blog.csdn.net/kingice1014/article/details/53508747)
- [支付宝 分布式事务服务 DTS 一](https://blog.csdn.net/qq_27384769/article/details/79303744)
- [支付宝 分布式事务服务 DTS 二](https://blog.csdn.net/qq_27384769/article/details/79303942)
- [微服务架构下分布式事务解决方案——阿里GTS-推荐参考byArvin](https://www.cnblogs.com/jiangyu666/p/8522547.html)
