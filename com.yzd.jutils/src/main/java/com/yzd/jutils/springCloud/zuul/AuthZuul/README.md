
### [采用Zuul网关和Spring Security搭建一个基于JWT的全局验证架构](https://blog.csdn.net/daijinmingcn/article/details/79261610)

```
https://github.com/shuaicj/zuul-auth-example
https://github.com/yaozd/zuul-auth-example
```
```
总体架构图

1、验证中心
这个服务用于生成JWT令牌（token）
客户端通过 向/login 地址 Post { username:"帐号",password:"密码" } 进行验证，获得令牌。

2、后台服务

提供业务服务，可按照功能切分为多个微服务。

3、Zuul API 网关

定义验证中心、后台服务的路由
验证JWT令牌
基于角色的权限校验
项目源码：https://github.com/shuaicj/zuul-auth-example
```