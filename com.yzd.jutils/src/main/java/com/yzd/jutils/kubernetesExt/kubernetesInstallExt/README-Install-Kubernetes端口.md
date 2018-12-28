#### [Kubernetes中的nodePort，targetPort，port的区别和意义](https://blog.csdn.net/u013760355/article/details/70162242)
```
1. nodePort
　外部机器可访问的端口。 
比如一个Web应用需要被其他用户访问，那么需要配置type=NodePort，而且配置nodePort=30001，那么其他机器就可以通过浏览器访问scheme://node:30001访问到该服务，例如http://node:30001。 
　例如MySQL数据库可能不需要被外界访问，只需被内部服务访问，那么不必设置NodePort

2. targetPort
　容器的端口（最根本的端口入口），与制作容器时暴露的端口一致（DockerFile中EXPOSE），例如docker.io官方的nginx暴露的是80端口。 
　docker.io官方的nginx容器的DockerFile参考https://github.com/nginxinc/docker-nginx

3. port
　kubernetes中的服务之间访问的端口，尽管mysql容器暴露了3306端口（参考https://github.com/docker-library/mysql/的DockerFile），但是集群内其他容器需要通过33306端口访问该服务，外部机器不能访问mysql服务，因为他没有配置NodePort类型

4. 举例
apiVersion: v1
kind: Service
metadata:
 name: nginx-service
spec:
 type: NodePort
 ports:
 - port: 30080
   targetPort: 80
   nodePort: 30001
 selector:
  name: nginx-pod

apiVersion: v1
kind: Service
metadata:
 name: mysql-service
spec:
 ports:
 - port: 33306
   targetPort: 3306
 selector:
  name: mysql-pod
  
5. 其他文章
Kubernetes安装配置与服务部署 
Kubernetes 1.5部署安装dashboard
--------------------- 
```