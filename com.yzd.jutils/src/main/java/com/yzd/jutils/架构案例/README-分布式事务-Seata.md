## 分布式事务
- [揭秘蚂蚁金服分布式事务 Seata 的AT、Saga和TCC模式](https://mp.weixin.qq.com/s?__biz=MzIxMzEzMjM5NQ==&mid=2651033686&idx=2&sn=85d01f2415e9eace043c46dfe62992e7)
- []()
- []()
- Seata
```
Saga 是一种补偿协议，
在 Saga 模式下，
分布式事务内有多个参与者，
每一个参与者都是一个冲正补偿服务，
需要用户根据业务场景实现其正向操作和逆向回滚操作。
```