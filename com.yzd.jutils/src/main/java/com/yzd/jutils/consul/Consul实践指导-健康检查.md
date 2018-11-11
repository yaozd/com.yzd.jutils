### [Consul实践指导-健康检查（Checks）](https://blog.csdn.net/younger_china/article/details/52243759)

### 有五种check方法
- 1.Script+ Interval
- 2.HTTP+ Interval
- 3.TCP+ Interval
- 4.Timeto Live（TTL）
- 5.Docker+ interval


```
Consul的一个基本功能是提供系统级和应用级健康检查。如果健康检查与某个服务关联，则称为是应用级的；如果不予服务关联，则监控整个节点的健康。

check定义在配置文件中，或运行时通过HTTP接口添加。Check是通过HTTP与节点保持一致。

有五种check方法：

1.1.1  Script+ Interval
        通过执行外部应用进行健康检查：这种外部程序具有退出代码，并可能产生一些输出；脚本按照指预置时间间隔来调用（比如，每30秒调用一次），类似于Nagios插件系统，脚本输出限制在4K以内，输出大于4K将截断。默认情况下，脚本超时时间为30秒——可通过timeout来配置。

示例：

{
  "check": {
    "id": "mem-util",
    "name": "Memoryutilization",
    "script": "/usr/local/bin/check_mem.py",
    "interval": "10s",
    "timeout": "1s"
  }
}

注：实际操作发现，并不支持py，必须为shell脚本
 

1.1.2  HTTP+ Interval
        这种检查将按照预设的时间间隔创建一个HTTP “get”请求。HTTP响应代码来标示服务所处状态：任何2xx代码视为正常，429表示警告——有很多请求；其他值表示失败。

这种类型的检查应使用curl或外部程序来处理HTTP操作。默认情况下，HTTP Checks中，请求超时时间等于调用请求的间隔时间，最大10秒。有可能使用客制的HTTP check，可以自由配置timeout时间，输出限制在4K以内，输出大于4K将截断。

{
  "check": {
    "id": "api",
    "name": "HTTPAPI on port 5000",
    "http": "http://localhost:5000/health",
    "interval": "10s",
    "timeout": "1s"
  }
}
 

1.1.3  TCP+ Interval
        将按照预设的时间间隔与指定的IP/Hostname和端口创建一个TCP连接。服务的状态依赖于TCP连接是否成功——如果连接成功，则状态是“success”；否则状态是“critical”。如果一个Hostname解析为一个IPv4和一个IPv6，将尝试连接这两个地址，第一次连接成功则服务状态是“success”。

        如果希望通过这种方式利用外部脚本执行健康检查，那么脚本应该采用“netcat”或者简单的socket操作。

默认情况下，TCP checks中，请求超时时间等于调用请求的间隔时间，最大10秒。也是可以自由配置的。

{
  "check": {
    "id": "ssh",
    "name": "SSHTCP on port 22",
    "tcp": "localhost:22",
    "interval": "10s",
    "timeout": "1s"
  }
}
 

1.1.4  Timeto Live（TTL）
        这种checks为给定TTL保留了最后一种状态，checks的状态必须通过HTTP接口周期性更新，如果外部接口没有更新状态，那么状态就会被认定为不正常。

        这种机制，在概念上类似“死人开关”，需要服务周期性汇报健康状态。比如，一个健康的APP可以周期性的将状态put到HTTP端；如果app出问题了，那么TTL将过期，健康检查将进入Critical状态。用来为给定check更新健康信息的endpoint都是pass endpoint和fail endpoint。（参见agent http endpoint）

        TTL checks同时会将其最后已知状态更新至磁盘，这允许Agent通过重启后恢复到已知的状态。通过TTL端上一次check来维持健康状态的有效性。

{
  "check": {
    "id": "web-app",
    "name": "WebApp Status",
    "notes": "Webapp does a curl internally every 10 seconds",
    "ttl": "30s"
  }
}
 

1.1.5  Docker+ interval
        这种检查依赖于调用封装在docker容器内的外部程序。运行的docker通过docker Exec API来触发外部应用。

        我们期望，consul Agent用户访问Docker HTTP API或UNIX套接字。Consul使用$DOCKER_HOST来确定Docker API端点。应用程序将运行，并对在容器内运行的服务执行健康检查，并返回适当的退出代码。Check按照指定的时间间隔调用。

        如果在同一个host主机上有多重shell，那么同样需要配置shell参数。

输出限制在4K以内，输出大于4K将截断。

{
"check": {
    "id": "mem-util",
    "name": "Memoryutilization",
    "docker_container_id": "f972c95ebf0e",
    "shell": "/bin/bash",
    "script": "/usr/local/bin/check_mem.py",
    "interval": "10s"
  }
}
 

1.1.6 小结
        每一种check都必须包含name，id和notes两个是可选的。如果没有提供id，那么id会被设置为name。在一个节点中，check的ID都必须是唯一的。如果名字是冲突的，那么ID就应该设置。

        字段Notes主要是增强checks的可读性。Script check中，notes字段可以由脚本生成。同样，适用HTTP接口更新TTL check的外部程序一样可以设置notes字段。

 

1.1.7  Check脚本
        Check脚本可以自由地做任何事情确定check的状态。唯一的限制是：退出代码必须遵循下面的约定：

退出代码0 – 正常
退出代码1 – 告警
其他值 - 失败。
        Consul依赖此约定。脚本其他的输出都保存在notes字段中，可以供人查看。

 

1.1.8  健康状态初始化
        默认情况下，当checks注册到Consul agent时，健康状态立即被设置为“Critical”。可以防止服务直接被注册为通过（“passing”）状态，在进入service pool前认为是健康状态。在某些情况下，checks可能需要指定健康检查的初始状态，可以通过设置“status”字段来实现。

如下：

{
  "check": {
    "id": "mem",
    "script": "/bin/check_mem",
    "interval": "10s",
    "status": "passing"
  }
}
初始状态设置为passing。



1.1.9  Service-boundchecks
        健康检查（Health checks）或者有可能绑定到指定的服务。这将确保健康检查的状态只会影响给定的服务而不是整个节点。服务绑定健康检查需要提供一个service_id字段。

{
  "check": {
    "id": "web-app",
    "name": "WebApp Status",
    "service_id": "web-app",
    "ttl": "30s"
  }
}
        在上述示例中，web-app健康检查如果失败了，只会影响web-app服务的有效性，本节点的其他服务是不受影响的。

 

1.1.10  MultipleCheck Definitions
多个check定义，可以使用字段“checks”，示例：

{
  "checks": [
    {
      "id": "chk1",
      "name": "mem",
      "script": "/bin/check_mem",
      "interval": "5s"
    },
    {
      "id": "chk2",
      "name": "/health",
      "http": "http://localhost:5000/health",
      "interval": "15s"
    },
    {
      "id": "chk3",
      "name": "cpu",
      "script": "/bin/check_cpu",
      "interval": "10s"
    },
    ...
  ]
}

注，实践过程中发现，脚本并不支持python，必须为shell脚本
--------------------- 
作者：YoungerChina 
来源：CSDN 
原文：https://blog.csdn.net/younger_china/article/details/52243759 
版权声明：本文为博主原创文章，转载请附上博文链接！
```