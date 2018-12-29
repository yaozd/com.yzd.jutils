###  README-Install-Kubernetes常用命令.md
```
1.查看pod的ip
kubectl get svc
-----------------------
kubectl get pods
-----------------------
kubectl delete pods nginx-pod
-----------------------
kubectl get nodes
-----------------------
kubectl create -f nginx-pod.yaml
-----------------------
kubectl get pods --namespace=kube-system
kubectl describe pods nginx-pod
kubectl describe pods kubernetes-dashboard-2783801932-q1tfg --namespace=kube-system
kubectl get pod --all-namespaces
kubectl delete pods --all 
kubectl delete pods --all --namespace=kube-system

-----------------------
kubectl get deployment --all-namespaces
kubectl delete deployment kubernetes-dashboard --namespace=kube-system
-----------------------
kubectl get services
kubectl delete services nginx-service

```

### [Kubernetes之kubectl常用命令](https://blog.csdn.net/xingwangc2014/article/details/51204224)
### [Kubernetes之kubectl常用命令使用指南:3:故障对应](https://blog.csdn.net/liumiaocn/article/details/73997635)
```
8. Delete
根据resource名或label删除resource。 
kubectl delete -f rc-nginx.yaml 
kubectl delete po rc-nginx-btv4j 
kubectl delete po -lapp=nginx-2 
--------------------- 
kubectl get deployment --all-namespaces
[root@master ~]# kubectl get deployment --all-namespaces
NAMESPACE   NAME      DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
default     nginx     2         2         2            2           20h

--------------------- 
查看IP地址
kubectl get pod  -o wide  --all-namespaces
[root@master ~]# kubectl get pod  -o wide  --all-namespaces
NAMESPACE   NAME                     READY     STATUS    RESTARTS   AGE       IP            NODE
default     nginx-3449338310-9txf4   1/1       Running   0          20h       10.255.61.2   node1
default     nginx-3449338310-p3wzh   1/1       Running   0          20h       10.255.61.3   node1
从master可以ping通过node的docker 的ip(但目前不知道为什么-20181229-1432)
[root@master ~]# ping 10.255.61.2
PING 10.255.61.2 (10.255.61.2) 56(84) bytes of data.
64 bytes from 10.255.61.2: icmp_seq=1 ttl=249 time=5.56 ms
64 bytes from 10.255.61.2: icmp_seq=2 ttl=249 time=5.21 ms

--------------------- 
kubectl get deployment --all-namespaces
kubectl delete deployment nginx
--------------------- 

```

### 问题定位指南
```
1.查询所有事件
kubectl get even
2.查看Pod相关事件
kubectl get pods --namespace=kube-system
kubectl describe pods nginx-pod
kubectl describe pods kubernetes-dashboard-2783801932-q1tfg --namespace=kube-system
来自：

```