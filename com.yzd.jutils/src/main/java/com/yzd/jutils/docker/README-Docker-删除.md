## README-Docker-删除
- [如何批量删除Docker中已经停止的容器](https://blog.csdn.net/jiangeeq/article/details/79499324)
- [快速删除docker中的容器](https://blog.csdn.net/cmzsteven/article/details/49230363)
- []()
```
1、停用全部运行中的容器:
docker stop $(docker ps -q)
2、删除全部容器：
docker rm $(docker ps -aq)
3、一条命令实现停用并删除容器：
docker stop $(docker ps -q) & docker rm $(docker ps -aq)
方法一：
#显示所有的容器，过滤出Exited状态的容器，取出这些容器的ID，
sudo docker ps -a|grep Exited|awk '{print $1}'
#查询所有的容器，过滤出Exited状态的容器，列出容器ID，删除这些容器
sudo docker rm `docker ps -a|grep Exited|awk '{print $1}'`
方法二： 
#删除所有未运行的容器（已经运行的删除不了，未运行的就一起被删除了）
sudo docker rm $(sudo docker ps -a -q)
//方法三
#根据容器的状态，删除Exited状态的容器
sudo docker rm $(sudo docker ps -qf status=exited)
//方法四：
#Docker 1.13版本以后，可以使用 docker containers prune 命令，删除孤立的容器。
sudo docker container prune
//
02-删除所有的容器，所有未运行的容器都被删除，正在运行的无法删除，达到删除不用容器的目的。
03-低于1.13版本的Docker，可以根据容器的状态来进行删除
04-查询所有的容器，过滤出状态为Exited的容器
05-Docker 1.13版本以后，开始支持prune命令，快速删除已退出的容器

```