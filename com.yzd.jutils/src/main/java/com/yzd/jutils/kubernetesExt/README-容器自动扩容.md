# K8S-Pod 容器自动扩容
- [微服务架构实践](https://myslide.cn/slides/21430?vertical=1)
```
容器自动扩容：
 TargetNumOfPods= ceil(sum(Currentpodscpuutiliz)/ Target
PS:ceil(取整)
参照 Kuberneteshpa(Horizontal Pod Autoscaling)算法:
自研发实现自动扩容缩容,CurrentPodsCpuUtilization为当前每台pod的CPU使用率,Target为定义的应用CPU使用率指标
示例演示：
假设 Target定为为50%,当前容器CPU使用率的平均值为60%,有
10个容器实例,则 CurrentPodsCpuUtilization=60%*10=6,则
TargetNumOfPods=ceil(6/0.5)=12,则需要扩容12-10=2台容器
```