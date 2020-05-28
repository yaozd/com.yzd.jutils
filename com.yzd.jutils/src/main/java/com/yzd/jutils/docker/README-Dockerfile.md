## docker file 

### 参考
- [Docker部署运行springboot项目，并使用Dockerfile制作镜像](https://www.cnblogs.com/spll/p/10059542.html)
- [你必须知道的Dockerfile](https://www.cnblogs.com/edisonchou/p/dockerfile_inside_introduction.html)
- [SpringBoot之Dockerfile应用](https://blog.csdn.net/catoop/article/details/89645254)
    - [https://gitee.com/catoop/springboot-docker/tree/master](https://gitee.com/catoop/springboot-docker/tree/master)


### Testing with Docker 
- demo 下的docker文件夹下的文件部署在本地docker测试用的
```                                   
docker run -p 80:80 --name mynginx -d nginx
docker build . -t demo      
docker-compose -f .\app.yml up -d       
``` 
- docker-compose app.yml
```
version: '2'
services:
  demo-app1:
    image: delay-queue
    environment:
      - JAVA_OPTS=-Dserver.port=9000
    ports:
      - 9000:9000
  demo-app2:
    image: delay-queue
    environment:
      - JAVA_OPTS=-Dserver.port=9001
    ports:
      - 9001:9001
```
### Docker-完整示例
- spring boot
```
com.yzd.prometheus.demo-1.0-SNAPSHOT.jar
```
- dockerfile
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY com.yzd.prometheus.demo-1.0-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
```
- 把jar包构建成镜像
```
docker build -t prometheus.demo:v1.0 .
//
docker images
:
REPOSITORY                              TAG                 IMAGE ID            CREATED             SIZE
prometheus.demo                         v1.0                9e22c64378eb        18 seconds ago      123 MB
docker.io/openjdk                       8-jdk-alpine        a3562aa0b991        12 months ago       105 MB
```
- 运行刚才构建成的镜像
```
docker run -d -p 18090:8090 --name prometheus.demo prometheus.demo:v1.0
//查看所有容器，包括关闭的
docker ps -a
docker ps
:
CONTAINER ID        IMAGE                   
c0ea8ea2f1fd        prometheus.demo:v1.0    
//
netstat -ntpl


```
- 项目变成容器运行在docker里，怎么查看项目日志：
```
容器日志命令查看：表示查看容器名为thymeleaf-master的500行日志
docker logs -f -t --tail 500 prometheus.demo
```
- 打开浏览器ip：8082端口验证
```
curl -v http://127.0.0.1:18090
```
- 关闭docker
```
docker kill prometheus.demo
//查看所有容器，包括关闭的
docker ps -a
docker ps
```
### K8s-完整部署示例
```
docker images
kubectl run m-prometheus-demo --image=prometheus.demo:v1.0 --port=8090
kubectl get pods -o wide
curl -v http://172.17.0.5:8090
netstat -ntpl
kubectl get deployments
kubectl get services
//创建服务
kubectl expose deployment m-prometheus-demo
//通过edit操作设置ports下增加或是修改name
kubectl edit svc m-prometheus-demo
kubectl get services
//扩容与缩容
#扩容
kubectl scale deployment m-prometheus-demo --replicas=10
#缩容
kubectl scale deployment m-prometheus-demo --replicas=1
//更新回滚
#更新应用的镜像从v1.0版本——>v1.1
kubectl set image deployment m-prometheus-demo m-prometheus-demo=m-prometheus-demo:v1.1
#确认是否更新成功
kubectl rollout status deployment m-prometheus-demo
deployment "m-prometheus-demo" successfully rolled out
#回滚到上一代版本
kubectl rollout undo deployment m-prometheus-demo
deployment "m-prometheus-demo" rolled back

```

### Dockerfile-示例
- Dockerfile:DOME-01   -推荐参考byArvin
```
FROM openjdk:8-jdk-alpine
VOLUME /tmp
#ARG JAR_FILE
COPY demo.jar app.jar
ENV JAVA_OPTS=""
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
```
- Dockerfile:DOME-02
```
#基础镜像，如果本地没有，会从远程仓库拉取。
FROM openjdk:8-jdk-alpine

#镜像的制作人
MAINTAINER xzxiaoshan/365384722@qq.com

#工作目录
WORKDIR /app/

#在容器中创建挂载点，可以多个
VOLUME ["/tmp"]

#声明了容器应该打开的端口并没有实际上将它打开
EXPOSE 8080

#定义参数
ARG JAR_FILE

#拷贝本地文件到镜像中
COPY ${JAR_FILE} app.jar

#指定容器启动时要执行的命令，但如果存在CMD指令，CMD中的参数会被附加到ENTRYPOINT指令的后面
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
```

### 故障处理
- [(已解决)使用docker拉镜像时连接超时]()
```

vim /etc/docker/daemon.json
在文件中加入：
{
"registry-mirrors":["https://hub-mirror.c.163.com/","https://docker.mirrors.ustc.edu.cn"]
}
然后重启守护进程：
sudo systemctl daemon-reload
sudo systemctl restart docker
```

### docker 常用命令总结
- [docker 常用命令总结](https://blog.csdn.net/u013378306/article/details/86668313)
```
docker的使用命令
1 docker 命令介绍

docker --help

管理命令:

  container   管理容器

  image       管理镜像

  network     管理网络

命令：

  attach      介入到一个正在运行的容器

  build       根据 Dockerfile 构建一个镜像

  commit      根据容器的更改创建一个新的镜像

  cp          在本地文件系统与容器中复制 文件/文件夹

  create      创建一个新容器

  exec        在容器中执行一条命令

  images      列出镜像

  kill        杀死一个或多个正在运行的容器    

  logs        取得容器的日志

  pause       暂停一个或多个容器的所有进程

  ps          列出所有容器

  pull        拉取一个镜像或仓库到 registry

  push        推送一个镜像或仓库到 registry

  rename      重命名一个容器

  restart     重新启动一个或多个容器

  rm          删除一个或多个容器

  rmi         删除一个或多个镜像

  run         在一个新的容器中执行一条命令

  search      在 Docker Hub 中搜索镜像

  start       启动一个或多个已经停止运行的容器

  stats       显示一个容器的实时资源占用

  stop        停止一个或多个正在运行的容器

  tag         为镜像创建一个新的标签

  top         显示一个容器内的所有进程

  unpause     恢复一个或多个容器内所有被暂停的进程

```