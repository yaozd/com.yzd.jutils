### ZUUL的技术问题
- [Spring Cloud限流思路及解决方案](https://www.cnblogs.com/xifenglou/p/8519700.html)

转自： http://blog.csdn.net/zl1zl2zl3/article/details/78683855

在高并发的应用中，限流往往是一个绕不开的话题。本文详细探讨在Spring Cloud中如何实现限流。

在 Zuul 上实现限流是个不错的选择，只需要编写一个过滤器就可以了，关键在于如何实现限流的算法。常见的限流算法有漏桶算法以及令牌桶算法。这个可参考 https://www.cnblogs.com/LBSer/p/4083131.html ，写得通俗易懂，你值得拥有，我就不拽文了。

GoogleGuava 为我们提供了限流工具类 RateLimiter ，于是乎，我们可以撸代码了。

代码示例
@Component

public class RateLimitZuulFilter extends ZuulFilter {

 

   private final RateLimiter rateLimiter = RateLimiter.create(1000.0);

 

   @Override

   public String filterType() {

       return FilterConstants.PRE_TYPE;

   }

 

   @Override

   public int filterOrder() {

       return Ordered.HIGHEST_PRECEDENCE;

   }

 

   @Override

   public boolean shouldFilter() {

       // 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。

       return true;

   }

 

   @Override

   public Object run() {

       try {

           RequestContext currentContext = RequestContext.getCurrentContext();

           HttpServletResponse response = currentContext.getResponse();

           if (!rateLimiter.tryAcquire()) {

               HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;

 

               response.setContentType(MediaType.TEXT_PLAIN_VALUE);

               response.setStatus(httpStatus.value());

               response.getWriter().append(httpStatus.getReasonPhrase());

 

               currentContext.setSendZuulResponse(false);

 

               throw new ZuulException(

                       httpStatus.getReasonPhrase(),

                       httpStatus.value(),

                       httpStatus.getReasonPhrase()

               );

           }

       } catch (Exception e) {

           ReflectionUtils.rethrowRuntimeException(e);

       }

       return null;

   }

}

如上，我们编写了一个 pre 类型的过滤器。对Zuul过滤器有疑问的可参考我的博客：

 

Spring Cloud内置的Zuul过滤器详解：http://www.itmuch.com/spring-cloud/zuul/zuul-filter-in-spring-cloud


 

Spring Cloud Zuul过滤器详解：http://www.itmuch.com/spring-cloud/zuul/spring-cloud-zuul-filter


在过滤器中，我们使用 GuavaRateLimiter 实现限流，如果已经达到最大流量，就抛异常。

分布式场景下的限流
以上单节点Zuul下的限流，但在生产中，我们往往会有多个Zuul实例。对于这种场景如何限流呢？我们可以借助Redis实现限流。

使用redis实现，存储两个key，一个用于计时，一个用于计数。请求每调用一次，计数器增加1，若在计时器时间内计数器未超过阈值，则可以处理任务

if(!cacheDao.hasKey(TIME_KEY)) {

   cacheDao.putToValue(TIME_KEY, 0, 1, TimeUnit.SECONDS);

}      

if(cacheDao.hasKey(TIME_KEY) && cacheDao.incrBy(COUNTER_KEY, 1) > 400) {

   // 抛个异常什么的

}

实现微服务级别的限流
一些场景下，我们可能还需要实现微服务粒度的限流。此时可以有两种方案：

方式一：在微服务本身实现限流。

和在Zuul上实现限流类似，只需编写一个过滤器或者拦截器即可，比较简单，不作赘述。个人不太喜欢这种方式，因为每个微服务都得编码，感觉成本很高啊。

加班那么多，作为程序猿的我们，应该学会偷懒，这样才可能有时间孝顺父母、抱老婆、逗儿子、遛狗养鸟、聊天打屁、追求人生信仰。好了不扯淡了，看方法二吧。

方法二：在Zuul上实现微服务粒度的限流。

在讲解之前，我们不妨模拟两个路由规则，两种路由规则分别代表Zuul的两种路由方式。

zuul:

 routes:

   microservice-provider-user: /user/**

   user2:

     url: http://localhost:8000/

     path: /user2/**

如配置所示，在这里，我们定义了两个路由规则， microservice-provider-user 以及 user2 ，其中 microservice-provider-user 这个路由规则使用到Ribbon + Hystrix，走的是 RibbonRoutingFilter ；而 user2 这个路由用不上Ribbon也用不上Hystrix，走的是 SipleRoutingFilter 。如果你搞不清楚这点，请参阅我的博客：

Spring Cloud内置的Zuul过滤器详解：http://www.itmuch.com/spring-cloud/zuul/zuul-filter-in-spring-cloud

Spring Cloud Zuul过滤器详解：http://www.itmuch.com/spring-cloud/zuul/spring-cloud-zuul-filter

搞清楚这点之后，我们就可以撸代码了：

@Component

public class RateLimitZuulFilter extends ZuulFilter {

 

   private Map<String, RateLimiter> map = Maps.newConcurrentMap();

 

   @Override

   public String filterType() {

       return FilterConstants.PRE_TYPE;

   }

 

   @Override

   public int filterOrder() {

       // 这边的order一定要大于org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter的order

       // 也就是要大于5

       // 否则，RequestContext.getCurrentContext()里拿不到serviceId等数据。

       return Ordered.LOWEST_PRECEDENCE;

   }

 

   @Override

   public boolean shouldFilter() {

       // 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。

       return true;

   }

 

   @Override

   public Object run() {

       try {

           RequestContext context = RequestContext.getCurrentContext();

           HttpServletResponse response = context.getResponse();

 

           String key = null;

           // 对于service格式的路由，走RibbonRoutingFilter

           String serviceId = (String) context.get(SERVICE_ID_KEY);

           if (serviceId != null) {

               key = serviceId;

               map.putIfAbsent(serviceId, RateLimiter.create(1000.0));

           }

           // 如果压根不走RibbonRoutingFilter，则认为是URL格式的路由

           else {

               // 对于URL格式的路由，走SimpleHostRoutingFilter

               URL routeHost = context.getRouteHost();

               if (routeHost != null) {

                   String url = routeHost.toString();

                   key = url;

                   map.putIfAbsent(url, RateLimiter.create(2000.0));

               }

           }

           RateLimiter rateLimiter = map.get(key);

           if (!rateLimiter.tryAcquire()) {

               HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;

 

               response.setContentType(MediaType.TEXT_PLAIN_VALUE);

               response.setStatus(httpStatus.value());

               response.getWriter().append(httpStatus.getReasonPhrase());

 

               context.setSendZuulResponse(false);

 

               throw new ZuulException(

                       httpStatus.getReasonPhrase(),

                       httpStatus.value(),

                       httpStatus.getReasonPhrase()

               );

           }

       } catch (Exception e) {

           ReflectionUtils.rethrowRuntimeException(e);

       }

       return null;

   }

}

简单讲解一下这段代码：

对于 microservice-provider-user 这个路由，我们可以用 context.get(SERVICE_ID_KEY); 获取到serviceId，获取出来就是 microservice-provider-user；

而对于 user2 这个路由，我们使用 context.get(SERVICE_ID_KEY); 获得是null，但是呢，可以用 context.getRouteHost() 获得路由到的地址，获取出来就是 http://localhost:8000/ 。接下来的事情，你们懂的。

改进与提升
实际项目中，除以上实现的限流方式，还可能会：

一、在上文的基础上，增加配置项，控制每个路由的限流指标，并实现动态刷新，从而实现更加灵活的管理

二、基于CPU、内存、数据库等压力限流（感谢平安常浩智）提出。。

下面，笔者借助Spring Boot Actuator提供的 Metrics 能力进行实现基于内存压力的限流——当可用内存低于某个阈值就开启限流，否则不开启限流。

@Component

public class RateLimitZuulFilter extends ZuulFilter {

   @Autowired

   private SystemPublicMetrics systemPublicMetrics;

   @Override

   public boolean shouldFilter() {

       // 这里可以考虑弄个限流开启的开关，开启限流返回true，关闭限流返回false，你懂的。

       Collection<Metric<?>> metrics = systemPublicMetrics.metrics();

       Optional<Metric<?>> freeMemoryMetric = metrics.stream()

               .filter(t -> "mem.free".equals(t.getName()))

               .findFirst();

       // 如果不存在这个指标，稳妥起见，返回true，开启限流

       if (!freeMemoryMetric.isPresent()) {

           return true;

       }

       long freeMemory = freeMemoryMetric.get()

               .getValue()

               .longValue();

       // 如果可用内存小于1000000KB，开启流控

       return freeMemory < 1000000L;

   }

   // 省略其他方法

}

三、实现不同维度的限流，例如：

对请求的目标URL进行限流（例如：某个URL每分钟只允许调用多少次）

对客户端的访问IP进行限流（例如：某个IP每分钟只允许请求多少次）

对某些特定用户或者用户组进行限流（例如：非VIP用户限制每分钟只允许调用100次某个API等）

多维度混合的限流。此时，就需要实现一些限流规则的编排机制。与、或、非等关系。

参考文档
分布式环境下限流方案的实现：http://blog.csdn.net/Justnow_/article/details/53055299