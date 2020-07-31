# SQL优化-EXPLAIN,PROFILING,OPTIMIZER_TRACE
- [我说 SELECT COUNT(*) 会造成全表扫描，面试官让我回去等通知](https://www.jianshu.com/p/9ea7ae51d01a)
- [【MySQL优化】——看懂explain](https://blog.csdn.net/jiadajing267/article/details/81269067)
- [MySql--optimizer trace 表的神奇功效](https://www.jianshu.com/p/161f634f5db3)

## explain
```
explain select host,user,plugin from user
```
## profiling
```
使用profiling详细的列出在每一个步骤消耗的时间，前提是先执行一遍语句。

#打开profiling 的设置
SET profiling = 1;
SHOW VARIABLES LIKE '%profiling%';

#查看队列的内容
show profiles;  
#来查看统计信息
show profile block io,cpu for query 3;
```
## optimizer (5.6以后才有)
```
#打开设置
SET optimizer_trace='enabled=on';  
#最大内存根据实际情况而定， 可以不设置
SET OPTIMIZER_TRACE_MAX_MEM_SIZE=1000000;
SET END_MARKERS_IN_JSON=ON;
SET optimizer_trace_limit = 1;
SHOW VARIABLES LIKE '%optimizer_trace%';

#执行所需sql后，查看该表信息即可看到详细的执行过程
SELECT * FROM `information_schema`.`OPTIMIZER_TRACE`;
```