## DDOS攻击器
- [用JAVA 开发的DDOS攻击器](http://www.mamicode.com/info-detail-146149.html) -属于CC攻击，并非是DDOS攻击
- [高性能流量生成工具trafgen(DDoS模拟)](https://blog.csdn.net/wuzhimang/article/details/54581117)
```
工具选择
开源的流量生成工具很多，可用于模拟DoS攻击的工具也不在少数，如hping、scapy（python库）等等，但均存在着不足，如性能不够，不能模拟DDoS攻击（攻击流IP和PORT不能动态变化）
通过详细的对比测试（同hping、scapy、LOIC等对比测试，具体数据不宜公开），最后统一选择了netsniff-ng套件中的trafgen攻击，其在测试环境中可达到24万pps的SYNFLOOD攻击，是一款高速、多线程网络数据包生成工具
trafgen 工具能够动态生成攻击IP和端口号，能够通过配置文件动态修改攻击包的内容

```