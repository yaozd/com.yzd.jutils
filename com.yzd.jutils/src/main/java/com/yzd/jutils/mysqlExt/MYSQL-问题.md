###A.问题
- [mysql sleep连接过多的完美解决办法](https://www.cnblogs.com/centos-python/articles/8522127.html)
```
当然，更根本的方法，还是从以上三点排查之：
1.　程序中，不使用持久链接，即使用mysql_connect而不是pconnect。
2.   程序执行完毕，应该显式调用mysql_close
3.　只能逐步分析系统的SQL查询，找到查询过慢的SQL,优化之
```

###A.问题

JDBC中The server time zone value '???ú±ê×??±??' is ............. 的错误
出现这个的原因是因为 mysql返回的时间总是有问题，比实际时间要早8小时--问题解决
https://blog.csdn.net/weixin_37577564/article/details/80329775
---------------------
解决办法

public static final  String URL="jdbc:mysql://localhost:3306/jdbc01?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";//链接的mysql

在jdbc连接的url后面加上serverTimezone=GMT即可解决问题，如果需要使用gmt+8时区，需要写成GMT%2B8

问题就会解决；

---------------------
###A.问题

mysql数据库error: Found option without preceding group in config file 问题解决
https://blog.csdn.net/wyx100/article/details/52859381
---------------------
原因：my.ini文件格式是utf-8

解决办法，my.ini文件保存为ANSI格式文件

---------------------
###A.问题
MySQL启动1053错误解决方法
https://www.jb51.net/article/31802.htm
>mysqld -nt remove mysqlservice
>mysqld -nt install mysqlservice

---------------------
###A.问题
mysql column which is not functionally dependent GROUP BY clause
https://blog.csdn.net/Himire/article/details/80249360

sql_mode=STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION

复制sql_mode去掉ONLY_FULL_GROUP_BY
3.修改my.ini在[mysqld]下加入sql_mode
4.net stop mysql
5.net start mysql
--------------------

### mysql max_allowed_packet查询和修改
```
mysql根据配置文件会限制server接受的数据包大小。
有时候大的插入和更新会被max_allowed_packet 参数限制掉，导致失败。
查看目前配置  
show VARIABLES like '%max_allowed_packet%';
-------
mysql max_allowed_packet 默认值（字节）：1024 （为1k）
===
阿里云数据库导出的数据库备份sql的中insert的合并大小5m
所以建议把max_allowed_packet的值调整为16777216 （16M） 

在线文件大小(bit,bytes,KB,MB,GB,TB)转换换算
http://www.bejson.com/convert/filesize/

```
### [Mybatis 中$与#的区别](https://www.cnblogs.com/hellokitty1/p/6007801.html)
```
#与的区别最大在于：#{} 传入值时，sql解析时，参数是带引号的，而{}穿入值，sql解析时，参数是不带引号的
-------
二:使用$与#
   #{}: 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符，一个 #{ } 被解析为一个参数占位符 。
   ${}: 仅仅为一个纯碎的 string 替换，在动态 SQL 解析阶段将会进行变量替换。
 eg: 
  select id,name,age from student where name=#{name}   -- name='cy'
  select id,name,age from student where name=${name}    -- name=cy
```
### MYSQL-索引-最左原则

### [MyBatis中Like语句使用方式](https://www.cnblogs.com/icewee/articles/6927841.html)
```
SELECT * FROM user
WHERE name LIKE CONCAT('%',#{name},'%')
PS:CONCAT的方式目前测试也是可以走索引的。
```
---------------------

---------------------