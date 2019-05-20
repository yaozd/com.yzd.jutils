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