##　AB压力测试

- 示例 （开启KEEP ALIVE可以提高性能：HTTP建立连接是特别消耗资源）

```
ab -k -n 10000000 -c 300
HTTP DEMO--byDisheng
23万/秒
ab -k -n 10000000 -c 300 http://127.0.0.1:8081/
===================================================
Server Software:        
Server Hostname:        127.0.0.1
Server Port:            8081

Document Path:          /
Document Length:        0 bytes

Concurrency Level:      300
Time taken for tests:   42.738 seconds
Complete requests:      10000000
Failed requests:        0
Write errors:           0
Keep-Alive requests:    10000000
Total transferred:      1030000000 bytes
HTML transferred:       0 bytes
Requests per second:    233982.86 [#/sec] (mean)
Time per request:       1.282 [ms] (mean)
Time per request:       0.004 [ms] (mean, across all concurrent requests)
Transfer rate:          23535.39 [Kbytes/sec] received

```