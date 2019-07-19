## Nginx-服务自动发现

### 设计思路
- 实现Nginx动态配置（nginx动态负载均衡）：Nginx+Upsync+Consul
    - [nginx-upsync-module](https://github.com/weibocom/nginx-upsync-module)
    - [nginx-upsync-module使用](https://blog.csdn.net/shenshouer/article/details/52925735)
    - [使用Nginx实现HTTP动态负载均衡—《亿级流量网站架构核心技术》](https://blog.csdn.net/jek123456/article/details/68059358)
- spring boot启动与优雅退出实现自动注册到consul中
    - 参考：[com.yzd.prometheus.influxdb.root](https://github.com/yaozd/com.yzd.prometheus.influxdb.root)-Grafana+Prometheus打造springboot监控平台
    - []()