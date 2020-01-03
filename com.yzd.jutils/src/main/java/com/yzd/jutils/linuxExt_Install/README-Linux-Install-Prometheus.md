## 安装Prometheus
- [prometheus + influxdb + grafana + mysql](https://www.cnblogs.com/cheyunhua/p/11376756.html)
### Prometheus查询执行界面：
- [http://192.168.56.101:9090/targets](http://192.168.56.101:9090/targets)
- [http://192.168.56.101:9090/graph](http://192.168.56.101:9090/graph)
  ```
  1.instance维度连接数查看：
  sum by (instance) (hyperspace_connections{endpoint_type="gateway"})
  ```
### Prometheus语法
- [prometheus 之 查询语法（函数列表）](https://blog.csdn.net/qq_25934401/article/details/84561512)
- [Prometheus 查询语言](https://www.jianshu.com/p/3bdc4cfa08da)
- []()
### Prometheus 配置
- [Prometheus 配置采集目标](https://www.cnblogs.com/xiangsikai/p/11288858.html)
- [https://prometheus.io/docs/prometheus/latest/configuration/configuration/](https://prometheus.io/docs/prometheus/latest/configuration/configuration/) -官方配置文档
### Prometheus 重新标签
- [Prometheus 重新标签](https://www.cnblogs.com/xiangsikai/p/11288927.html)
- []()
 
### Prometheus 监控K8S集群资源监控
- [Prometheus 监控K8S集群资源监控](https://www.cnblogs.com/xiangsikai/p/11432919.html)
### 安装方法：
- [https://prometheus.io/download/](https://prometheus.io/download/)
```
1 下载 

https://prometheus.io/download/
2 解压安装
tar -xf prometheus-2.15.1.linux-amd64.tar.gz 
mkdir /usr/local/prometheus
mv prometheus-2.15.1.linux-amd64/* /usr/local/prometheus/
cd /usr/local/prometheus
./prometheus --version
//编辑配置文件
vim prometheus.yml
//启动prometheus
./prometheus
//启动prometheus时，自定义配置文件
/usr/local/prometheus/prometheus --config.file=/usr/local/prometheus/prometheus.yml
```
- 编辑配置文件[vim prometheus.yml]
```
# my global config
global:
  scrape_interval:     5s # Set the scrape interval to every 15 seconds. Default is every 1 minute.
  evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute.
  # scrape_timeout is set to the global default (10s).

# Alertmanager configuration
alerting:
  alertmanagers:
  - static_configs:
    - targets:
      # - alertmanager:9093

# Load rules once and periodically evaluate them according to the global 'evaluation_interval'.
rule_files:
  # - "first_rules.yml"
  # - "second_rules.yml"

# A scrape configuration containing exactly one endpoint to scrape:
# Here it's Prometheus itself.
scrape_configs:
  # The job name is added as a label `job=<job_name>` to any timeseries scraped from this config.
  - job_name: '1722013479'
    # metrics_path defaults to '/metrics'
    # scheme defaults to 'http'.
    static_configs:
      - targets: ['172.20.134.79:9311']
  - job_name: '172206045'
    static_configs:
      - targets: ['172.20.60.45:9311']
remote_write:
  - url: "http://127.0.0.1:8086/api/v1/prom/write?db=prometheus"
remote_read:
  - url: "http://127.0.0.1:8086/api/v1/prom/read?db=prometheus"
```