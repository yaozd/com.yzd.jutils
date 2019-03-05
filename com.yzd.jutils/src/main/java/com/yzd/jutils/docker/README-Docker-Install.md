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
- []()
```

```