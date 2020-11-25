# K8S基础知识面试题

```

1.k8s组件高可用原理？

   apiserver 

   scheduler

   controller-manager

 

2.Deployment->rs-{hash}->pod 副本。

hash 与什么有关？

副本数变化为啥hash不变？

 

3.k8s  pod健康检查(liveness probe存活探针&&readiness probe 可读性探针)

  liveness probe存活探针，如果检测失败触发k8s 什么动作？

  readiness probe 存活探针，如果检测失败触发k8s 什么动作？

 

4. k8s 授权访问api 步骤？



5.当一个pod 调度时，如何确认node的cpu和内存是否够用？



6. 如何确保一个deploy 里的多个pod副本，分布到不同的node？带来的问题？



7.如果程序未做优雅关闭，会有哪些影响结合k8s我们能做哪些事情？



8.网络插件flanneld进程，cni作用什么？如果flanneld进程down无法启动，会有哪些影响？



9.oom 和kubtctl top pod  xxx 与那个监控参数有关。



10.k8s  pod的yaml文件中，有哪些可以影响调度器调度。

 

11.描述如何开发一个k8s crd 插件。描述informer 原理。

 

12.一个pod 由哪些ccontainer 组成。```