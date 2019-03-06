> docker install
- [Centos7 下 InfluxDB 从安装开始到入门-推荐参考byArvin](https://www.jianshu.com/p/5968e7aa1e1f)
- [influxdb](https://docs.docker.com/samples/library/influxdb/)
```
docker pull library/influxdb
docker images      
docker run -p 8086:8086 -v root:/var/lib/influxdb  influxdb &

---------------
验证：
netstat -ntpl
systemctl status firewalld
systemctl stop firewalld
---------------
http://192.168.1.243:8083
PS:
8083是管理端口-（老版本的才有）
8086是数据端口（如果你使用influxDB studio需使用8086端口 ）

```
- [InfluxDB使用教程：数据库管理工具InfluxDBStudio](https://blog.csdn.net/x541211190/article/details/83152068)
> InfluxDB-SQL语法查询
- [influxDB学习笔记-SQL语法查询-推荐参考byArvin](https://blog.csdn.net/vtnews/article/details/80197045)
- [InfluxDB系列学习教程目录](https://www.linuxdaxue.com/influxdb-study-series-manual.html)

> Influxdb-实例参考
- [influxdb最佳实战-监控对比](https://www.cnblogs.com/iiiiher/p/8046600.html)
- [influxdb+grafana实战-各省份api访问成功率统计](https://www.cnblogs.com/iiiiher/p/8046817.html)
- []()
- []()
- []()
```

```