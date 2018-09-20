#### 灰度发布(Gated Launch/Gray Release)
- [灰度发布(Gated Launch/Gray Release)](http://www.cnblogs.com/yjmyzz/p/spring-cloud-zuul-demo.html#)

```
4.3 灰度发布(Gated Launch/Gray Release)　

大型分布式系统中，灰度发布是保证线上系统安全生产的重要手段，一般的做法为：从集群中指定一台（或某几台）机器，每次做新版本发布前，先只发布这些机器上，先观察一下是否正常，如果稳定运行后，再发布到其它机器。这种策略（相当于按部分节点来灰度），大多数情况下可以满足要求，但是有一些特定场景，可能不太适用。

比如：笔者所在的“美味不用等”公司，主要B端用户为各餐饮品牌的商家，多数情况下，如果新上了一个功能，希望找一些规模较小的餐厅做试点，先看看上线后的运行情况，如果运行良好，再推广到其它商家。

再比如：后端服务有N多个版本在同时运行，比如V1、V2，现在新加了一个V3版本（这在手机app应用中很常见），希望只有部分升级了app的用户访问最新的V3版本服务，其它用户仍然访问旧版本，待系统稳定后，再大规模提示用户升级。

对于这些看上去需求各异的灰度需求，其实本质是一样的：将请求（根据参数内容+业务规则），将其转向到特定的灰度机器上。Spring Cloud MicroService中有一个metadata-map(元数据）设置，可以很好的满足这类需求。

首先要引入一个jar包：(这是github上开源的一个项目ribbon-discovery-filter-spring-cloud-starter）

1
compile 'io.jmnarloch:ribbon-discovery-filter-spring-cloud-starter:2.1.0'
示例如下：

在各个服务的application.yml中设置以下metadata-map

eureka:
  instance:
    metadata-map:
      gated-launch: false
即：所有节点发布后，默认灰度模式为false。然后把特定的灰度机器上的配置，该参数改成true（表明这台机器是用于灰度验证的）。

然后在ZuulFilter中参考下面的代码：


@Override
public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
 
    Object token = request.getParameter("token");
 
    //校验token
    if (token == null) {
        logger.info("token为空，禁止访问!");
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        return null;
    } else {
        //TODO 根据token获取相应的登录信息，进行校验（略）
 
        //灰度示例
        RibbonFilterContextHolder.clearCurrentContext();
        if (token.equals("1234567890")) {
            RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "true");
        } else {
            RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "false");
        }
    }
 
    //添加Basic Auth认证信息
    ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));
 
    return null;
}
注意18-23行，这里演示了通过特定的token参数值，将请求引导到gated-lanuch=true的机器上。（注：参考这个原理，大家可以把参数值，换成自己的version-版本号，shopId-商家Id之类)。只要请求参数中的token=1234567890，这次请求就会转发到灰度节点上。

如果有朋友好奇这是怎么做到的，可以看下io.jmnarloch.spring.cloud.ribbon.predicate.MetadataAwarePredicate 这个类：


@Override
protected boolean apply(DiscoveryEnabledServer server) {
 
    final RibbonFilterContext context = RibbonFilterContextHolder.getCurrentContext();
    final Set<Map.Entry<String, String>> attributes = Collections.unmodifiableSet(context.getAttributes().entrySet());
    final Map<String, String> metadata = server.getInstanceInfo().getMetadata();
    return metadata.entrySet().containsAll(attributes);
}　　
大致原理就是拿上下文中，开发人员设置的属性 与 服务节点里的metadata-map 进行比较，如果metadata-map中包括开发人员设置的属性，就返回成功（即：选择这台服务器）
```