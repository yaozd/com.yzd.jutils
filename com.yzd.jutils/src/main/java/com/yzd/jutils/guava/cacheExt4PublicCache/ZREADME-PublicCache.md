## 公共缓存数据资源访问统计
- 目的：实现热点访问数据的自动扩容。

## 基本思路
```
1.guava-cache 收集访问统计
2.DataRepository-ArrayBlockingQueue 进行批量数据上传influxdb
3.influxdb统计汇总，计算出副本数量，实现自动扩容。
-----------------
参考：
批量导入实现
com.yzd.jutils.batchDataExt.BatchInsertTest
```