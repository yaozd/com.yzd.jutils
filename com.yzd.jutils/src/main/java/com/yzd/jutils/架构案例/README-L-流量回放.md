# 流量回放及复制工具

## Gor
- [https://github.com/buger/goreplay](https://github.com/buger/goreplay) 官方
- [Gor 简单易用的流量回放及复制工具](https://ld246.com/article/1469103390485)
- [gor实现线上HTTP流量复制压测引流](https://www.cnblogs.com/qmfsun/p/11598763.html)
- []()

## 用法简介
```
简单的 HTTP 流量复制：

gor –input-raw :80 –output-http “http://staging.com”

HTTP 流量复制频率控制：

gor –input-tcp :28020 –output-http “http://staging.com|10″

HTTP 流量复制缩小：

gor –input-raw :80 –output-tcp “replay.local:28020|10%”

HTTP 流量记录到本地文件：

gor –input-raw :80 –output-file requests.gor

HTTP 流量回放和压测：

gor –input-file “requests.gor|200%” –output-http “staging.com”

HTTP 流量过滤复制：

gor –input-raw :8080 –output-http staging.com –output-http-url-regexp ^www.

注入改变请求流量header

gor --input-raw :80 --output-http "http://staging.server"  --output-http-header "User-Agent: Replayed by Gor"   --output-http-header "Enable-Feature-X: true"

作者：niuhaipeng
链接：https://ld246.com/article/1469103390485
来源：链滴
协议：CC BY-SA 4.0 https://creativecommons.org/licenses/by-sa/4.0/
```