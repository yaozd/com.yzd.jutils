> Grafana-数据源连接参考
- [Grafana +MySQL Data Source](https://blog.csdn.net/tomcatRun/article/details/79654046)
```
SELECT
  UNIX_TIMESTAMP(Date_Time) as time_sec,
  Total_Current as value,
  Timer_Id as metric
FROM workinfo
WHERE $__timeFilter(Date_Time)
ORDER BY Date_Time ASC
--------------------- 
MYSQL-求总数
SELECT count(*) FROM tb_user_details WHERE ud_is_del=1
--------------------- 
MYSQL-求百分比
SELECT total/total_us_card from (SELECT count(1) as total from tb_user_details where ud_is_del=1) T1,(SELECT count(1) as total_us_card from tb_user_details where ud_bank_card_no is not null and ud_is_del=1)T2;
---------------------
mysql 按照月份统计 
select ud_create_time AS "time",count(*) AS total  FROM tb_user_details WHERE  $__timeFilter(ud_create_time) and ud_is_del=1 GROUP BY DATE_FORMAT(ud_create_time, '%Y-%m')
==
select ud_create_time AS "time",count(*) AS total  FROM tb_user_details WHERE  ud_create_time BETWEEN FROM_UNIXTIME(1537850411) AND FROM_UNIXTIME(1553488811) and ud_is_del=1 GROUP BY DATE_FORMAT(ud_create_time, '%Y-%m')
--------------------- 
```
- []()
```

```