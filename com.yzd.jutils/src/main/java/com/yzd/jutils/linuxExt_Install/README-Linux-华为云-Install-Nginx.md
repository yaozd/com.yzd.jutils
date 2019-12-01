## 华为云-Install-Nginx
- [华为云-Install-Nginx](https://www.huaweicloud.com/kunpeng/software/nginx.html) -推荐参考byArvin
- [Linux下安装Nginx](https://www.jianshu.com/p/9f2c162ac77c
```
1.      获取源代码

软件获取路径：http://nginx.org/download/

nginx官网：http://nginx.org/

2.      编译环境配置

执行如下命令，安装依赖包。

yum install gcc gcc-c++ make unzip pcre pcre-devel zlib zlib-devel libxml2 libxml2-devel  readline readline-devel ncurses ncurses-devel perl-devel perl-ExtUtils-Embed openssl-devel -y

3.      编译源代码

1)     执行以下命令，获取安装包。

wget -c http://nginx.org/download/nginx-1.16.0.tar.gz

2)     执行以下命令，解压安装包。

tar -zxvf nginx-1.16.0.tar.gz

3)     执行以下命令，进入安装目录。

cd nginx-1.16.0

4)     执行以下命令，编译安装nginx。

./configure

make -j4 && make install

4.      测试已完成编译的软件

1)     新增nginx用户

useradd nginx

2)     执行以下命令，给nginx用户开启nginx安装目录权限。

chown nginx:nginx /usr/local/nginx

3)     执行如下命令，查看nginx版本。

cd /usr/local/nginx/sbin/

./nginx -v

```
## 1、nginx验证
```
nginx主配置文件：/usr/local/nginx/conf/nginx.conf
nginx日志文件：/usr/local/nginx/logs/access.log
启动Nginx：/usr/local/nginx/sbin/nginx

作者：若汐缘
链接：https://www.jianshu.com/p/9f2c162ac77c
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
```
## 1、nginx部分使用命令
```
部分命令如下：
启动
[root@ecs-node-0001 nginx]#  /usr/local/nginx/sbin/nginx

重启：
[root@ecs-node-0001 nginx]#  /usr/local/nginx/sbin/nginx –s reload

停止：
[root@ecs-node-0001 nginx]#  /usr/local/nginx/sbin/nginx –s stop

测试配置文件是否正常：
[root@ecs-node-0001 nginx]#  /usr/local/nginx/sbin/nginx –t

强制关闭：
[root@ecs-node-0001 nginx]#  pkill nginx

查看进程命令：
ps -ef | grep nginx

平滑重启：
kill -HUP [Nginx主进程号(即ps命令查到的PID)]
```
## 2、firewalld的基本使用
```

启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld
```