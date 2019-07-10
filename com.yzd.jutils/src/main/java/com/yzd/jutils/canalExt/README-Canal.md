# CANAL
- [官网](https://github.com/alibaba/canal)
- [QuickStart](https://github.com/alibaba/canal/wiki/QuickStart)
- [aliyun RDS QuickStart-阿里云](https://github.com/alibaba/canal/wiki/aliyun-RDS-QuickStart)
- [ClientAdapter](https://github.com/alibaba/canal/wiki/ClientAdapter)
    - canal 1.1.1版本之后, 增加客户端数据落地的适配及启动功能, 目前支持功能:
    - ES
    - HBASE
    - MYSQL
 - [Canal Kafka RocketMQ QuickStart](https://github.com/alibaba/canal/wiki/Canal-Kafka-RocketMQ-QuickStart)  
 
 ## instance.properties参数列表
- [instance.properties参数列表](https://github.com/alibaba/canal/wiki/AdminGuide)
- canal.instance.filter.regex
    ```
    PS:白名单规则
    订阅实例中所有的数据库和表
    mysql 数据解析关注的表，Perl正则表达式. 
    多个正则之间以逗号(,)分隔，转义符需要双斜杠(\\)
    常见例子：
    1.  所有表：.*   or  .*\\..*
    2.  canal schema下所有表： canal\\..*
    3.  canal下的以canal打头的表：canal\\.canal.*
    4.  canal schema下的一张表：canal\\.test1
    5.  多个规则组合使用：canal\\..*,mysql.test1,mysql.test2 (逗号分隔)
    ```
- canal.instance.filter.black.regex
    ```
     PS:黑名单规则
     订阅实例中所有的数据库和表	
     mysql 数据解析表的黑名单，表达式规则见白名单的规则
    ```

## canal.properties文件-部分参数说明
```
#设置要监听的mysql服务器的地址和端口
canal.instance.master.address = 127.0.0.1:3306
#设置一个可访问mysql的用户名和密码并具有相应的权限，本示例用户名、密码都为canal
canal.instance.dbUsername = canal
canal.instance.dbPassword = canal
#连接的数据库
canal.instance.defaultDatabaseName =test
#订阅实例中所有的数据库和表
canal.instance.filter.regex = .*\\..* 
#连接canal的端口
canal.port= 11111
#监听到的数据变更发送的队列
canal.destinations= example
#连接模式
# tcp, kafka, RocketMQ
canal.serverMode = tcp
```
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
### 数据对象格式简单介绍：EntryProtocol.proto
```
Entry  
    Header  
        logfileName [binlog文件名]  
        logfileOffset [binlog position]  
        executeTime [binlog里记录变更发生的时间戳,精确到秒]  
        schemaName   
        tableName  
        eventType [insert/update/delete类型]  
    entryType   [事务头BEGIN/事务尾END/数据ROWDATA]  
    storeValue  [byte数据,可展开，对应的类型为RowChange]  
RowChange

isDdl       [是否是ddl变更操作，比如create table/drop table]

sql         [具体的ddl sql]

rowDatas    [具体insert/update/delete的变更数据，可为多条，1个binlog event事件可对应多条变更，比如批处理]

beforeColumns [Column类型的数组，变更前的数据字段]

afterColumns [Column类型的数组，变更后的数据字段]


Column

index

sqlType     [jdbc type]

name        [column name]

isKey       [是否为主键]

updated     [是否发生过变更]

isNull      [值是否为null]

value       [具体的内容，注意为string文本]  
```