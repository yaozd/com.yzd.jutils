### 通过职责链-来自byArvin-2018-09-11
- [设计模式之职责链](http://blog.51cto.com/zero01/2065240)

```
1.获取一个根节点
2.把根节点放到消息队列之中
3.监控消息队列中的数据
4.通过职责链进行业务的回滚，撤销等操作
细节：
数据库中有一个日志表
然后记录每一步的入口参数与根节点
再通过职责链进行业务的回滚，撤销等相关操作
参考：
[微服务架构下分布式事务解决方案——阿里GTS](https://www.cnblogs.com/jiangyu666/p/8522547.html)
[EasyTransaction](https://github.com/QNJR-GROUP/EasyTransaction)
```
###[工作中的一些设计实践，合适不合适仁者见仁吧](https://github.com/jiangmin168168/jim-framework)
```
状态模式
职责链模式，同事设计的处理复杂订单流程。
建造者，策略模式。新旧价格转换过程中的尝试。
```
