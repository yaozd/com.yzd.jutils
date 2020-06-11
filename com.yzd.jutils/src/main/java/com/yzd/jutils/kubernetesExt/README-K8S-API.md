## k8s api doc -v1.15|v1.18
- [https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.15/](https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.15/)
- [https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.18/#watch-list-node-v1-core](https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.18/#watch-list-node-v1-core)

### WatchEvent
- [https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.15/#watchevent-v1-meta](https://kubernetes.io/docs/reference/generated/kubernetes-api/v1.15/#watchevent-v1-meta)
```
 * Object is:
 * * If Type is Added or Modified: the new state of the object.
 * * If Type is Deleted: the state of the object immediately before deletion.
 * * If Type is Error: *Status is recommended; other types may make sense depending on context.
```

## K8S-API
- [使用 Java 操作 Kubernetes API](https://blog.csdn.net/fly910905/article/details/101345091)
- [kubernetes api微服务开发--访问api server](https://blog.csdn.net/xingyuzhe/article/details/80564441)
- [Kubernetes(K8s) API概念理解&常用API使用方法速查 v1.11](https://blog.csdn.net/weixin_38070561/article/details/83272480)
- [kubernetes 常用 API](https://www.jianshu.com/p/04dc3d77178a)
- []()

## Kubernetes 认证
- [K8s直接访问 REST API](https://blog.csdn.net/shenhonglei1234/article/details/81633127) -使用BearerToken方式
- [Kubernetes 认证](http://docs.kubernetes.org.cn/51.html)
- [k8s获取用户BearerToken方式](https://www.cnblogs.com/davygeek/p/12876540.html)
- [k8s查看用户的token并验证](https://blog.csdn.net/wangshuminjava/article/details/92791083)
- [Kubernetes 忘记token解决方案](https://blog.csdn.net/qq_19734597/article/details/97674360)
- []()
- []()

### 操作示例
```
Kubernetes(K8s) API概念理解&常用API使用方法速查 v1.11
https://blog.csdn.net/weixin_38070561/article/details/83272480
kubectl api-versions
curl http://127.0.0.1:8080/api/v1/services
curl http://127.0.0.1:8080/api/v1/pods
curl http://127.0.0.1:8080/api/v1/services
//获取所有
curl http://127.0.0.1:8080/api/v1/namespaces/default/services/m-nginx
//监听
curl http://127.0.0.1:8080/api/v1/watch/namespaces/default/services/m-nginx
curl http://127.0.0.1:8080/api/v1/watch/namespaces/default/services/
//获取所有 --用于服务发现
curl http://127.0.0.1:8080/api/v1/namespaces/default/endpoints
curl http://127.0.0.1:8080/api/v1/namespaces/default/endpoints/my-nginx
//监听 --用于服务发现
curl http://127.0.0.1:8080/api/v1/watch/namespaces/default/endpoints
curl http://127.0.0.1:8080/api/v1/watch/namespaces/default/endpoints/my-nginx

PS:调试命令：
kubectl run my-nginx --image=nginx --port=80
# 扩展10个副本
$ kubectl scale deployment my-nginx --replicas=10
# 缩扩到1个副本
$ kubectl scale deployment my-nginx --replicas=1
```