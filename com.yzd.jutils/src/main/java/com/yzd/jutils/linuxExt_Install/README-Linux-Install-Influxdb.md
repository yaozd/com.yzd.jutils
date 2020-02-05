## Influxdb
### 下载地址
- [https://portal.influxdata.com/downloads/](https://portal.influxdata.com/downloads/)
- [https://github.com/CymaticLabs/InfluxDBStudio/releases](https://github.com/CymaticLabs/InfluxDBStudio/releases) -InfluxDBStudio
### 安装方法
- [Centos 7 安装InfluxDB](https://blog.csdn.net/qq_34158117/article/details/90597304) -主要参考：byArvin
- [在Linux系统部署InfluxDB](https://blog.csdn.net/qian_feifei/article/details/75139652) 
### Influxdb修改数据保留策略
- [Influxdb修改数据保留策略](https://www.jianshu.com/p/25754f52514d)
```
ps:Influxdb默认的数据保留策略的duration是0s，即长期保留。如果业务上无需长期保留，可以修改数据保留策略。
查看数据库当前策略。
show retention policies on LeeMan_db;
修改数据策略。
alter retention policy "autogen" on "LeeMan_db" duration 4320h replication 1 default;
确认策略修改结果。
show retention policies on LeeMan_db;
```

```
wget https://dl.influxdata.com/influxdb/releases/influxdb-1.7.6.x86_64.rpm
yum -y localinstall influxdb-1.7.6.x86_64.rpm
启动
sudo systemctl start influxdb
查看状态
sudo systemctl status influxdb
停止influxDB
sudo systemctl stop influxdb
```

## 警告：
```
简而言之：
别用prometheus或者influxdb统计userID或者IP。
别用prometheus或者influxdb统计userID或者IP。
别用prometheus或者influxdb统计userID或者IP。
重要的事说三遍。

可是，万一真的有需求要统计这些，怎么办？

撕逼需求方，一哭二闹三上吊，让他们取消这个需求
用另外一个神器呀， Elasticsearch， 你值得拥有
可能有更好的，免费的，容易维护的解决方法。请留言指教，感激不尽

作者：EagleChan
链接：https://www.jianshu.com/p/56869368e361
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```