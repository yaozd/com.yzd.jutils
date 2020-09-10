# Nacos client
## 参考
- [Nacos系列：Nacos的Java SDK使用](https://blog.csdn.net/weixin_34099526/article/details/88611897)
    - [https://github.com/zhixinglvren/learn-nacos](https://github.com/zhixinglvren/learn-nacos)
- [服务注册实例源码-nacos-spring-cloud-discovery-exampl](https://github.com/nacos-group/nacos-examples/tree/master/nacos-spring-cloud-example/nacos-spring-cloud-discovery-example/nacos-spring-cloud-provider-example)
- [Nacos 解读：服务发现客户端](https://www.lbbniu.com/6405.html)
- []()
## 关键参数
- [服务发现](https://www.lbbniu.com/6405.html)
```
1.
向Nacos服务端发送已注册服务的心跳:默认情况下每5秒发送一次心跳
2.
HostReactor用于获取、保存、更新各Service实例信息间隔:默认值为10秒
3.
更新Nacos服务端地址:默认间隔为30秒
4.
```
## 