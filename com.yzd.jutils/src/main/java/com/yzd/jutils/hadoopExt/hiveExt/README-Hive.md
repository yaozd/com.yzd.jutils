### Hive学习参考-数据倾斜问题
> [[Hive]Hive数据倾斜（大表join大表）](https://blog.csdn.net/yeweiouyang/article/details/45665727)
```
第二次优化效果效果不理想的原因，其实是在左关联中，虽然设置了左表关联字段为空不去关联右表，但是这样做，左表中未关联的记录（ext_field7为空）将会全部聚集在一个reduce中进行处理，体现为reduce进度长时间处在99%。 
换一种思路，解决办法的突破点就在于如何把左表的未关联记录的key尽可能打散，因此可以这么做：若左表关联字段无效（为空、字段长度为零、字段填充了非整数），则在关联前将左表关联字段设置为一个随机数，再去关联右表，这么做的目的是即使是左表的未关联记录，它的key也分布得十分均匀
--------------------- 
```
> [Hive调优总结——数据倾斜，join表连接优化-推荐参考byArvin-必读](https://blog.csdn.net/u012715448/article/details/78327618)
```
数据倾斜即为数据在节点上分布不均，是常见的优化过程中常见的需要解决的问题。常见的Hive调优的方法：列剪裁、Map Join操作、 Group By操作、合并小文件。
一、表现
      1.任务进度长度为99%，在任务监控页面中发现只有几个 reduce 子任务未完成；
      2.单一 reduce 记录与平均记录数差异过大（大于3倍），最长时长＞＞平均时长；
      3.job数多的，效率低，多次关联后，产生几个jobs，起码半小时以上才跑完；
二、原因
      1.key分布不均；
      2.业务数据本身问题；
      3.建表有问题；
      4.sql语句本身数据倾斜；
```
> [20亿与20亿表关联优化方法(超级大表与超级大表join优化方法)](https://blog.csdn.net/robinson1988/article/details/50756921)
```
记得5年前遇到一个SQL，就是一个简单的两表关联，SQL跑了差不多一天一夜，这两个表都非常巨大，每个表都有几十个G，数据量每个表有20多亿，表的字段也特别多。
相信大家也知道SQL慢在哪里了，单个进程的PGA 是绝对放不下几十个G的数据，这就会导致消耗大量temp tablespace，SQL慢就是慢在temp来回来回来回...的读写数据。  
--------------------- 
原因：数据量太大，无法放到服务器内存表中
解决方案：通过哈希进行切割，加一个字段HASH_VALUE。
之前运行的 select * from t1,t2 where t1.object_id=t2.object_id  其实就等价于下面5个SQL了
select * from p1,p2 where p1.object_id=p2.object_id and p1.hash_value=0 and p2.hash_value=0;
select * from p1,p2 where p1.object_id=p2.object_id and p1.hash_value=1 and p2.hash_value=1;
select * from p1,p2 where p1.object_id=p2.object_id and p1.hash_value=2 and p2.hash_value=2;
select * from p1,p2 where p1.object_id=p2.object_id and p1.hash_value=3 and p2.hash_value=3;
select * from p1,p2 where p1.object_id=p2.object_id and p1.hash_value=4 and p2.hash_value=4;
--------------------- 
通过哈希进行切割，变大为小。
```
> [用sqoop将mysql的数据导入到hive表中](http://www.cnblogs.com/xuyou551/p/7998846.html)

> [在hive中分析数据然后将数据导出到mysql中练习](https://blog.csdn.net/qq_37662746/article/details/78186828)

> [将Hive统计分析结果导入到MySQL数据库表中（二）——使用Hive和MySQL JDBC驱动](https://blog.csdn.net/NIITYZU/article/details/45198531)
```

```
> []()
```

```