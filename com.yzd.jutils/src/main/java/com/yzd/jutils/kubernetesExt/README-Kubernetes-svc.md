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

### 各种Service类型以及对应操作详解（ClusterIP, ExternalName, LoadBalancer, NodePort）
- [各种Service类型以及对应操作详解（ClusterIP，NodePort，ExternalName）](https://blog.csdn.net/Victor2code/article/details/105969032) -推荐参考byArvin
```
Service的类型
k8s中的Service有如下几种类型，对应着定义Service的yaml文件的type字段

ClusterIP
默认类型，该Service的虚拟IP仅为集群内部访问
NodePort
在ClusterIP的基础上，在每个Node的物理网卡上都为该Service建立一个相同的端口映射，例如上图中将每个Node的8443端口都映射到Frontend这个Service的443端口，
这样不管外部访问哪个Node的IP:8443都可以访问到该Service。如果要对集群外提供服务采用该方式，并且通常在Node的前面加上针对Node物理网卡IP的负载均衡
LoadBalancer
在NodePort的基础上，借助第三方的云服务提供Node物理网卡IP的负载均衡。企业中用的不多，因为第三方云服务要额外收费，并且完全可以用免费方案代替
ExternalName
相当于给集群外部的一个第三方服务加了一个DNS的CNAME记录，将外部流量引入集群内部

```

###　kubectl expose详解
- [https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#expose](https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#expose)

##  type=NodePort yaml
```
kubectl expose deployment m-prometheus-demo --type="NodePort" --port=8090
kubectl edit svc m-prometheus-demo 
//
apiVersion: v1
kind: Service
metadata:
  creationTimestamp: 2020-05-28T05:28:04Z
  labels:
    run: m-prometheus-demo
  name: m-prometheus-demo
  namespace: default
  resourceVersion: "346289"
  selfLink: /api/v1/namespaces/default/services/m-prometheus-demo
  uid: 012d537b-a0a4-11ea-b7c5-0800271d5e97
spec:
  clusterIP: 10.254.25.193
  ports:
  ## 修改nodePort为固定端口
  - nodePort: 30001
    port: 8090
    protocol: TCP
    targetPort: 8090
    name: http
  selector:
    run: m-prometheus-demo
  sessionAffinity: None
  type: NodePort
status:
  loadBalancer: {}
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

