
### [容器(docker)中运行java需关注的几个小问题](http://ifeve.com/%E5%AE%B9%E5%99%A8docker%E4%B8%AD%E8%BF%90%E8%A1%8Cjava%E9%9C%80%E5%85%B3%E6%B3%A8%E7%9A%84%E5%87%A0%E4%B8%AA%E5%B0%8F%E9%97%AE%E9%A2%98/)
- java不知道自己运行在container里，以为它看到的资源都能用。结果：java工作在资源充足的
- jvm检测可用cpu个数来优化运行时，影响jvm后台做的一些决策。

#### docker安装启动
```
yum install -y docker
8、启动并加入开机启动
$ sudo systemctl start docker
$ sudo systemctl enable docker
9、验证安装是否成功(有client和service两部分表示docker安装启动都成功了)
$ docker version
```

#### docker-compose安装启动
```
https://github.com/docker/compose/releases
//
安装
curl -L https://github.com/docker/compose/releases/download/1.23.2/docker-compose-`uname -s`-`uname -m` -o /usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

####　docker常用命令
```
docker pull soyking/e3w
docker images -a
docker ps
列出所有容器
docker container ls
-------------------- 
Docker常用命令
https://www.cnblogs.com/yaohong/p/7842499.html
简略：
容器生命周期：run、start/stop/restart、kill、rm、pause/unpause、create、exec
容器操作：ps、inspect、top、attach、events、logs、wait、export、port
容器rootfs：commit、cp、diff
镜像仓库：login/logout、pull、push、search
本地镜像管理：images、rmi、tag、build、history、save、import
info|version：info、version       【docker info/vaersion分别查看系统信息和版本信息】 
```
