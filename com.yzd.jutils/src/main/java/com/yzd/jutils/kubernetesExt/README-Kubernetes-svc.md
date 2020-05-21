##　参考
- [k8s的service的几种访问模式](Kubernetes系列之理解K8s Service的几种模式)
```
在实际生产环境中，一般有两种访问 对集群内部的访问， 集群外部的访问。service现在分为以下类型
ClusterIP
集群内部容器访问地址，会生成一个虚拟IP 与pod不在一个网段。
**NodePort **
会在宿主机上映射一个端口，供外部应用访问模式。
**Headless CluserIP **
无头模式，无serviceip，即把spec.clusterip设置为None 。
```

## svc:服务相关操作

- 期望
```
"ports": [
  {
    "name": "http", //如何设置name
    "port": 80,
    "protocol": "TCP"
  }
]

```

- 操作
```
kubectl get endpoints nginx -o wide  -o yaml

//通过edit操作设置ports下增加或是修改name
kubectl edit svc nginx

kubectl describe svc nginx

kubectl get svc nginx
```

## service 常用命令
```
kubectl expose deployment ml3 --type=LoadBalancer --name=management --port=80 --target-port=8000
kubectl expose deployment ml3 --type=LoadBalancer --name=query --port=80 --target-port=8001
# 创建服务
kubectl expose deployment/my-nginx --type="NodePort" --port 80
//
kubectl get endpoints nginx -o wide  -o yaml
//通过edit操作设置ports下增加或是修改name
kubectl edit svc nginx
kubectl describe svc nginx
kubectl get svc nginx
```

