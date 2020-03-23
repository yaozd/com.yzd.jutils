## opentracing-API
- [opentracing/opentracing-java](https://github.com/opentracing/opentracing-java)
- [OpenTracing的使用实例（Java）](https://developer.aliyun.com/article/746760)
```
参考：
https://developer.aliyun.com/article/746760
//
构件组织
OpenTracing API的Java构件如下：

opentracing-api：主要的API，无其他依赖。
opentracing-noop：为主要API提供无意义实现（NoopTracer），依赖于opentracing-api。
opentracing-util：工具类，例如GlobalTracer和默认的基于ThreadLocal存储的ScopeManager实现，依赖于上面所有的构件。
opentracing-mock：用于测试的mock层。包含MockTracer，简单的将Span存储在内存中，依赖于opentracing-api和opentracing-noop。
```
## opentracing-netty
- [https://github.com/dougEfresh/opentracing-netty](https://github.com/dougEfresh/opentracing-netty)

## OpenTracing-brave
- [https://github.com/openzipkin/brave](https://github.com/openzipkin/brave)
- []()
- []()