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


---------------------

---------------------