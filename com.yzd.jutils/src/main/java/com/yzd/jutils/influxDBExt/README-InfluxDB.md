> InfluxDBUtils使用参考：
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