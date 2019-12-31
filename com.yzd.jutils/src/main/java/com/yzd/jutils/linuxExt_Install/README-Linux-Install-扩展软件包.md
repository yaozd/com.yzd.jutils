##　１．Linux-Install-扩展软件包
- [Linux 安装 EPEL YUM源](https://www.cnblogs.com/lfxiao/p/9297912.html)
- [Centos7安装yum及EPEL扩展源*](https://www.jianshu.com/p/dbed88288153)
- redis的所有版本下载
    - [http://download.redis.io/releases/](http://download.redis.io/releases/)
```

cd /etc/yum.repos.d/
mkdir repo_bak
mv *.repo repo_bak/
wget http://mirrors.aliyun.com/repo/Centos-7.repo
wget http://mirrors.163.com/.help/CentOS7-Base-163.repo
yum clean all
yum makecache
yum install -y epel-release
wget -O /etc/yum.repos.d/epel-7.repo http://mirrors.aliyun.com/repo/epel-7.repo
yum clean all
yum makecache
```
- [CentOS 7笔记yum install epel-release](https://blog.csdn.net/liuqun69/article/details/101461788)

```

sudo yum install epel-release
sudo yum repolist
sudo yum check-update
```

##　１．Linux-Install-扩展软件包
- [yum安装htop报错解决过程](https://blog.51cto.com/2617796/2057834)　－No package htop available.

```
No package htop available.
Error: Nothing to do
我知道应该是没有安装成功的，但还是抱着侥幸的心理，输入htop命令，系统提示
[root@oldboy ~]# htop
-bash: htop: command not found
至此，最终肯定htop没有安装成功，于是就开始查找没有安装上的原因。
由于htop是一个扩展工具，是一个强大的进程管理前端工具，一般在centos系统源中没有，
所以需要去fedora-epel（epel extra packages for enterprises linux 企业级linux扩展软件包）中下载。
所以先安装 epel-release
```
##　解决办法
```

步骤一：yum install -y epel-release
步骤二：yum install -y htop
```