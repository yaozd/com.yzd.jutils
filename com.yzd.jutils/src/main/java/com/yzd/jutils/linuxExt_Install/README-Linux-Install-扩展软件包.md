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