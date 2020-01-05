## Grafana
### 下载地址
- [https://grafana.com/grafana/download](https://grafana.com/grafana/download)
```
wget https://dl.grafana.com/oss/release/grafana-6.5.2-1.x86_64.rpm
sudo yum -y localinstall grafana-6.5.2-1.x86_64.rpm
systemctl enable grafana-server
//
systemctl start grafana-server
systemctl stop grafana-server
systemctl status grafana-server
grafana-server -v 

grafana-server 监听端口为 3000
3 访问 grafana-server 

http://192.168.56.111:3000
http://ServerIP:3000
默认用户名密码为： admin admin
```
### 安装插件
- Pie Chart
    ```
    https://grafana.com/grafana/plugins/grafana-piechart-panel
    grafana-cli plugins install grafana-piechart-panel
    ```