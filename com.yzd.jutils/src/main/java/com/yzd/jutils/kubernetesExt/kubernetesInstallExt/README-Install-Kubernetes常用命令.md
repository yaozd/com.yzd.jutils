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

```