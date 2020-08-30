## 开放分布式追踪-OpenTracing
- [开放分布式追踪（OpenTracing）入门与 Jaeger 实现](https://www.jianshu.com/p/0859dac9320c)
```
诞生一系列面向 DevOps 的诊断与分析系统，
包括
集中式日志系统（Logging），集中式度量系统（Metrics）和分布式追踪系统（Tracing）。
===========================
Logging，Metrics 和 Tracing

Logging，Metrics 和 Tracing 有各自专注的部分。

Logging - 用于记录离散的事件。例如，应用程序的调试信息或错误信息。它是我们诊断问题的依据。

Metrics - 用于记录可聚合的数据。例如，队列的当前深度可被定义为一个度量值，在元素入队或出队时被更新；HTTP 请求个数可被定义为一个计数器，新请求到来时进行累加。

Tracing - 用于记录请求范围内的信息。例如，一次远程方法调用的执行过程和耗时。它是我们排查系统性能问题的利器。

```

### opentracing 采样率问题
```
1. 目前不推荐在中间层网关控制采样率。中间层控制控制采样率，会产生许多不完整的trace
2. 采样率控制可以入口网关，或者在DC（数据接收中心）控制采样率
3. DC（数据接收中心）控制采样率，对tracId进行hash,然后再取余，这样可以以traceId完整的丢弃数据
```

### 参考示例
- [zipkin](https://zipkin.io/pages/instrumenting.html)
- [openzipkin/b3-propagation](https://github.com/openzipkin/b3-propagation) -有示例说明-推荐参考byArvin
- []()