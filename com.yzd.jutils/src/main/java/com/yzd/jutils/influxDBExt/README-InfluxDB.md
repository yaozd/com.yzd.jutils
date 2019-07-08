> InfluxDBUtils使用参考：
- [https://github.com/yaozd/com.yzd.influxdb.root.git](https://github.com/yaozd/com.yzd.influxdb.root.git)-byArvin推荐
- [InfluxDB使用教程：Java版InfluxDB工具类](https://blog.csdn.net/x541211190/article/details/83216589)
- [sentinel控制台监控数据持久化【InfluxDB】-代码实现-github-地址](https://github.com/yaozd/Sentinel/tree/dev-yzd)
- 工具类静态变量赋值
```
   @Value("${influxdb.url}")
    public void setUrl(String url) {
        InfluxDBUtils.url = url;
    }

    @Value("${influxdb.username}")
    public void setUsername(String username) {
        InfluxDBUtils.username = username;
    }

    @Value("${influxdb.password}")
 ------------------------------------------
 # influxdb settings
 influxdb.url=http://192.168.1.237:8086
 influxdb.username=admin
 influxdb.password=123456
```
- [InfluxDB数据备份与恢复](https://blog.51cto.com/357712148/2165716)

> **开源数据库InfluxDB常用函数**

- [InfluxDB 的函数详解](https://blog.csdn.net/zx711166/article/details/84381077)
- [开源数据库InfluxDB常用函数](https://blog.csdn.net/weixin_36135773/article/details/78789443)
```
SPREAD()函数
返回字段的最小值和最大值之间的差值。数据的类型必须是长整型或float64。
语法：
SELECT SPREAD(<field_key>) FROM <measurement_name> [WHERE <stuff>] [GROUP BY <stuff>]
--------------------- 
使用场景：
计算：GC COUNT(垃圾回收次数)，GC SUM(垃圾回收时间)

SELECT SPREAD("process_uptime_seconds") FROM "microservice_status" WHERE time > now() - 5m GROUP BY time(10s)
```

