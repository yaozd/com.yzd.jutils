
##　OpenTracing的基本元素-三个概念
```
标记包含以下三个概念:
traceId
一次请求全局只有一个traceId。用来在海量的请求中找到同一链路的几次请求。比如servlet服务器接收到用户请求，调用dubbo服务，然后将结果返回给用户，整条链路只有一个traceId。开始于用户请求，结束于用户收到结果。
spanId
一个链路中每次请求都会有一个spanId。例如一次rpc，一次sql都会有一个单独的spanId从属于traceId。
parentId
上一次请求的spanId。用于将一条链路的多次请求串联起来。

```
## Zipkin示例-01
- 这条跟踪信息对应的代码片段为
``` java
Span twoPhase = tracer.newTrace().name("twoPhase").start();
try {
    Span prepare = tracer.newChild(twoPhase.context()).name("prepare").start();
    try {
	prepare();
    } finally {
	prepare.finish();
    }
    Span commit = tracer.newChild(twoPhase.context()).name("commit").start();
    try {
	commit();
    } finally {
	commit.finish();
    }
} finally {
    twoPhase.finish();
}
```
- 这段代码实际上向Zipkin上报的数据为
```
[
  {
    "traceId": "89e051d5394b90b1",
    "id": "89e051d5394b90b1",
    "name": "twophase",
    "timestamp": 1510043591038983,
    "duration": 1000356,
    "binaryAnnotations": [
      {
        "key": "lc",
        "value": "",
        "endpoint": {
          "serviceName": "tracer-demo",
          "ipv4": "192.168.99.1"
        }
      }
    ]
  },
  {
    "traceId": "89e051d5394b90b1",
    "id": "60568c4903793b8d",
    "name": "prepare",
    "parentId": "89e051d5394b90b1",
    "timestamp": 1510043591039919,
    "duration": 499246,
    "binaryAnnotations": [
      {
        "key": "lc",
        "value": "",
        "endpoint": {
          "serviceName": "tracer-demo",
          "ipv4": "192.168.99.1"
        }
      }
    ]
  },
  {
    "traceId": "89e051d5394b90b1",
    "id": "ce14448169d01d2f",
    "name": "commit",
    "parentId": "89e051d5394b90b1",
    "timestamp": 1510043591539304,
    "duration": 499943,
    "binaryAnnotations": [
      {
        "key": "lc",
        "value": "",
        "endpoint": {
          "serviceName": "tracer-demo",
          "ipv4": "192.168.99.1"
        }
      }
    ]
  }
]
```　