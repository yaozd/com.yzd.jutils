# 微服务概念与区别

## [微服务架构的理论基础 - 康威定律](https://blog.csdn.net/weixin_33814685/article/details/90681732) 第一参考byArvin
```
第一定律

Communication dictates design
组织沟通方式会通过系统设计表达出来
第二定律

There is never enough time to do something right, but there is always enough time to do it over
时间再多一件事情也不可能做的完美，但总有时间做完一件事情
第三定律

There is a homomorphism from the linear graph of a system to the linear graph of its design organization
线型系统和线型组织架构间有潜在的异质同态特性
第四定律

The structures of large systems tend to disintegrate during development, qualitatively more so than with small systems
大的系统组织总是比小系统更倾向于分解
```
- [墨菲定律（设计系统）和康威定律（系统划分）](https://blog.csdn.net/mytobaby00/article/details/79840927)
```
在设计系统时，应该多考虑 墨菲定律：

任何事物都没有表面看起来那么简单。
所有的事都会比你预计的时间长。
可能出错的事总会出错。
如果你担心某种情况发生，那么他就更有可能发生。
在划分系统时，应该多考虑 康威定律：

系统架构是公司组织架构的反映。
应该按照业务闭环进行系统拆分／组织架构划分，实现闭环／高内聚／低耦合，减少沟通成本。
如果沟通出现问题，那么应该考虑进行系统和组织架构的调整。
在合适时机进行系统拆分，不要一开始就把系统／服务拆的非常细，虽然闭环，但是每个人维护的系统多，维护成本高。
```
- [架构的方法论之康威定律](https://blog.csdn.net/guotufu/article/details/80287768)
```
Organizations which design systems are constrained to produce designs which are copies of the communication structures of these organizations. - Melvin Conway(1967)

大概翻译下就是设计系统的组织，其产生的设计和架构等价于组织间的沟通结构。

康威定律被视为微服务架构的理论基础，是有一定的根据的，主要有以下几点：

1.把一个大的系统分成一个个小的业务模块，每个业务模块由对应的小团队来负责而且各个小团队都是独立的，所以在分模块时要按业务而不是技术来划分。

2.避免过度设计。一个系统初级是不可能大而全，十分完美，它必须是有一个演进进程，所以刚开始能保持可移植性、高扩展性即可。保持弹性设计。

3.各微服务应该都有自己独立的数据库和资源，避免耦合在一起。

4.各微服务对外提供的接口尽量的兼容各种不同的技术和开发语言。

5.专注产品的生命力，而不是为了项目而技术。所以技术人员也有必要对业务有一定的理解。
```

## [微服务架构实践](https://myslide.cn/slides/21430?vertical=1) 内容大而全：第二参考byArvin

## [什么是微服务](https://www.sohu.com/a/130215781_609518)
- [融数数据基于DevOps的微服务架构演进之路](https://www.sohu.com/a/130215781_609518)
```
微服务：高度封装，松耦合，独立部署，独立扩展。
微服务应该尽量避免对通用组件或者基础构建块的依赖，例如数据库；
Bounded context来源于DDD，其提供了一种将大型架构拆分成具体feature的方法，真正的微服务希望团队无需过多地了解其他微服务团队的业务知识。
```
- [微服务架构强调敏捷、独立开发、独立部署、独立扩展，“小”不是微服务的判断标准](https://www.sohu.com/a/130215781_609518)
```
一个服务只实现一个独立的Feature

尽量不要为外部应用发布代码级API，依赖通过服务调用或者事件搞定

服务之间最好通过异步事件交互 每个服务拥有自己独立的数据
```
## [微服务与SOA的对比](https://www.sohu.com/a/130215781_609518)