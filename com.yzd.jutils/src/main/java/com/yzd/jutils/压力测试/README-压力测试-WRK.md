##　wrk压力测试
- [性能测试工具 wrk 使用教程](https://www.cnblogs.com/quanxiaoha/p/10661650.html)
- 示例 （开启KEEP ALIVE可以提高性能：HTTP建立连接是特别消耗资源）

```
wrk -c 300 -d 60s -t 16 http://172.20.60.45:8081/
HTTP DEMO--byDisheng
30多万/秒
wrk -c 300 -d 60s -t 16 http://172.20.60.45:8081/
===================================================

```