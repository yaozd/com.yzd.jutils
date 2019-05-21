### 1.hive-可视化工具
- [hive数据库连接可视化工具DBeaver使用](http://itxw.net/article/238.html)

### hive-安装参考
- []()
- []()
- []()
- []()
### hive-site.xml --配置文件调整
- 元数据存储-DERBY转为MYSQL
    ```
    <property>
      <name>hive.druid.metadata.db.type</name>
      <value>mysql</value>
      <description>
        Expects one of the pattern in [mysql, postgresql, derby].
        Type of the metadata database.
      </description>
    </property>
    ``` 
- 数据库访问驱动调整
    ```
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
    <description>Driver class name for a JDBC metastore</description>
  </property>
    ```
- hive.server2.thrift.bind.host
    ```
      <property>
        <name>hive.server2.thrift.bind.host</name>
        <value>0.0.0.0<value/>
        <description>Bind host on which to run the HiveServer2 Thrift service.</description>
      </property>
    ```
- []()
- []()
### 2.问题-hive beeline 连接 User: root is not allowed to impersonate root
- [hive beeline 连接 User: root is not allowed to impersonate root](https://ask.csdn.net/questions/758059)
- [beeline连接hiveserver2报错：User: root is not allowed to impersonate root](https://blog.csdn.net/qq_16633405/article/details/82190440)
- []()
### 3.[[Hive]那些年我们踩过的Hive坑](https://blog.csdn.net/sunnyyoona/article/details/51648871)
- []()

### 4.Hive database does not exist 排查
- [Hive database does not exist 排查](https://segmentfault.com/a/1190000017271897?utm_source=tag-newest)
```
jar包：
mysql-connector-java-5.1.47.jar
```
