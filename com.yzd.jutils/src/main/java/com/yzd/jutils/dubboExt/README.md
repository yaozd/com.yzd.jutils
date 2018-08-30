
### 如果要关闭直连，则在provider使用token=true
### dubbo(provider,consumer)点到点直连配置
```

如果要关闭直连，则在provider使用token=true
https://blog.csdn.net/ichsonx/article/details/39008541
 <!-- 声明需要暴露的服务接口,直连时，token=true要去掉，会有不安全因素，但直连一般用于内部使用，安全问题可以暂忽略 -->
     <dubbo:service interface="com.hoperun.biz.smk.service.SmkService" ref="smkService"  version="1.0"/>

</beans>
```
### Dubbo消费端直连服务提供者（开发调试）
```
Dubbo高级篇_11_Dubbo消费端直连服务提供者（开发调试）

https://blog.csdn.net/frankenjoy123/article/details/78529381
https://blog.csdn.net/hardworking0323/article/details/51166113/

直连提供者
在开发及测试环境下，经常需要绕过注册中心，只测试指定服务提供者，这时候可能需要点对点直连，
点对点直联方式，将以服务接口为单位，忽略注册中心的提供者列表，
A接口配置点对点，不影响B接口从注册中心获取列表。



(1) 如果是线上需求需要点对点，可在<dubbo:reference>中配置url指向提供者，将绕过注册中心，多个地址用分号隔开，配置如下：(1.0.6及以上版本支持)

<dubbo:reference id="xxxService"interface="com.alibaba.xxx.XxxService"url="dubbo://localhost:20890"/>
(2) 在JVM启动参数中加入-D参数映射服务地址，如：
(key为服务名，value为服务提供者url，此配置优先级最高，1.0.15及以上版本支持)

java -Dcom.alibaba.xxx.XxxService=dubbo://localhost:20890
	注意
为了避免复杂化线上环境，不要在线上使用这个功能，只应在测试阶段使用。
(3) 如果服务比较多，也可以用文件映射，如：
(用-Ddubbo.resolve.file指定映射文件路径，此配置优先级高于<dubbo:reference>中的配置，1.0.15及以上版本支持)
(2.0以上版本自动加载${user.home}/dubbo-resolve.properties文件，不需要配置)

java -Ddubbo.resolve.file=xxx.properties
然后在映射文件xxx.properties中加入：
(key为服务名，value为服务提供者url)

com.alibaba.xxx.XxxService=dubbo://localhost:20890
	注意
为了避免复杂化线上环境，不要在线上使用这个功能，只应在测试阶段使用。
在生产环境使用情况是，服务消费端只消费指定Provider提供者的服务


```
### java -Ddubbo.resolve.file=xxx.properties
```
java -Ddubbo.resolve.file=xxx.properties
-Ddubbo.resolve.file=xxx.properties配置在启动界面中的“VM options”参数中
```
### Dubbo服务接口的设计原则
```
Dubbo高级篇_07_Dubbo服务接口的设计原则
https://blog.csdn.net/hardworking0323/article/details/51142320
1 、设计方式

action->facade->biz->dao

好的Dubbo服务接口设计，并非只是纯粹的接口服务化

2.接口类型

简单的数据查询接口:action.facade、dao(例根据Id查询记录)

带业务逻辑的数据查询接口:action、facade、biz、dao(复杂的查询，带业务逻辑)

简单的数据写入接口：action、facade、dao(简单数据插入)

带业务逻辑的数据写入接口:action、facade、biz、dao(有业务逻辑的数据处理)

同步接口

异步接口

3.设计原则

服务接口尽可能大粒度，每个服务方法应代表一个功能，而不是某功能的一个步骤，否则将地面临分布式事务问题，

Dubbo暂未提供分布式事务支持，同时可以减少系统间的网络交互



服务接口建议以业务场景为单位划分，并对相近的业务做抽象,防止接口数量爆增(爆炸)。

例:某一个接口有多个实现，做成一个接口，再在dubbo分组中多实现



不建议使用过于抽象的通用接口，如Map query(Map),这样的接口没有明确语义，会给后期维护带来不便





接口版本:

每个接口应定义版本号，为后续不兼容升级提供可能

如:

<dubbo:service interface="com.xxService" version="1.0"/>



接口兼容性:

服务接口增加方法，或服务模型增加字段，可向后兼容；

删除方法或删除字段，将不兼容，枚举类型新增字段也不兼容，需要通过变更版本号升级。



异常处理:

建议使用异常汇报错误，而不是返回错误码，异常信息能携带更多的信息，以及语义更友好。



如果担心性能问题，在必要时，可以通过override掉异常类的finlllnStackTrace()方法为空方法，使其不拷贝栈信息。



查询方法不建议抛出checked异常，否则调用 方在查询 时将过多的try...catch,并且不能进行有效处理。



服务提供方不应将DAO或者SQL等异常抛给消费方，应在服务实现中对消费方不关心的异常进行包装，否则可能出现消费方无法反序列化相应异常



必要的接口输入参数校验



在Provider上尽量多配置Consumer端属性:

原因如下:

作为服务的提供者，比服务使用方更清楚服务性能参数，如调用的超时时间，合理的重试次数，并发控制数量，负载均衡 ，等等

在Provider配置后，Consumer不配置则会使用Provider的配置值 ，

即Provider配置可以作为Comsumer的缺省值，否则，Consumer会使用Consumer端的全局设置，这对于Provider不可控的，并且往往是不合理的



Provider上尽量多配置Consumer端的属性，让Provider实现者一开始就思考Provider服务特点、服务质量的问题

样例:


```