## 效率工具
- SSO (单点登录)
    - 支持按钮级控制、分级授权、分级管理
    - [http://www.xuxueli.com/xxl-sso/](https://gitee.com/xuxueli0323/xxl-sso)
    - [https://kawhii.github.io/sso](https://gitee.com/Kawhi-Carl/sso)
    - [基于Spring Cloud，写了一个单点登陆的starter](https://my.oschina.net/u/4447432/blog/4366850)
    - 淘宝登录或微信登录时，建议设置两个ID：登录ID,与用户ID。做为订单数据在用户绑定与解绑时，关系拆分。订单记录：订单ID,登录ID,用户ID
    - [https://github.com/apereo/cas](https://github.com/apereo/cas) -Central Authentication Service (CAS)
- 接口管理平台
    - [BAT都在使用的开源接口管理平台](https://my.oschina.net/u/4447432/blog/4366974)
    - [https://github.com/YMFE/yapi](https://github.com/YMFE/yapi)
- 注解
    - [用注解解决业务逻辑和缓存逻辑的深度耦合](https://my.oschina.net/u/4447432/blog/4425055)
- Prometheus 指标打点
- Sentinel 动态限流
- 服务发现与注册- Nacos
- apm
    - [Zipkin、Jaeger、SkyWalking、OpenTracing](https://blog.csdn.net/weixin_34268310/article/details/91438444)
- SQL自动检查神器
    - [SQL自动检查神器-Yearning MYSQL 是一个SQL语句审核平台](https://www.jianshu.com/p/099a9282229c)
- redis实现延迟消息队列
    - [https://github.com/yangwenjie88/delay-queue](https://github.com/yangwenjie88/delay-queue)
    - [基于Redis的延时任务队列](https://blog.csdn.net/liuyanglglg/article/details/96299435)
    - [xxL-mq](http://www.xuxueli.com/xxl-mq/)
    ```
    一款轻量级分布式消息队列，支持 "并发消息、串行消息、广播消息、延迟消息、事务消息、失败重试、超时控制" 等消息特性。现已开放源代码，开箱即用
    ```
- [EventBus-实现java状态机](https://www.jianshu.com/p/8def04b34b3c) -推荐参考byArvin
- Web爬虫
    - [http://www.xuxueli.com/xxl-crawler](http://www.xuxueli.com/xxl-crawler)
- 分布式事务
    - RocketMQ
        - [基于RocketMQ的分布式事务解决方案](https://www.jianshu.com/p/286cac4625b6)
            ```
            回查本地事物消息是否成功的操作，来对MQ的消息做个补偿动作实现数据一致性
            回查表：checkTransactionState
            RocketMQ事务消息回查设计方案
            https://blog.csdn.net/qq_27529917/article/details/79802406
            
            ```
        - [springboot-rocketmq-example](https://github.com/zygfengyuwuzu/springboot-rocketmq-example)
            - [听说RocketMQ与MYSQL事务消息联姻了](https://mp.weixin.qq.com/s/zdPRpEahzQOmba3gz_APaA)
            ```
            对RocketMQ示例的编写，并针对事物消息与mysql事物相结合
            https://github.com/search?p=1&q=rocketmq+transaction&type=Repositories
            ```
        - []()
    - seata 阿里开源
        - [https://github.com/seata/seata-samples.git](https://github.com/seata/seata-samples.git)
        - []()
-
-
-
-
## 敏捷开发-故事地图