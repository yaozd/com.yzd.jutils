### 分布式事务研究
- [微服务架构下分布式事务解决方案——阿里GTS-byArvin推荐](https://www.cnblogs.com/jiangyu666/p/8522547.html)
- [消息事务+最终一致性](https://blog.csdn.net/mine_song/article/details/64118963)
- [EasyTransaction-byArvin推荐](https://github.com/QNJR-GROUP/EasyTransaction)
- [LCN分布式事务框架v4.0](https://github.com/codingapi/tx-lcn)
- [分布式事务解决方案框架（LCN）](https://www.jianshu.com/p/73beee3c70e9)
- [tmycat1-分布式事务的一种实现方式——状态流转](https://github.com/yu199195/tmycat1)
- [myth-基于可靠消息最终一致性分布式事务框架](https://github.com/yu199195/myth)

可以借鉴阿里GTS的思路：
```
可以借鉴阿里GTS的思路
@TxcTransaction(timeout = 1000 * 10)
public void Bussiness(OrderService orderService, StockService stockService, String userId) {
    //获取事务上下文
    String xid = TxcContext.getCurrentXid();
    //通过RpcContext将xid传到一个服务端
    RpcContext.getContext().setAttachment("xid", xid);
==
//获取全局事务ID，并绑定到上下文
String xid = RpcContext.getContext().getAttachment("xid");
TxcContext.bind(xid,null);
//执行自己的业务逻辑
int ret = jdbcTemplate.update("update stock set amount = amount - ? where product_id = ?",new Object[]{orderDO.getNumber(), orderDO.getProductId()});
TxcContext.unbind();
```

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
职责键模式，同事设计的处理复杂订单流程。
建造者，策略模式。新旧价格转换过程中的尝试。
```