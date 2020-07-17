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

### 修改slave读取的位置
- [修改slave读取的位置](https://blog.csdn.net/fst438060684/article/details/82593407)
```
修改conf/examlpe里面的meta.data

journalName=也就是mysql的binlog的名字

position=也就是binlog的位置

timestamp=也就是时间戳

可以通过一下语句找到mysql 的binlog某个位置的具体信息，从而跳过或者进行别的操作：

mysqlbinlog --start-position=281985638 --base64-output=decode-rows -v /pydtdata/mysqllog/binlog/mylogbin.000435>>/pydata/binlog20180906.sql

## --stop-position=400000000
## --database=pydtcredit_prod_2

```

## 案例
- [缓存一致性和跨服务器查询的数据异构解决方案canal](https://www.cnblogs.com/Leo_wl/p/7456892.html)
- [使用canal client-adapter完成mysql到es数据同步教程(包括全量和增量)](https://blog.csdn.net/puhaiyang/article/details/100171395)
- [基于canal client 自实现canal同步es(增量,全量)](https://blog.csdn.net/qq_38665235/article/details/102514649)
    ```
     由于业务数据量较大,单表超亿,根据实际业务对数据库进行分库分表,es中存储主键和账号对应关系,访问数据库时先送es中获取主键再查db,这样可以直接打到对应分片上秒回
    
     起初打算采用官方提供的工具实现,后续发现官方的不太符合我们的需求(或许是自己没搞明白官方的工具),并切我们是分库分表的,按照官方的配置太过繁琐.一个表到底层就分了几百张表,所以决定就基于canal client自己实现了同步es,全量和增量.不过踩了不少得吭.
    
    采用es的bulk api批量更新 .设置自定义模板,后续翻阅官方的源码,其实大致也是这么实现的,只不过官方的具有通用性而已.
    
    ```
- [实战 | canal 实现Mysql到Elasticsearch实时增量同步](https://blog.csdn.net/laoyang360/article/details/88600799)-推荐参考byArvin
```
5、坑
坑1：canal.adapter-1.1.2 启动失败
启动失败：https://github.com/alibaba/canal/issues/1513
该问题在1.1.3版本已经修复。

坑2：不支持全量同步
全量同步建议使用logstash或者其他工具:

坑3：必须先在ES创建好对应索引的Mapping
否则，会没有识别索引，会报写入错误。

坑4：多张表的同步如何实现？
在canal.adapter-1.1.3/conf/es的新增*.yml配置即可。
也就是说，可以一张Mysql表一个配置文件。

坑5：空指针异常错误
解决方案：sql语句部分，指定对应库表id为ES中的_id，否则会报错。
举例：

select sx_sid as _id, name from baidu_info
1
坑6：基于 row 模式的 binlog 会不会记录变更前、变更后的值呢？

INSERT:只有变更后的值。
UPDATE:包含了变更前、变更后的值。
DELETE:变更前的值
关于全量同步：https://github.com/alibaba/canal/issues/376

```
##全量同步