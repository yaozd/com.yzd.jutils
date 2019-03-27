
> [如何获取 docker 容器(container)的 ip 地址](https://blog.csdn.net/sannerlittle/article/details/77063800)
```
1. 进入容器内部后
cat /etc/hosts
1
会显示自己以及(– link)软连接的容器IP

2.使用命令
docker inspect --format '{{ .NetworkSettings.IPAddress }}' <container-ID> 
或 
docker inspect <container id> 
或 
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' container_name_or_id

3.可以考虑在 ~/.bashrc 中写一个 bash 函数：
function docker_ip() {
    sudo docker inspect --format '{{ .NetworkSettings.IPAddress }}' $1
}

source ~/.bashrc 然后：

$ docker_ip <container-ID>
172.17.0.6

4.要获取所有容器名称及其IP地址只需一个命令。
docker inspect -f '{{.Name}} - {{.NetworkSettings.IPAddress }}' $(docker ps -aq)

如果使用docker-compose命令将是：

docker inspect -f '{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)

5.显示所有容器IP地址：
docker inspect --format='{{.Name}} - {{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $(docker ps -aq)
--------------------- 
```