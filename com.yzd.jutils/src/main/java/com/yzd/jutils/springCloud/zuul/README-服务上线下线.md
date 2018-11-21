
-[spring cloud 学习(7) - 生产环境如何不停机热发布？](https://www.cnblogs.com/yjmyzz/p/how-to-hot-release-using-eureka-rest-operation.html)

```
1、先将目标机的服务状态调整成“下线”

即：利用 PUT /eureka/apps/appID/instanceID/status?value=OUT_OF_SERVICE 这个rest接口
```

-解决思路
```
1.监听spring boot关闭请求
2.关闭当前应用请求，使用本应用不再对eureka发起注册请求：eureka.client.registerWithEureka=false
3.利用 PUT /eureka/apps/appID/instanceID/status?value=OUT_OF_SERVICE 这个rest接口
  向euraka发出下线请求
4.调用其他客户端的更新请求（是通过反射的方式实现的）
```