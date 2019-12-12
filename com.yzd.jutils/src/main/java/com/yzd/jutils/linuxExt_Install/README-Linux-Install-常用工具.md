## 1.Linux install 常用工具
- [CentOS安装操作系统级初始优化](https://www.cnblogs.com/yinzhengjie/p/10467951.html)
- [Linux系统监控命令 (转)](https://www.cnblogs.com/vicowong/p/11702836.html)
- []()

### 1.Install-扩展软件包
```
yum install -y epel-release
```
### 2.Install-常用工具
```
yum -y install  wget net-tools vim tree htop iftop iotop lrzsz sl unzip telnet nmap nc psmisc dos2unix bash-completion nethogs glances
```

### 3.常用工具解释说明
- glances 监视 CPU，平均负载，内存，网络流量，磁盘 I/O，其他处理器 和 文件系统 空间的利用情况 glances
- iftop 用来监控网卡的实时流量（可以指定网段）、反向解析IP、显示端口信息等 iftop
- [psmisc工具](https://blog.csdn.net/mhpsqyq/article/details/56835496) -Psmisc软件包包含三个帮助管理/proc目录的程序。
    ```
    fuser 显示使用指定文件或者文件系统的进程的PID。
    killall 杀死某个名字的进程，它向运行指定命令的所有进程发出信号。
    pstree 树型显示当前运行的进程。
    ```
- lrzsz（Linux服务器和window互传文件工具）
- [nmon](https://www.cnblogs.com/lijiaman/p/9466614.html)  -生哥推荐
    ```
    nmon是以一个用来做linux服务器监控的工具，通过nmon，可以实现对以下参数的监控：
      --CPU使用率
      --内存、交换空间使用率
      --网络使用情况
      --磁盘I/O，读写速度
      --网络I/O速度，传输和读写速度
      --顶级进程，查看哪些应用程序占用的CPU较多
      --网络文件系统NFS
      --文件系统使用情况（类似df -h）
    ```
- []
- []

