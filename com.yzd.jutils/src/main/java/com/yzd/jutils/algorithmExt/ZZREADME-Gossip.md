# gossip
- [https://gitee.com/mirrors/jgossip](https://gitee.com/mirrors/jgossip)
- [https://www.oschina.net/p/jgossip](https://www.oschina.net/p/jgossip)
- [Gossip协议](https://blog.csdn.net/moonlight821/article/details/84850934)
  ```
  Gossip的缺陷
  分布式网络中，没有一种完美的解决方案，Gossip协议跟其他协议一样，也有一些不可避免的缺陷，主要是两个：
  
  1）消息的延迟
  
  由于绯闻协议中，节点只会随机向少数几个节点发送消息，消息最终是通过多个轮次的散播而到达全网的，因此使用绯闻协议会造成不可避免的消息延迟。不适合用在对实时性要求较高的场景下。
  
  2）消息冗余
  
  闲话协议规定，节点会定期随机选择周围节点发送消息，而收到消息的节点也会重复该步骤，因此就不可避免的存在消息重复发送给同一节点的情况，造成了消息的冗余，同时也增加了收到消息的节点的处理压力。而且，由于是定期发送，因此，即使收到了消息的节点还会反复收到重复消息，加重了消息的冗余。
  ```
- []()