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

---------------------