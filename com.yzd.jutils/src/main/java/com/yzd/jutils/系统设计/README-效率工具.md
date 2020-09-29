## 效率工具
- 系统架构设计
    - [日均百万订单下的高可用苏宁拼购系统架构设计 朱羿全](https://myslide.cn/slides/19942)
- [StackOverflow创始人关于如何高效编程的清单](https://blog.csdn.net/qq_35246620/article/details/73015315)
    - []()
- SLA
    - [SLA服务可用性4个9是什么意思？怎么达到？](https://blog.csdn.net/varyall/article/details/80010784)
    ```
    SLA：服务等级协议（简称：SLA，全称：service level agreement）。
    是在一定开销下为保障服务的性能和可用性，服务提供商与用户间定义的一种双方认可的协定
    99.99 = 8760 * 0.0001 = 0.876小时 = 0.876 * 60 = 52.6分钟
    ```
- Google监控的4个黄金指标
    - [Google监控的4个黄金指标](https://blog.csdn.net/weixin_39123372/article/details/106070586)
    > google的黄金指标： 流量，延迟，错误，饱和度
    
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
    - [https://gitee.com/spirit_demon/CrawlerDemon](https://gitee.com/spirit_demon/CrawlerDemon) - 分布式爬虫 Crawler
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
        - [https://gitee.com/linzhangxian/seata-cloud](https://gitee.com/linzhangxian/seata-cloud)
            > Spring Cloud 整合最新版seata分布式事务 Seata 是一款开源的分布式事务解决方案
        - []()
- 分布式缓存
    - Codis redis
    - [Codis与RedisCluster的原理详解](https://www.cnblogs.com/pingyeaa/p/11294773.html)
    - [JVM内存级分布式缓存Hazelcast](https://my.oschina.net/u/3768341/blog/3071997)
    - binlog+Canal+Kafka 做好映射。“冲”掉redis上旧数据--明华（让架构保持简单） 
    - []()
    - []()
- RETE算法的纯Java规则引擎
    - [https://gitee.com/youseries/urule](https://gitee.com/youseries/urule)
    > URULE是一款基于RETE算法的纯Java规则引擎，提供规则集、决策表、决策树、评分卡，规则流等各种规则表现工具及基于网页的可视化设计器，可快速开发出各种复杂业务规则
- 分布式聚合支付系统
    - [https://gitee.com/jmdhappy/xxpay-master](https://gitee.com/jmdhappy/xxpay-master)
    > 使用spring-cloud开发的分布式聚合支付系统,集成微信,支付宝,易宝支付,京东支付,IAP支付等.
- 后台管理框架
    - [https://gitee.com/geek_qi/cloud-platform](https://gitee.com/geek_qi/cloud-platform) -spring-cloud
    - [https://gitee.com/aun/Timo](https://gitee.com/aun/Timo)
- 后台模板
    - [https://gitee.com/bmloveyou/Ace-Admin](https://gitee.com/bmloveyou/Ace-Admin)
- 数据压缩
    - [https://gitee.com/yu120/compress](https://gitee.com/yu120/compress)
    > 基于gzip、deflate、lz4、snappy、lzo等算法实现数据压缩，主要用于RPC通讯数据的压缩！
- 分布式websocket聊天程序
    - [CookIM - 一个基于akka的分布式websocket聊天程序](https://gitee.com/cookeem/CookIM)
    - []()
- 分库分表
    - [分库分表？如何做到永不迁移数据和避免热点？](https://blog.csdn.net/qq_35246620/article/details/90407308)
    - [炸！亿级数据DB秒级平滑扩容](https://blog.csdn.net/z50L2O08e2u4afToR9A/article/details/89839471)
- 跨库分页
    - [业界难题-“跨库分页”的四种方案](https://blog.csdn.net/shenjian58/article/details/89849907)
- 消息队列
    - [面试题：Kafka、ActiveMQ、RabbitMQ、RocketMQ 有什么优缺点？](https://blog.csdn.net/qq_35246620/article/details/107175767)
    
- 异常检测
    - [https://gitee.com/guobinhit/archimedes](https://gitee.com/guobinhit/archimedes)
    - [https://github.com/guobinhit/archimedes](https://github.com/guobinhit/archimedes)
- 秒杀解决方案
    - ["小米在印度把亚马逊搞挂了"事件的秒杀解决方案](https://gitee.com/chanjarster/artemis-disruptor-miaosha)
- netty实现的高性能内网穿透
    - [https://gitee.com/haojiangbo/venomous_sting](https://gitee.com/haojiangbo/venomous_sting) -毒刺
- 通用报警框架
    - [https://gitee.com/liuyueyi/quick-alarm](https://gitee.com/liuyueyi/quick-alarm)
- mock数据
    - [https://gitee.com/kkk001/mockj](https://gitee.com/kkk001/mockj)
- 数据同步中间件
    - [https://gitee.com/sxfad/porter](https://gitee.com/sxfad/porter)
- 阿里云组件封装
    - [https://gitee.com/ITEater/vinus-spring-boot-starter](https://gitee.com/ITEater/vinus-spring-boot-starter)
    > afs（人机验证，captcha）、sms（短信验证）、oss（对象存储）、sts（访问控制）四个模块
- Istio分层架构
    - [Istio分层架构？80%的人有误解](https://blog.csdn.net/z50L2O08e2u4afToR9A/article/details/89597120)

- [技术管理岗位的思考总结](https://www.cnblogs.com/xiaoyangjia/p/11338959.html)
- 搜索服务
    - Sonic
        - [Sonic 是一个快速、轻量级、无模式的搜索后端](https://gitee.com/mirrors/Sonic)
        - [Sonic：用Rust编写的Elasticsearch的极简替代品](https://zhuanlan.zhihu.com/p/63963140)
- ES集群管理
    - [可视化工具之cerebro](https://blog.csdn.net/liumiaocn/article/details/98517815)
- OSS(对象存储)
    - MinIO是一个开源的对象存储服务 -推荐byArvin
        - [SpringBoot2 整合MinIO中间件，实现文件便捷管理](https://mp.weixin.qq.com/s/93aQawYIT2Ce-9CvkHjQrQ)
        - [https://github.com/cicadasmile/middle-ware-parent](https://github.com/cicadasmile/middle-ware-parent)
- 如何平稳用户无感知的完成系统重构升级
    - [如何平稳用户无感知的完成系统重构升级](https://segmentfault.com/a/1190000023924409)
    ```
    面临几个问题：
        1.代码逻辑的切换：包括增删改查
        2.表结构的变更
        3.数据的迁移
        4.迁移过程中用户无感知
    ```
-
-
-
-
## 敏捷开发-故事地图

## SpringCloud
- [https://gitee.com/zuihou111/zuihou-admin-cloud](https://gitee.com/zuihou111/zuihou-admin-cloud)
- [https://gitee.com/zscat/mallcloud-platform](https://gitee.com/zscat/mallcloud-platform)
- [https://github.com/fafeidou/fast-cloud-nacos](https://github.com/fafeidou/fast-cloud-nacos)
- []()

## 工具及其它
- [工具及其它](https://blog.csdn.net/qq_35246620/category_6633794.html) -推荐参考byArvin
- [工具及其它](https://blog.csdn.net/qq_35246620/category_6633794.html) -推荐参考byArvin

## 分布式系统
- [https://gitee.com/zscat/mallcloud-platform](https://gitee.com/zscat/mallcloud-platform)
- [https://gitee.com/catshen/zscat_sw](https://gitee.com/catshen/zscat_sw)
```
AOP、RPC、分布式缓存、限流、降级、熔断、统一配置中心、swagger api自动生成、
Opentracing数据追踪、metrics数据监控、分布式session、代码生成器、shiro安全控制
统一认证功能

支持oauth2的四种模式登录
支持用户名、密码加图形验证码登录
支持手机号加密码登录
支持openId登录
支持第三方系统单点登录
分布式系统基础支撑

服务注册发现、路由与负载均衡
服务降级与熔断
服务限流(url/方法级别)
统一配置中心
统一日志中心
统一分布式缓存操作类、cacheManager配置扩展
分布式锁
分布式任务调度器
支持CI/CD持续集成(包括前端和后端)
分布式高性能Id生成器
分布式事务
系统监控功能

服务调用链监控
应用拓扑图
慢服务检测
服务Metric监控
应用监控(应用健康、JVM、内存、线程)
错误日志查询
慢查询SQL监控
应用吞吐量监控(qps、rt)
服务降级、熔断监控
服务限流监控
分库分表、读写分离
业务基础功能支撑

高性能方法级幂等性支持
RBAC权限管理，实现细粒度控制(方法、url级别)
快速实现导入、导出功能
数据库访问层自动实现crud操作
代码生成器
基于Hutool的各种便利开发工具
网关聚合所有服务的Swagger接口文档
统一跨域处理
统一异常处理
```