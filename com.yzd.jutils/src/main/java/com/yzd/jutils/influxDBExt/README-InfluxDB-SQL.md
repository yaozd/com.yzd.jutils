> README-InfluxDB-SQL 查询语句
> InfluxDB-SQL语法查询
- [influxDB学习笔记-SQL语法查询-推荐参考byArvin](https://blog.csdn.net/vtnews/article/details/80197045)
- [InfluxDB系列学习教程目录](https://www.linuxdaxue.com/influxdb-study-series-manual.html)
- [InfluxDB数据备份与恢复](https://blog.51cto.com/357712148/2165716)


0. 字符串字段查询--必须使用单引号
```
PS:userName = '15010223310'
SELECT * FROM "productdetail_metric"  WHERE resourceCode=3046 and userName = '15010223310'
```
1. 产品详情-用户访问率
```
PS:
Type:Table
SQL:SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter GROUP BY "userName"
```
2. 访问总数
```
PS:
Type:Singlestat
SQL:SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter fill(null)
```
3. 产品详情-访问趋势
```
PS:
Type:Graph
SQL:
SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter GROUP BY time($__interval) fill(null)
SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter GROUP BY time(5m) fill(null)
```
4. 独立用户数
```
PS:
SQL:SELECT  COUNT(DISTINCT(userId)) FROM "productdetail_metric" WHERE $timeFilter fill(null)
```
5.[InfluxDB删除数据](https://blog.51cto.com/3922078/2316723)
```
PS:
Delete FROM "productdetail_metric" WHERE time >= 1552838400000ms and time <= 1553443199999ms AND userName='aaa'
```



- [InfluxDB使用教程：数据库管理工具InfluxDBStudio](https://blog.csdn.net/x541211190/article/details/83152068)
> Influxdb-实例参考
- [influxdb最佳实战-监控对比](https://www.cnblogs.com/iiiiher/p/8046600.html)
- [influxdb+grafana实战-各省份api访问成功率统计](https://www.cnblogs.com/iiiiher/p/8046817.html)
- []()
- []()
- []()
```