## *Linux常用命令*
- [能解决95%以上的问题的 Linux 常用命令！](https://mp.weixin.qq.com/s/OGe3VhvFtxtzGHJvvmlBiQ)
- [Java开发必会的Linux命令](https://mp.weixin.qq.com/s/2KUUQ4KvSkIBsJFtdmU0fQ)
- []()
- [linux下添加链接与删除链接（ln命令的用法）](https://www.cnblogs.com/sign-ptk/p/6207936.html)
    ```
    添加
    ln -s /usr/jboss4.0.5.GA/ jboss
    删除
    rm jboss
    最后 删除符号链接，有创建就有删除
    rm -rf   symbolic_name   注意不是rm -rf   symbolic_name/ 
    ```
-  查看当前文件夹大小
    > du -sh
-  TCP 连接数查看
    ```
    ==PID:1454
    netstat -ntp|grep ES|grep 1454
    netstat -nt|grep ES  |wc -l
    netstat -nt|grep ES | grep 172.20.227.113:80 |wc -l
    netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a, S[a]}'
    每隔1s显示连接数的变量：
    watch -n 1 -d 'netstat -nt|grep ES|wc -l'
    watch -n 1 -d 'curl http://127.0.0.1:9311 |grep "0.0.0.0"'
    ==================
    PS:回收连接
    cat /etc/sysctl.conf
    //
    net.ipv4.tcp_keepalive_time = 60
    net.ipv4.tcp_fin_timeout = 30
    //
    apply：sysctl -p
    ```
- 快速查看进程启动时间和运行时间
    ```
    ps -eo pid,lstart,etime,cmd
    ```
### [CentOS7使用firewalld打开关闭防火墙与端口](https://www.cnblogs.com/moxiaoan/p/5683743.html)

```
1、firewalld的基本使用
启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld
```