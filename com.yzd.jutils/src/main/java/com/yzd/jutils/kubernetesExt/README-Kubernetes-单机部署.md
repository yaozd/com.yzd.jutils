## Kubernetes-单机部署
- [初识Kubernetes（K8s）：从一个单机部署实现Java Web应用例子开始](https://blog.51cto.com/andyxu/2309187?source=dra) -主要参考byArvin
- [kubernetes-pod-infrastructure:latest](https://www.cnblogs.com/learn-ops/p/10055573.html) 
    - PS:#解决kubernetes启动容器时，容器一直是ContainerCreating不能running
- [kubernetes入门](https://www.cnblogs.com/Leo_wl/p/8072881.html)
- Docker备份与迁移
    - [Docker教程系列八：Docker备份与迁移（完）](https://blog.csdn.net/u011142756/article/details/81712504)
- []()


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
