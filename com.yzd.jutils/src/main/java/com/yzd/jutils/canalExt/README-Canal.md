# CANAL
- [官网](https://github.com/alibaba/canal)
- [QuickStart](https://github.com/alibaba/canal/wiki/QuickStart)
- [ClientAdapter](https://github.com/alibaba/canal/wiki/ClientAdapter)
    - canal 1.1.1版本之后, 增加客户端数据落地的适配及启动功能, 目前支持功能:
    - ES
    - HBASE
    - MYSQL
 - [Canal Kafka RocketMQ QuickStart](https://github.com/alibaba/canal/wiki/Canal-Kafka-RocketMQ-QuickStart)  

## 准备工作
```
准备
对于自建 MySQL , 需要先开启 Binlog 写入功能，配置 binlog-format 为 ROW 模式，my.cnf 中配置如下

[mysqld]
log-bin=mysql-bin # 开启 binlog
binlog-format=ROW # 选择 ROW 模式
server_id=1 # 配置 MySQL replaction 需要定义，不要和 canal 的 slaveId 重复
注意：针对阿里云 RDS for MySQL , 默认打开了 binlog , 并且账号默认具有 binlog dump 权限 , 不需要任何权限或者 binlog 设置,可以直接跳过这一步
授权 canal 链接 MySQL 账号具有作为 MySQL slave 的权限, 如果已有账户可直接 grant

CREATE USER canal IDENTIFIED BY 'canal';  
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;
FLUSH PRIVILEGES;
``` 
