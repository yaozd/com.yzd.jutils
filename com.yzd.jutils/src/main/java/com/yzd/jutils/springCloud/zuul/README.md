### ZUUL的技术问题

#### 限流问题
- [Spring Cloud限流思路及解决方案-byArvin推荐](https://www.cnblogs.com/xifenglou/p/8519700.html)
- [Zuul：构建高可用网关之多维度限流-spring-cloud-zuul-ratelimit](https://www.jianshu.com/p/681c3b368eed)
- [zuul和ratelimit一起使用 配置](https://blog.csdn.net/weixin_40066829/article/details/79456806)
- [Zuul上实现限流（spring-cloud-zuul-ratelimit）](https://www.jianshu.com/p/d165e12df1da)
- [zuul网关限流-byArvin推荐](https://blog.csdn.net/SHIYUN123zw/article/details/82315252)

限流思路
```
byArvin-2018-09-20
1.guava-对zuul整体的流量进行一个计数统计(作用域：单个zuul)
2.spring-cloud-zuul-ratelimit，依用户ID维度进行计数统计(作用域：集群zuul)
3.阿里sentinel,对单个接口进行计数统计(作用域：单个服务)
```
spring-cloud-zuul-ratelimit配置参数详解：
```
repository ：是key值保存方式，可以选Redis、Consul、Spring Data JPA等方式，这里选择的是 Redis，所以要添加redis依赖和配置。
limit 单位时间内允许访问的次数
quota 单位时间内允许访问的总时间（单位时间窗口期内，所有的请求的总时间不能超过这个时间限制）
注：quota 每个窗口对应的请求总时间（每个窗口请求时间相加后的和）的限制（窗口期内，所有的请求的总时间不能超过这个时间限制）。
refresh-interval 单位时间设置
type 限流类型：
url类型的限流就是通过请求路径区分
origin是通过客户端IP地址区分
user是通过登录用户名进行区分，也包括匿名用户
```

#### 全局验证问题-auth
- [采用Zuul网关和Spring Security搭建一个基于JWT的全局验证架构](https://blog.csdn.net/daijinmingcn/article/details/79261610)
- [token校验/安全认证](http://www.cnblogs.com/yjmyzz/p/spring-cloud-zuul-demo.html#)

```
byArvin-2018-09-20
全局验证问题时，只需要做token校验就可以
对不需要token校验的地址做过滤就可以
```

#### 跨域问题-cors
- [Spring Cloud#微服务+Zuul时的跨域问题](https://blog.csdn.net/xichenguan/article/details/77394779)
- [SpringCloud 从整体上解决跨域问题_zuul网关配置实现跨域](https://blog.csdn.net/lidew521/article/details/82625296)

```
行为如下，原理待整理： 
1. 微服务配置跨域+zuul不配置=有跨域问题 
2. 微服务配置+zuul配置=有跨域问题 
3. 微服务不配置+zuul不配置=有跨域问题 
4. 微服务不配置+zuul配置=ok
```
#### 动态路由问题
- [zuul动态配置路由规则，从DB读取](https://blog.csdn.net/hxpjava1/article/details/78304003)

####　熔断处理


#### 灰度发布(Gated Launch/Gray Release)
- [灰度发布(Gated Launch/Gray Release)](http://www.cnblogs.com/yjmyzz/p/spring-cloud-zuul-demo.html#)

```
```
####　Zuul超时问题
- [Zuul超时问题，微服务响应超时，zuul进行熔断](https://blog.csdn.net/tianyaleixiaowu/article/details/78772269)

```
总结起来就是三种超时配置： 
网关的超时层级 
zuul

zuul: 
max: 
host: 
connections: 500 
host: 
socket-timeout-millis: 60000 
connect-timeout-millis: 60000

ribbon

ribbon: 
ReadTimeout: 10000 
ConnectTimeout: 10000 
MaxAutoRetries: 0 
MaxAutoRetriesNextServer: 1 
eureka: 
enabled: true

hystrix

hystrix: 
command: 
default: 
execution: 
timeout: 
enabled: true 
isolation: 
thread: 
timeoutInMilliseconds: 60000 
这里面ribbon和hystrix是同时生效的，哪个值小哪个生效，另一个就看不到效果了。
```


