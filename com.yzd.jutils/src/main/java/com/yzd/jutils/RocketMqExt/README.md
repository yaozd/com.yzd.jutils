# 场景分析
- [RocketMQ 在联想大数据中的应用简析](https://www.infoq.cn/article/3qUmN-5bmVBlkJLP1lyi?) 推荐参考 
```
应用 RocketMQ 的使用场景中经常会出现：异步请求，应用解耦和日志处理等场景情况。
使用 RocketMQ 做为应对特殊的业务处理流程的技术手段:
需要在数据生产端承接瞬时高数据流量，在数据消费端平稳地将数据推送到下游业务线。
```

# 电子书
- [RocketMQ使用排查指南 中文pdf高清版](https://www.jb51.net/books/741642.html)

# 案例参考
- [RocketMQ 在联想大数据中的应用简析](https://www.infoq.cn/article/3qUmN-5bmVBlkJLP1lyi?)
```
RocketMQ 的吞吐量基本保证在 11~12w/s，磁盘 IO 率虽已接近 100%

```

# rocketmq管理界面
- [RcoketMq集群安装和RocketMQ web界面监控rocketmq-console部署](https://blog.csdn.net/weidong22/article/details/79246726)
- [RocketMQ 学习之四 —— RocketMQ Web管理界面](https://blog.csdn.net/ruanhao1203/article/details/89496832)
- []()


# [RocketMqCurrencyBoot](https://github.com/atliwen/RocketMqCurrencyBoot)
``
https://github.com/atliwen/RocketMqCurrencyBoot
https://github.com/yaozd/RocketMqCurrencyBoot`
``
- [RabbitMQ都写了，RocketMQ怎么能落下](https://my.oschina.net/u/4447432/blog/4429039)
    - [https://github.com/erlieStar/rocketmq-examples](https://github.com/erlieStar/rocketmq-examples)
对MQ高级封装。 支持规则匹配 转发消息、外调 WEB API、内销。 容错次数设置等..


    使用理念：

			内销模式

				 建议使用场景为 消息生命末端，也就是对消息的最后的处理 。
				 由于需要编写 消息逻辑完全由 使用者控制。

			转发模式

				建议使用场景为  对消息分流控制、对消息的加工处理。
				提供
					消息处理接口

					 	只要实现改接口，就可以对消息进行二次加工处理

				   	自定义规则匹配

				   		自定义规则控制消息的转发。如 消息中包含字符串 “客户A”，改消息将转发到 配置的对应 队列中。

				   	匹配规则数据获取接口

				   		只需实现改接口，并将实现类注入到改模式中。就可以通过  数据源来动态控制  规则匹配。

				   		该接口是为了可以 动态更改匹配规则，不需要修改程序任何地方。

				注：
					执行流程 是先执行 消息处理 再执行 规则匹配转发

			外调模式

				建议使用场景为  满足非主流业务、不能统一处理、满足特殊需求   方便 进行的 动态拓展。

				提供
					自定义匹配条件

						进行消息过滤。如 消息中包含字符串 “客户A”，改消息将调用配置中的URL 请求访问该 WebApi.

					请求结果继续 转发

						调用WebApi后将会 进行判断是否 需要继续 消息流转。

						按照匹配规则条件 是否配置了转发 队列 和 标签，或者 转发模式 bean

					匹配规则数据获取接口

				   		只需实现改接口，并将实现类注入到改模式中。就可以通过  数据源来动态控制  规则匹配。

				   		该接口是为了可以 动态更改匹配规则，不需要修改程序任何地方。

			匹配规则权级

				匹配数据获取 接口类》XML匹配配置

			注：
				转发模式

						规则匹配成功，后续规则将不在处理。

				 		不建议一个匹配规则中 设置  多个转发 Topic

						因为如果有一个转发失败，后续将不进行转发，并且返回 消费失败。

						容错次数>1，将继续 消费该消息， 原先转发成功的队列将继续转发。

						会造成 一个消息多次转发到 同一个Topic 中。

				外调模式

						一个匹配规则 配置XML 转发 Topic 和 标签后该模式只能设置 一个 转发 Topic

						注入转发模式类后 将按照转发模式中 规则转发

			重要事项：

					强烈建议 功能单一化。

					设置多个匹配规则，和 多个转发Topic 规则后 。

					只要有一个处理失败，后续将不进行处理。

					并容错次数 >1  将继续消费该消息， 原先转发成功的还将继续转发。

					会照成 一个消息  多次转发 到 同一个Topic 中。



    版本更新

  

	版本V2.4.1

          为 方便 直接使用  不需要页面管理 添加 初始化启动生产者功能  判断条件  MQ.Topic 如果有值就直接启动

	版本V2.4.0

		发布私人Maven 库 使用更加方便
		<repository>
			<id>atliwen</id>
			<url>https://raw.githubusercontent.com/atliwen/maven-repo/master/repository</url>
		</repository>
		<dependency>
			<groupId>com.currencyboot.mq</groupId>
			<artifactId>currencyboot-mq</artifactId>
			<version>2.4.0-SNAPSHOT</version>
		</dependency>

 	版本V2.3.1

 		新增 消费超出设置次数  触发的接口类

	版本V2.3.0

		新增 生产端 事务 支持
		RocketMq 对事务的支持 生产端不支持多条发送消息 事务提交啊
		无必要，不推荐使用

	版本V2.2.2

	  		外调模式  请求  HTTP 响应 状态码  202  后续将不继续  转发 消息


	版本V2.2.1

	       	 修改HTML 发送消息功能按钮错误名称

	版本V2.2.0

      	新增 外调	Web Api 后续结果进行 转发消息

		外调功能增强后 支持两种模式

        	1、调用  Web Api 后  消息生命周期结束。
        	2、调用  Web Api 后  得到 响应数据，消息继续流转。

	版本V2.1.0

		新增 匹配数据获取 接口类

			通过 数据源来获取 匹配数据。 方便通过 数据源表操作就可以动态修改  匹配规则。

			匹配规则权级     匹配数据获取 接口类》XML匹配配置


	版本V2.0.0 （重要更新）

		转换为SpringBoot 版
        	原因：
        		SpringBoot通常被认为是快速开发的，其实我更加觉得它是为微服务提供的。

        		SpringBoot有简单友好的数据源依赖，非常适合进行并不复杂的数据源操作。并且我们通常需要MQ处理的并没有复杂的操作。

        		如果有，建议梳理业务流程，将程序拆分，通过消息转发，外调WEB api 来处理。

        		微服务的核心 我觉得就是 SOA ,当然也并不能为了SOA而SOA。一切的前提是从业务需求出发
	版本V1.1.1

		修改了  验证方法 Bug

	版本V1.1.0 （重要更新）

		新增：
			1	 外调模式  验证规则 匹配 调用 对应的 Web服务端

			2	 转发模式 和  外调模式 中 增加 对 消息实体的 数据转译 处理接口

	版本V1.0.1

		新增：

			MQ在一个集群中 只能消费 一个 topic 下 一个 Tags 的 页面提示


	版本V1.0.0

		该程序 消费模式有三种

			1
				内销模式 ：

					该模式下  只需要继承  baseMessageListenerConsumer  接口 实现该接口的方法 即可 。

					和使用 官方MQ一样 只是对其进行了包装拓展   对实体数据进行了 编码转换，和容错次数验证 。

			2
				外调模式 ：

					该模式是为了 将其调用 其他 服务 （ SOA 理念 和 restful api 理念 ）

					该模式的核心理念就是    高拓展性  低耦合度  高并发

					通过MQ消费端的 N线程消费，和 web服务器的 N请求处理的特性   结合实现一套 高并发的  消息处理模式。

					SOA（面向服务）和 restful api （一切都是资源） 非常建议 符合 改理念来编写 被调用 WEB服务端

					支持N个 Web服务端 调用  但是  只要一个调用失败 将不再执行后续 调用 并且 从新开始消费
					（不建议 指定多个 Topic 外调）

			3
				转发模式 ：

					该模式 支持 Tags 和  实体  数据信息  匹配   定义转发的队列 名称和 标签

					支持 N个转发对象和验证  但是  只要一个 转发失败 将不再 执行后续转发 并且 从新开始消费
					（不建议 指定多个 Topic 转发）

	总结：
			对阿里MQ 进行包装 是想通过 改MQ 来实现一套 动态管理消费端，轻易可拓展 ，轻易可使用的。
			并且很容易就可以 分布式使用的一套 微处理程序 也可以看成是 一套微服务系统 。


  问题备注：

			如 HTML 页面 报错 跨域问题， 请删除  maven 库  spring 4.2 版本 重新获取

	PS:

			随着项目越来越完善，感觉应该出个 使用指南之类的文档了。本来很喜欢 springfox 但是 这次写的项目并不是 restful API

			等继续完善后，应该会出个文档。 项目 代码并不复查，  同时也劲量的减少配置 方便使用和二次开发了。
