### [Zuul超时问题，微服务响应超时，zuul进行熔断](https://blog.csdn.net/tianyaleixiaowu/article/details/78772269)

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