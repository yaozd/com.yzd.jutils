### dubbo配置参考：
- [dubbo常用配置及注意事项](https://www.cnblogs.com/linjiqin/p/7856270.html)
- [dubbo 配置文件详解](http://www.cnblogs.com/linjiqin/p/5859153.html)

-dubbo常用配置及注意事项
```
1、启动时检查
缺省会在启动时检查依赖的服务是否可用，不可用时会抛出异常，阻止Spring初始化完成，以便上线时，能及早发现问题，默认check=true。

关闭所有服务的启动时检查：(没有提供者时报错)
<dubbo:consumer check="false" />

关闭某个服务的启动时检查：(没有提供者时报错)
<dubbo:reference interface="com.foo.BarService" check="false" />
其它的启动时检查还包括：注册中心

2、直连提供者
在开发及测试环境下，经常需要绕过注册中心，只测试指定服务提供者，这时候可能需要点对点直连，
点对点直联方式，将以服务接口为单位，忽略注册中心的提供者列表。
<dubbo:reference id="xxxService" interface="com.alibaba.xxx.XxxService" url="dubbo://localhost:20890" />  

3、服务分组
当一个接口有多种实现时，可以用group区分。
provider：
<dubbo:service group="feedback" interface="com.xxx.IndexService" ref="indexServiceFeedback" />
<dubbo:service group="member" interface="com.xxx.IndexService"   ref="indexServiceMember" />

cosumer：
<dubbo:reference id="feedbackIndexService" group="feedback" interface="com.xxx.IndexService" />
<dubbo:reference id="memberIndexService" group="member" interface="com.xxx.IndexService" />

4、多版本
当一个接口实现，出现不兼容升级时，可以用版本号过渡，版本号不同的服务相互间不引用。
一般处理步骤
1）在低压力时间段，先升级一半提供者为新版本
2）再将所有消费者升级为新版本
3）然后将剩下的一半提供者升级为新版本
<dubbo:service interface="com.foo.BarService" version="1.0.0" />
<dubbo:service interface="com.foo.BarService" version="2.0.0" />
 
5、异步调用
可完成并行调用多个远程服务。异步总是不等待返回。
<dubbo:reference id="fooService" interface="com.alibaba.foo.FooService">
    <dubbo:method name="findFoo" async="true" />
</dubbo:reference>

6、延迟暴露
如果你的服务需要Warmup时间，比如初始化缓存，等待相关资源就位等，可以使用delay进行延迟暴露。
<dubbo:provider delay="-1" />
当然，也可以配置到服务级别，但有些需要地方需要注意。

7、dubbo:protocol属性
threadpool：线程池类型，可选：fixed/cached ，默认fixed 。
threads ：服务线程池大小(固定大小) ，默认为100

payload：请求及响应数据包大小限制，单位：字节，默认为88388608(=8M)
如：<dubbo:protocol name="dubbo" port="27001" threadpool="cached" threads="20"/>

ThreadPool
fixed 固定大小线程池，启动时建立线程，不关闭，一直持有。(缺省)
cached 缓存线程池，空闲一分钟自动删除，需要时重建。
limited可伸缩线程池，但池中的线程数只会增长不会收缩。(为避免收缩时突然来了大流量引起的性能问题)。

8、dubbo:application
<dubbo:application name="xxx_service" />
name必填。当前应用名称，用于注册中心计算应用间依赖关系，注意：消费者和提供者应用名不要一样
```

-dubbo配置文件详解
```
一、dubbo常用配置

复制代码
<dubbo:service/> 服务配置，用于暴露一个服务，定义服务的元信息，一个服务可以用多个协议暴露，一个服务也可以注册到多个注册中心。
eg、<dubbo:service ref="demoService" interface="com.unj.dubbotest.provider.DemoService" />

<dubbo:reference/> 引用服务配置，用于创建一个远程服务代理，一个引用可以指向多个注册中心。
eg、<dubbo:reference id="demoService" interface="com.unj.dubbotest.provider.DemoService" />

<dubbo:protocol/> 协议配置，用于配置提供服务的协议信息，协议由提供方指定，消费方被动接受。
eg、<dubbo:protocol name="dubbo" port="20880" />

<dubbo:application/> 应用配置，用于配置当前应用信息，不管该应用是提供者还是消费者。
eg、<dubbo:application name="xixi_provider" />
    <dubbo:application name="hehe_consumer" />

<dubbo:module/> 模块配置，用于配置当前模块信息，可选。
<dubbo:registry/> 注册中心配置，用于配置连接注册中心相关信息。
eg、<dubbo:registry address="zookeeper://192.168.2.249:2181" />

<dubbo:monitor/> 监控中心配置，用于配置连接监控中心相关信息，可选。
<dubbo:provider/> 提供方的缺省值，当ProtocolConfig和ServiceConfig某属性没有配置时，采用此缺省值，可选。
<dubbo:consumer/> 消费方缺省配置，当ReferenceConfig某属性没有配置时，采用此缺省值，可选。
<dubbo:method/> 方法配置，用于ServiceConfig和ReferenceConfig指定方法级的配置信息。
<dubbo:argument/> 用于指定方法参数配置。
复制代码
二、服务调用超时设置

上图中以timeout为例，显示了配置的查找顺序，其它retries, loadbalance, actives也类似。
方法级优先，接口级次之，全局配置再次之。
如果级别一样，则消费方优先，提供方次之。

其中，服务提供方配置，通过URL经由注册中心传递给消费方。
建议由服务提供方设置超时，因为一个方法需要执行多长时间，服务提供方更清楚，如果一个消费方同时引用多个服务，就不需要关心每个服务的超时设置。
理论上ReferenceConfig的非服务标识配置，在ConsumerConfig，ServiceConfig, ProviderConfig均可以缺省配置。

三、启动时检查 
Dubbo缺省会在启动时检查依赖的服务是否可用，不可用时会抛出异常，阻止Spring初始化完成，以便上线时，能及早发现问题，默认check=true。

如果你的Spring容器是懒加载的，或者通过API编程延迟引用服务，请关闭check，否则服务临时不可用时，会抛出异常，拿到null引用，如果check=false，总是会返回引用，当服务恢复时，能自动连上。

可以通过check="false"关闭检查，比如，测试时，有些服务不关心，或者出现了循环依赖，必须有一方先启动。

复制代码
1、关闭某个服务的启动时检查：(没有提供者时报错)
<dubbo:reference interface="com.foo.BarService" check="false" />

2、关闭所有服务的启动时检查：(没有提供者时报错)  写在定义服务消费者一方
<dubbo:consumer check="false" />

3、关闭注册中心启动时检查：(注册订阅失败时报错)
<dubbo:registry check="false" />
复制代码
引用缺省是延迟初始化的，只有引用被注入到其它Bean，或被getBean()获取，才会初始化。
如果需要饥饿加载，即没有人引用也立即生成动态代理，可以配置：

<dubbo:reference interface="com.foo.BarService" init="true" />
四、订阅
1、问题
为方便开发测试，经常会在线下共用一个所有服务可用的注册中心，这时，如果一个正在开发中的服务提供者注册，可能会影响消费者不能正常运行。

2、解决方案
可以让服务提供者开发方，只订阅服务(开发的服务可能依赖其它服务)，而不注册正在开发的服务，通过直连测试正在开发的服务。

禁用注册配置：
<dubbo:registry address="10.20.153.10:9090" register="false" />
或者：
<dubbo:registry address="10.20.153.10:9090?register=false" />
五、回声测试(测试服务是否可用)
回声测试用于检测服务是否可用，回声测试按照正常请求流程执行，能够测试整个调用是否通畅，可用于监控。
所有服务自动实现EchoService接口，只需将任意服务引用强制转型为EchoService，即可使用。

eg、<dubbo:reference id="memberService" interface="com.xxx.MemberService" />

MemberService memberService = ctx.getBean("memberService"); // 远程服务引用
EchoService echoService = (EchoService) memberService; // 强制转型为EchoService
String status = echoService.$echo("OK"); // 回声测试可用性
assert(status.equals("OK"))
六、延迟连接 
延迟连接，用于减少长连接数，当有调用发起时，再创建长连接。
只对使用长连接的dubbo协议生效。

<dubbo:protocol name="dubbo" lazy="true" />
七、令牌验证 
防止消费者绕过注册中心访问提供者，在注册中心控制权限，以决定要不要下发令牌给消费者，注册中心可灵活改变授权方式，而不需修改或升级提供者

复制代码
1、全局设置开启令牌验证：
<!--随机token令牌，使用UUID生成-->
<dubbo:provider interface="com.foo.BarService" token="true" />

<!--固定token令牌，相当于密码-->
<dubbo:provider interface="com.foo.BarService" token="123456" />

2、服务级别设置开启令牌验证：
<!--随机token令牌，使用UUID生成-->
<dubbo:service interface="com.foo.BarService" token="true" />

<!--固定token令牌，相当于密码-->
<dubbo:service interface="com.foo.BarService" token="123456" />

3、协议级别设置开启令牌验证：
<!--随机token令牌，使用UUID生成-->
<dubbo:protocol name="dubbo" token="true" />

<!--固定token令牌，相当于密码-->
<dubbo:protocol name="dubbo" token="123456" />
复制代码
八、日志适配 
缺省自动查找：log4j、slf4j、jcl、jdk

可以通过以下方式配置日志输出策略：dubbo:application logger="log4j"/>

访问日志：
如果你想记录每一次请求信息，可开启访问日志，类似于apache的访问日志。此日志量比较大，请注意磁盘容量。

将访问日志输出到当前应用的log4j日志：

<dubbo:protocol accesslog="true" />
将访问日志输出到指定文件：

<dubbo:protocol accesslog="http://10.20.160.198/wiki/display/dubbo/foo/bar.log" />
九、配置Dubbo缓存文件

配置方法如下：

<dubbo:registryfile=”${user.home}/output/dubbo.cache” />
注意：
文件的路径，应用可以根据需要调整，保证这个文件不会在发布过程中被清除。如果有多个应用进程注意不要使用同一个文件，避免内容被覆盖。

这个文件会缓存：
注册中心的列表
服务提供者列表

有了这项配置后，当应用重启过程中，Dubbo注册中心不可用时则应用会从这个缓存文件读取服务提供者列表的信息，进一步保证应用可靠性。
```