### -[Spring对于事务的控制@Transactional注解详解](https://blog.csdn.net/fanxb92/article/details/81296005)
```
先简单介绍一下Spring事务的传播行为：

所谓事务的传播行为是指，如果在开始当前事务之前，一个事务上下文已经存在，此时有若干选项可以指定一个事务性方法的执行行为。在TransactionDefinition定义中包括了如下几个表示传播行为的常量：

TransactionDefinition.PROPAGATION_REQUIRED：如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值。
TransactionDefinition.PROPAGATION_REQUIRES_NEW：创建一个新的事务，如果当前存在事务，则把当前事务挂起。
TransactionDefinition.PROPAGATION_SUPPORTS：如果当前存在事务，则加入该事务；如果当前没有事务，则以非事务的方式继续运行。
TransactionDefinition.PROPAGATION_NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
TransactionDefinition.PROPAGATION_NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
TransactionDefinition.PROPAGATION_MANDATORY：如果当前存在事务，则加入该事务；如果当前没有事务，则抛出异常。
TransactionDefinition.PROPAGATION_NESTED：如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。
--------------------- 
原文：https://blog.csdn.net/fanxb92/article/details/81296005 
```
-如果想要方法1和方法2均单独保持事务一致性怎么办呢？
```
如果想要方法1和方法2均单独保持事务一致性怎么办呢？
刚说过了，如果不是用代理调用@Transactional注解是不生效的，所以一定要使用代理调用实现，然后让方法1和方法2分别单独开启新的事务，便OK啦。下面摆上图片。
---------------------
@Transactional
//REQUIRES_NEW事务的方法
@Transactional(propagation = Propagation.REQUIRES_NEW)
//REQUIRES_NEW事务的方法
@Transactional(propagation = Propagation.REQUIRES_NEW)
--------------------- 
原文：https://blog.csdn.net/fanxb92/article/details/81296005 
```
### -[链式方法调用的事务问题剖析](https://blog.csdn.net/fanxb92/article/details/82784348)
