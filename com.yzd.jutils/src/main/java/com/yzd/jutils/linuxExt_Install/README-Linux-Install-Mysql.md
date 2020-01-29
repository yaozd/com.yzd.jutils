## CentOS 7.0下使用yum安装MySQL
1. 下载并安装MySQL官方的 Yum Repository
> $ wget -i -c http://dev.mysql.com/get/mysql57-community-release-el7-10.noarch.rpm
2. 使用上面的命令就直接下载了安装用的Yum Repository，大概25KB的样子，然后就可以直接yum安装了。
> $ yum -y install mysql57-community-release-el7-10.noarch.rpm  
3. 开始安装MySQL服务器。
> $ yum -y install mysql-community-server
4. MySQL就安装完成,配置mysql

##MySQL就安装完成,配置mysql
- 重置mysql密码
> $ mysql -u root
```
登录时有可能报这样的错：
ERROR 2002 (HY000): Can‘t connect to local MySQL server through socket ‘/var/lib/mysql/mysql.sock‘ (2)
原因是/var/lib/mysql的访问权限问题。
下面的命令把/var/lib/mysql的拥有者改为当前用户：
```
- 把/var/lib/mysql的拥有者改为当前用户：
> $ sudo chown -R root:root /var/lib/mysql
- 重启mysql服务
> $ service mysqld restart

- 接下来登录重置密码：
- [跳过MySQL的密码认证过程](https://blog.csdn.net/fmwind/article/details/81941790)
```
参考：Linux重置MySQL密码

cat /etc/my.cnf
vi /etc/my.cnf
==
在[mysqld]后面任意一行添加“skip-grant-tables”用来跳过密码验证的过程
```
- 重启mysql服务
> service mysqld restart
- 关闭防火墙
> systemctl stop firewalld
- 接下来登录重置密码：
```
$ mysql -u root  //直接回车进入mysql控制台
mysql > use mysql;
mysql > update user set password=password('新密码') where user='要更新密码的用户名';
mysql > flush privileges;
mysql > exit;
```
- 再次登录用
```
$ mysql -uroot -p
enter password:（直接输入新密码即可，然后回车）
```

## 参考：
- [CentOS 7.0下使用yum安装MySQL](https://segmentfault.com/a/1190000015216149)
- [Linux重置MySQL密码](https://blog.csdn.net/fmwind/article/details/81941790)
- [【mysql】linux下mysql报Failed to restart mysqld.service: Unit not found](https://blog.csdn.net/qq_31083947/article/details/90248565)
    ```
    查看服务列表：
    chkconfig --list  查询服务列表
    ```