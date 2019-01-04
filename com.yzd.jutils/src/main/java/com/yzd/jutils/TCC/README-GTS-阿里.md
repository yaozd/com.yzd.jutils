### 阿里-GTS-分布式事务
- [微服务架构下分布式事务解决方案——阿里GTS](https://www.cnblogs.com/jiangyu666/p/8522547.html)

> [GTS确实很赞，其核心原理是 补偿](https://www.cnblogs.com/jiangyu666/p/8522547.html)
```
但这个补偿做得很屌，补偿操作由框架自动生成，无需业务干预，框架会记录修改前的记录值到上面的txc_undo_log里，若需要回滚，则拿出undo_log的记录覆盖回原有记录

同时这里存在一个事务隔离级别的问题，GTS的做法是默认脏读，那么就可以直接拿数据库记录展示（但个人觉得应该可以不做脏读，直接拿undo_log里的记录做mvcc,只要undo_log记录不大，都可以加载到内存里）。

还有另外一个问题是如何禁止其他事务对进行中的全局事务记录的更新，GTS的做法是需要接管APP中的数据源，这样就可以解析控制业务要执行的SQL，对于update操作（或者select for update），予以禁止或等待。

不过整体的做法相当于魔改数据库，将数据库的部分功能拉到了业务APP里进行，并修改了默认隔离级别（脏读，如果业务有用数据库记录乐观锁来控制并发的话，将会失效），还有就是，不通过GTS的定制数据源访问会访问修改到未提交数据

如果作者能自行介绍下GTS的优缺点会更方便，更权威，毕竟大家做选型肯定要了解原理才敢用

还有这里也有个我自己写的分布式事务框架，欢迎大家喵喵 https://github.com/QNJR-GROUP/EasyTransaction
```
> GTS的使用方式
```
在业务函数外围使用@TxcTransaction注解即可开启分布式事务。
Dubbo应用通过隐藏参数将GTS的事务xid传播到服务端。
-----------------------------------
@TxcTransaction(timeout = 1000 * 10)
public void Bussiness(OrderService orderService, StockService stockService, String userId) {
    //获取事务上下文
    String xid = TxcContext.getCurrentXid();
    //通过RpcContext将xid传到一个服务端
    RpcContext.getContext().setAttachment("xid", xid);
    
    //执行自己的业务逻辑
    int productId = new Random().nextInt(100);
    int productNum = new Random().nextInt(100);
    OrderDO orderDO = new OrderDO(userId, productId, productNum, new Timestamp(new Date().getTime()));
    orderService.createOrder(orderDO);
    
    //通过RpcContext将xid传到另一个服务端
    RpcContext.getContext().setAttachment("xid",xid);
    stockService.updateStock(orderDO);
}
```
> 建表 txc_undo_log
```
CREATE TABLE txc_undo_log (

id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',

gmt_create datetime NOT NULL COMMENT '创建时间',

gmt_modified datetime NOT NULL COMMENT '修改时间',

xid varchar(100) NOT NULL COMMENT '全局事务ID',

branch_id bigint(20) NOT NULL COMMENT '分支事务ID',

rollback_info longblob NOT NULL COMMENT 'LOG',

status int(11) NOT NULL COMMENT '状态',

server varchar(32) NOT NULL COMMENT '分支所在DB IP',

PRIMARY KEY (id),

KEY unionkey (xid,branch_id)

) ENGINE=InnoDB AUTO_INCREMENT=211225994 DEFAULT CHARSET=utf8 COMMENT='事务日志表';
```