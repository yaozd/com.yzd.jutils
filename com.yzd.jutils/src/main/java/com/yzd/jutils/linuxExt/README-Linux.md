- [Linux常用命令大全](https://www.toolfk.com/tool-find-linux)

### [CentOS7使用firewalld打开关闭防火墙与端口](https://www.cnblogs.com/moxiaoan/p/5683743.html)

```
1、firewalld的基本使用
启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld
```
> [在linux下如何使用yum查看安装了哪些软件包](https://blog.csdn.net/wenwenxiong/article/details/51785221)
```
列出所有可安装的软件包 
命令：yum list 
------------------------------
 yum list|grep "kub"
 [root@master ~]# yum list|grep "kub"
 kubernetes-client.x86_64                    1.5.2-0.7.git269f928.el7   @extras  
 kubernetes-master.x86_64                    1.5.2-0.7.git269f928.el7   @extras  
 cockpit-kubernetes.x86_64                   176-4.el7.centos           extras   
 kubernetes.x86_64                           1.5.2-0.7.git269f928.el7   extras   
 kubernetes-node.x86_64                      1.5.2-0.7.git269f928.el7   extras   
 rsyslog-mmkubernetes.x86_64                 8.24.0-34.el7              base     

```