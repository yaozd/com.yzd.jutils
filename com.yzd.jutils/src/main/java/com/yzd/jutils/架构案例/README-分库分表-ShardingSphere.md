## 分库分表
    - [分库分表？如何做到永不迁移数据和避免热点？](https://blog.csdn.net/qq_35246620/article/details/90407308)
    - [炸！亿级数据DB秒级平滑扩容](https://blog.csdn.net/z50L2O08e2u4afToR9A/article/details/89839471)
    
## ShardingSphere
- [ShardingSphere的快速入门](https://shardingsphere.apache.org/document/current/cn/quick-start/)
- [数据库连接池：连接模式](https://shardingsphere.apache.org/document/current/cn/features/sharding/principle/execute/)
    - 内存限制模式
    - 连接限制模式
## 理论
- 分库分表
    - [分库分表？如何做到永不迁移数据和避免热点？](https://blog.csdn.net/qq_35246620/article/details/90407308)
## 示例
- [Sharding-jdbc分库分表入门实例](https://blog.csdn.net/wk52525/article/details/89020367)
- [springboot整合sharding-jdbc分库分表最全详解](https://blog.csdn.net/weixin_43168010/article/details/100215988)
- [ShardingSphere x Seata，一致性更强的分布式数据库中间件](https://developer.aliyun.com/article/712037)
- [蚂蚁金服服务注册中心如何实现 DataServer 平滑扩缩容 | SOFARegistry 解析](https://developer.aliyun.com/article/759414)

- [Spring Boot集成Sharding-jdbc + Mybatis-Plus实现分库分表](https://blog.csdn.net/Macky_He/article/details/95754402)
    - [https://github.com/yaozd/spring-boot--shardingsphere-examples](https://github.com/yaozd/spring-boot--shardingsphere-examples)

## 实现类型
- 动态数据源
- [订单与用户ID映射](https://blog.csdn.net/qq_38665235/article/details/102514649)
```
由于业务数据量较大,单表超亿,根据实际业务对数据库进行分库分表,es中存储主键和账号对应关系,
访问数据库时先送es中获取主键再查db,这样可以直接打到对应分片上秒回

起初打算采用官方提供的工具实现,后续发现官方的不太符合我们的需求(或许是自己没搞明白官方的工具),
并切我们是分库分表的,按照官方的配置太过繁琐.一个表到底层就分了几百张表,所以决定就基于canal client自己实现了同步es,全量和增量.

```
- 静态算法
