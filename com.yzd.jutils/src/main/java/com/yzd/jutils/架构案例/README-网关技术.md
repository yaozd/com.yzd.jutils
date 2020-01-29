- 网关技术
    - [微服务与网关技术 SIA-GateWay](https://www.oschina.net/p/sia-gateway)
    - [大公司为什么都有API网关？聊聊API网关的作用](https://blog.csdn.net/weixin_45932995/article/details/103559425)
    - [微服务五种开源API网关实现组件对比](https://blog.csdn.net/squirrelanimal0922/article/details/88946900)
- 网关参考：扩展功能
    - [京东千万并发 API 网关实践之路！](https://blog.csdn.net/uxiAD7442KMy1X86DtM3/article/details/104082256) -功能参考
    - [一文搞懂 API 网关](https://blog.csdn.net/wnvalentin/article/details/100083874) -功能参考
    - [微服务五种开源API网关实现组件对比](https://blog.csdn.net/squirrelanimal0922/article/details/88946900)
    - []()
- APISIX 说明
    - [incubator-apisix](https://github.com/apache/incubator-apisix)
    - [APISIX 说明](https://github.com/apache/incubator-apisix/blob/master/doc/README_CN.md) -功能参考
    - [国产开源API网关项目进入Apache孵化器：APISIX](https://blog.csdn.net/j3T9Z7H/article/details/102812778) -功能参考
    - []()
    ```
    插件热加载：无需重启服务，完成插件热加载或卸载。
    HTTPS：根据 TLS 扩展字段 SNI(Server Name Indication) 动态加载证书。
    动态负载均衡：跨多个上游服务的动态负载均衡，目前已支持 round-robin 和一致性哈希算法。
    key-auth：基于 Key Authentication 的用户认证。
    JWT-auth：基于 JWT (JSON Web Tokens) Authentication 的用户认证。
    basic-auth：基于 basic auth 的用户认证。
    limit-count：基于“固定窗口”的限速实现。
    limit-req：基于漏桶原理的请求限速实现。
    limit-conn：限制并发请求（或并发连接）。
    prometheus：以 Prometheus 格式导出 APISIX 自身的状态信息，方便被外部 Prometheus 服务抓取。
    OpenTracing：支持 Zikpin 和 Apache SkyWalking。
    grpc-transcode：REST <--> gRPC 转码。
    serverless：允许在 APISIX 中的不同阶段动态运行 Lua 代码。
    ip-restriction: IP 黑白名单。
    openid-connect
    redirect: URI 重定向。
    response-rewrite: 支持自定义修改返回内容的 status code、body、headers。
    fault-injection：故障注入，可以返回指定的响应体、响应码和响应时间，从而提供了不同的失败场景下处理的能力，例如服务失败、服务过载、服务高延时等。
    //////////////////
    功能
    云原生: 平台无关，没有供应商锁定，无论裸机还是 Kubernetes，APISIX 都可以运行。
    
    热更新和热插件: 无需重启服务，就可以持续更新配置和插件。
    
    动态负载均衡：动态支持有权重的 round-robin 负载平衡。
    
    支持一致性 hash 的负载均衡：动态支持一致性 hash 的负载均衡。
    
    SSL：动态加载 SSL 证书。
    
    HTTP(S) 反向代理
    
    健康检查：启用上游节点的健康检查，将在负载均衡期间自动过滤不健康的节点，以确保系统稳定性。
    
    熔断器: 智能跟踪不健康上游服务。
    
    身份认证: key-auth, JWT。
    
    限制速率
    
    限制请求数
    
    限制并发
    
    代理请求重写: 支持重写请求上游的host、uri、schema、enable_websocket、headers信息。
    
    OpenTracing: 支持 Apache Skywalking 和 Zipkin
    
    监控和指标: Prometheus
    
    gRPC 协议转换：支持协议的转换，这样客户端可以通过 HTTP/JSON 来访问你的 gRPC API。
    
    Serverless: 在 APISIX 的每一个阶段，你都可以添加并调用自己编写的函数。
    
    自定义插件: 允许挂载常见阶段，例如rewrite，access，header filer，body filter和log，还允许挂载 balancer 阶段。
    
    控制台: 内置控制台来操作 APISIX 集群。
    
    版本控制：支持操作的多次回滚。
    
    CLI: 使用命令行来启动、关闭和重启 APISIX。
    
    REST API
    
    Websocket 代理
    
    IPv6：支持使用 IPv6 格式匹配路由。
    
    集群：APISIX 节点是无状态的，创建配置中心集群请参考 etcd Clustering Guide。
    
    可扩展：简单易用的插件机制方便扩展。
    
    高性能：在单核上 QPS 可以达到 24k，同时延迟只有 0.6 毫秒。
    
    防御 ReDoS(正则表达式拒绝服务)
    
    IP 黑名单
    
    IdP 支持: 支持外部的身份认证服务，比如 Auth0，okta 等，用户可以借此来对接 Oauth2.0 等认证方式。
    
    单机模式: 支持从本地配置文件中加载路由规则，在 kubernetes(k8s) 等环境下更友好。
    
    全局规则：允许对所有请求执行插件，比如黑白名单、限流限速等。
    
    TCP/UDP 代理: 动态 TCP/UDP 代理。
    
    动态 MQTT 代理: 支持用 client_id 对 MQTT 进行负载均衡，同时支持 MQTT 3.1.* 和 5.0 两个协议标准。
    ```

- 网关安全防御
    - [一场跨年晚会挣了50亿，B站在微服务治理中如何探索与实践？](https://mp.weixin.qq.com/s?__biz=MzIxMzEzMjM5NQ==&mid=2651035183&idx=1&sn=6563e00952c06267d358262657807fbf)
- API网关的使用场景
    - [API网关的使用场景](https://blog.csdn.net/SilenceCarrot/article/details/79527464)
    - []()