# callback-回调系统设计

## 思路
```
1.通过GRPC进行数据推送，hazelcast 维持连接关系，netty 作为对外输出接口，形成一个内循环。
  其中可能会用到服务发现，可以使用k8s的API或nacos等
2.RocketMQ做为中间节点，承上启下，通过ACK确保消息通知，ACK设置重试次数，默认为16次
3.Callback使用HTTP1的KEEPALIVE实现即可
```