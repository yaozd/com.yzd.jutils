##　分布式唯一id：snowflake算法思考
- [Twitter的分布式自增ID算法snowflake (Java版)](https://www.cnblogs.com/relucent/p/4955340.html)
- [分布式唯一id：snowflake算法思考](https://www.cnblogs.com/lirenzuo/p/8440413.html)
- [从一次 Snowflake 异常说起](https://cloud.tencent.com/developer/article/1074907)
- [理解分布式id生成算法SnowFlake](https://segmentfault.com/a/1190000011282426?utm_source=tag-newest)

### 结构
- 每个节点每毫秒产生4096个ID序号
```
snowflake的结构如下(每部分用-分开):

0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000

第一位为未使用，接下来的41位为毫秒级时间(41位的长度可以使用69年)，然后是5位datacenterId和5位workerId(10位的长度最多支持部署1024个节点） ，最后12位是毫秒内的计数（12位的计数顺序号支持每个节点每毫秒产生4096个ID序号）
```

### 时间回拨问题
