## Kubernetes-单机部署
- [初识Kubernetes（K8s）：从一个单机部署实现Java Web应用例子开始](https://blog.51cto.com/andyxu/2309187?source=dra) -主要参考byArvin
- [kubernetes-pod-infrastructure:latest](https://www.cnblogs.com/learn-ops/p/10055573.html) 
    - PS:#解决kubernetes启动容器时，容器一直是ContainerCreating不能running
- [kubernetes入门](https://www.cnblogs.com/Leo_wl/p/8072881.html) -K8s安装-部署一个实例参考:My-Nginx安装-特别推荐byArvin
    ```

    ```
- Docker备份与迁移
    - [Docker教程系列八：Docker备份与迁移（完）](https://blog.csdn.net/u011142756/article/details/81712504)
- [Kubernetes中文手册](https://www.kubernetes.org.cn/docs)
- [Kubernetes中文社区 | 中文文档](http://docs.kubernetes.org.cn/) -推荐byArvin

## Kubernetes-常用命令
- [官网](https://kubernetes.io/docs/reference/kubectl/overview/)
- [K8s的kubectl常用命令](https://www.cnblogs.com/vito-lee/p/11911963.html)
- [kubectl 常用命令总结](https://www.cnblogs.com/miclesvic/articles/10997060.html)
- []()
```
https://kubernetes.io/docs/reference/kubectl/overview/
```

- 安装K8S指令：
    ```
     hostnamectl set-hostname  k8s-master
     yum install -y epel-release
     systemctl disable firewalld
     systemctl stop firewalld
     reboot
     yum install -y etcd
     yum install -y  kubemetes
     //
     #解决kubernetes启动容器时，容器一直是ContainerCreating不能running
     wget http://mirror.centos.org/centos/7/os/x86_64/Packages/python-rhsm-certificates-1.19.10-1.el7_4.x86_64.rpm
     rpm2cpio python-rhsm-certificates-1.19.10-1.el7_4.x86_64.rpm | cpio -iv --to-stdout ./etc/rhsm/ca/redhat-uep.pem | tee /etc/rhsm/ca/redhat-uep.pem
     vim /etc/kubernetes/kubelet
     ···
     #解决kubernetes启动容器时，容器一直是ContainerCreating不能running
     # pod infrastructure container
     KUBELET_POD_INFRA_CONTAINER="--pod-infra-container-image=docker.io/tianyebj/pod-infrastructure:latest"
     ···
     //
     vim /etc/sysconfig/docker
     ···
     OPTIONS='--selinux-enabled=false --insecure-registry gcr.io'
     ···
     //
     vim /etc/kubernetes/apiserver
     ···
     KUBE_ADMISSION_CONTROL="--admission-control=NamespaceLifecycle,NamespaceExists,LimitRanger,SecurityContextDeny,ResourceQuota"
     ···
     //
     for SERVICES in etcd docker kube-apiserver kube-controller-manager kube-scheduler kubelet kube-proxy; do systemctl restart $SERVICES; systemctl enable $SERVICES; systemctl status $SERVICES; done;
     systemctl start etcd
     systemctl start docker
     systemctl start kube-apiserver
     systemctl start kube-controller-manager
     systemctl start kube-scheduler
     systemctl start kubelet
     systemctl start kube-proxy
     //
     docker images
     ll
     docker load -i nginx.tar 
     docker load -i pod-infrastructure.tar 
     docker images
     netstat -ntpl
     kubectl get cs
     kubectl get node
     kubectl get service
     kubectl run m-nginx --image=nginx --port=80
     kubectl get cs
     kubectl get node
     kubectl get service
     kubectl get pod
     netstat -ntpl
     kubectl get services
     kubectl get deployments
     kubectl expose deployment/m-nginx --type="NodePort" --port 80
     kubectl get services
     netstat -ntpl
     kubectl describe service/m-nginx
     curl -v http://172.17.0.2:80
     kubectl describe service/m-nginx
     ping 172.17.0.2
     ping 10.254.47.67
     history
    ```
- 快速启动：
    ```
    for SERVICES in etcd docker kube-apiserver kube-controller-manager kube-scheduler kubelet kube-proxy; do systemctl restart $SERVICES; systemctl enable $SERVICES; systemctl status $SERVICES; done;    

    ```
  
- 日志管理工具journalctl
```
kubectl describe service/m-nginx
curl -v http://172.17.0.2:80
kubectl describe service/m-nginx
ping 172.17.0.2
ping 10.254.47.67
netstat -nptl
```

- 实例部署-m-nginx
```
docker images
ll
docker load -i nginx.tar 
docker load -i pod-infrastructure.tar 
docker images
netstat -ntpl
kubectl get cs
kubectl get node
kubectl get service
## 部署实例
kubectl run m-nginx --image=nginx --port=80
kubectl get cs
kubectl get node
kubectl get service
kubectl get pod
netstat -ntpl
kubectl get services
kubectl get deployments
## 创建服务
kubectl expose deployment/m-nginx --type="NodePort" --port 80
kubectl get services

```

- service 服务
```
kubectl get services
kubectl get deployments
## 创建服务
kubectl expose deployment/m-nginx --type="NodePort" --port 80
kubectl get services
//
PS：服务只启用一个IP与端口。一个服务后端可以包含1个或多个pod。内部通过负载均衡的方式调用，eg:
[root@k8s-master tmp]# kubectl describe services/my-nginx
Name:			my-nginx
Namespace:		default
Labels:			run=my-nginx
Selector:		run=my-nginx
Type:			NodePort
IP:			10.254.248.158
Port:			<unset>	80/TCP
NodePort:		<unset>	30925/TCP
Endpoints:		172.17.0.10:80,172.17.0.11:80,172.17.0.12:80 + 7 more...
Session Affinity:	None
No events.
[root@k8s-master tmp]# kubectl get pods -o wide
NAME                       READY     STATUS    RESTARTS   AGE       IP            NODE
m-nginx-1747106554-vdchh   1/1       Running   3          142d      172.17.0.2    127.0.0.1
my-nginx-379829228-bjb14   1/1       Running   0          1h        172.17.0.3    127.0.0.1
my-nginx-379829228-c7h9r   1/1       Running   0          1h        172.17.0.8    127.0.0.1
my-nginx-379829228-d9xmz   1/1       Running   0          1h        172.17.0.4    127.0.0.1
my-nginx-379829228-g4cn6   1/1       Running   0          1h        172.17.0.9    127.0.0.1
my-nginx-379829228-p4h6p   1/1       Running   0          1h        172.17.0.7    127.0.0.1
my-nginx-379829228-rksfq   1/1       Running   0          1h        172.17.0.6    127.0.0.1
my-nginx-379829228-tft3b   1/1       Running   0          1h        172.17.0.12   127.0.0.1
my-nginx-379829228-wmnfg   1/1       Running   0          1h        172.17.0.11   127.0.0.1
my-nginx-379829228-z28ql   1/1       Running   0          1h        172.17.0.5    127.0.0.1
my-nginx-379829228-zwk7b   1/1       Running   0          1h        172.17.0.10   127.0.0.1

```

- 删除服务
```
//查看部署信息
kubectl get pods
## -o wide 显示pod的ip
kubectl get pods -o wide
kubectl get deployments
kubectl get service
//
curl -v 10.254.198.7 80
kubectl delete service my-nginx
kubectl get service
//
kubectl get deployments
kubectl delete deployment my-nginx
//
kubectl get pods
## -o wide 显示pod的ip
kubectl get pods -o wide
kubectl get deployments
kubectl get service
```

- 部署：扩容与缩容
- [kubernetes常用命令：缩容扩容回滚](https://www.cnblogs.com/shawhe/p/11088356.html)
```
# 运行nginx镜像
$ kubectl run my-nginx --image=nginx --port=80
# 创建服务
kubectl expose deployment/my-nginx --type="NodePort" --port 80
# 交互式 shell 的方式运行 pod
$ kubectl run -i --tty my-nginx --image=nginx --port=80 -- sh
# 链接到运行中的容器
$ kubectl attach my-nginx-532658988-10kxd -i
 
# 查看deployment
$ kubectl get deployments
NAME DESIRED CURRENT UP-TO-DATE AVAILABLE AGE
my-nginx 1 1 1 1 25m
# 扩展10个副本
$ kubectl scale deployment my-nginx --replicas=10
deployment "my-nginx" scaled
$ kubectl scale deployment/my-nginx --replicas=10 # 作用效果等同上一条命令
deployment "my-nginx" scaled
# 再次显示deployment
$ kubectl get deployments
NAME DESIRED CURRENT UP-TO-DATE AVAILABLE AGE
my-nginx 10 10 10 1 26m
$ kubectl get pods
NAME READY STATUS RESTARTS AGE
my-nginx-379829228-38hkg 1/1 Running 0 5m
my-nginx-379829228-7j15l 1/1 Running 0 31m
my-nginx-379829228-c8mt3 1/1 Running 0 5m
my-nginx-379829228-f6mm8 1/1 Running 0 5m
my-nginx-379829228-q1rj0 1/1 Running 0 5m
my-nginx-379829228-qg7lf 1/1 Running 0 5m
my-nginx-379829228-rjfbq 1/1 Running 0 5m
my-nginx-379829228-v581r 1/1 Running 0 5m
my-nginx-379829228-wh49w 1/1 Running 0 5m
my-nginx-379829228-wpn98 1/1 Running 0 5m
 
# 缩扩到1个副本
$ kubectl scale deployment/my-nginx --replicas=1
deployment "my-nginx" scaled
$ kubectl scale deployment my-nginx --replicas=1 # 作用效果等同上一条命令
```

- 对比docker命令
```
k8s的学习路线基本都是从docker[容器]到k8s的，因此两个对比理解有助于记忆

# docker run
$ docker run -d -e DOMAIN=cluster --name my-nginx -p 80:80 nginx
$ kubectl run my-nginx --image=nginx --port=80 --env="DOMAIN=cluster"
 
# docker ps
$ docker ps
$ kubectl get pods
 
# docker exec
$ docker exec [容器id] ls
$ kubectl exec [pod_id] ls
 
# docker exec 交互式
$ docker exec -it [容器id] /bin/sh
$ kubectl exec -it [pod_id] -- /bin/sh
 
# docker info
$ docker info
$ kubectl cluster-info
```

- 名称简写
```
po (pod)
ns (命名空间namespace)
instance (实例)
svc (service服务): 定义一个pod的逻辑分组，一种可以访问他们的策略（微服务）。
cm (configMap): 存储全局配置变量的，将分布式系统中不同模块的环境变量统一到一个对象中管理。
ds (deamonSet)：在每台计算机节点上运行一个守护进程（如日志采集等）,有时pod处于pending可能是因为某个deamonSet没起来。
deploy (deployment）
```