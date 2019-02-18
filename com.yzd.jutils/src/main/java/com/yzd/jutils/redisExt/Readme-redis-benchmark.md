## [Redis性能测试——redis-benchmark使用教程](https://blog.csdn.net/yangcs2009/article/details/50781530)
```
不同云主机和物理机器上的基准测试结果
这些测试模拟了 50 客户端和 200w 请求。
使用了 Redis 2.6.14。
使用了 loopback 网卡。
key 的范围是 100 w。
同时测试了 有 pipelining 和没有的情况（16 条命令使用 pipelining）。
Intel(R) Xeon(R) CPU E5520 @ 2.27GHz (with pipelining)

$ ./redis-benchmark -r 1000000 -n 2000000 -t get,set,lpush,lpop -P 16 -q
SET: 552028.75 requests per second
GET: 707463.75 requests per second
LPUSH: 767459.75 requests per second
LPOP: 770119.38 requests per second

Intel(R) Xeon(R) CPU E5520 @ 2.27GHz (without pipelining)

$ ./redis-benchmark -r 1000000 -n 2000000 -t get,set,lpush,lpop -q
SET: 122556.53 requests per second
GET: 123601.76 requests per second
LPUSH: 136752.14 requests per second
LPOP: 132424.03 requests per second

Linode 2048 instance (with pipelining)

$ ./redis-benchmark -r 1000000 -n 2000000 -t get,set,lpush,lpop -q -P 16
SET: 195503.42 requests per second
GET: 250187.64 requests per second
LPUSH: 230547.55 requests per second
LPOP: 250815.16 requests per second

Linode 2048 instance (without pipelining)

$ ./redis-benchmark -r 1000000 -n 2000000 -t get,set,lpush,lpop -q
SET: 35001.75 requests per second
GET: 37481.26 requests per second
LPUSH: 36968.58 requests per second
LPOP: 35186.49 requests per second

更多使用 pipeline 的测试
$ redis-benchmark -n 100000

====== SET ======
  100007 requests completed in 0.88 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

58.50% <= 0 milliseconds
99.17% <= 1 milliseconds
99.58% <= 2 milliseconds
99.85% <= 3 milliseconds
99.90% <= 6 milliseconds
100.00% <= 9 milliseconds
114293.71 requests per second

====== GET ======
  100000 requests completed in 1.23 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

43.12% <= 0 milliseconds
96.82% <= 1 milliseconds
98.62% <= 2 milliseconds
100.00% <= 3 milliseconds
81234.77 requests per second

====== INCR ======
  100018 requests completed in 1.46 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

32.32% <= 0 milliseconds
96.67% <= 1 milliseconds
99.14% <= 2 milliseconds
99.83% <= 3 milliseconds
99.88% <= 4 milliseconds
99.89% <= 5 milliseconds
99.96% <= 9 milliseconds
100.00% <= 18 milliseconds
68458.59 requests per second

====== LPUSH ======
  100004 requests completed in 1.14 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

62.27% <= 0 milliseconds
99.74% <= 1 milliseconds
99.85% <= 2 milliseconds
99.86% <= 3 milliseconds
99.89% <= 5 milliseconds
99.93% <= 7 milliseconds
99.96% <= 9 milliseconds
100.00% <= 22 milliseconds
100.00% <= 208 milliseconds
88109.25 requests per second

====== LPOP ======
  100001 requests completed in 1.39 seconds
  50 parallel clients
  3 bytes payload
  keep alive: 1

54.83% <= 0 milliseconds
97.34% <= 1 milliseconds
99.95% <= 2 milliseconds
99.96% <= 3 milliseconds
99.96% <= 4 milliseconds
100.00% <= 9 milliseconds
100.00% <= 208 milliseconds
71994.96 requests per second

注意：包大小从 256 到 1024 或者 4096 bytes 不会改变结果的量级 （但是到 1024 bytes 后，GETs 操作会变慢）。同样的，50 到 256 客户端的测试结果相同。 10 个客户端时候，吞吐量会变小（译者按：总量到不了最大吞吐量）。

不同机器可以获的不一样的结果，下面是 Intel T5500 1.66 GHz 在 Linux 2.6 下面的结果：

$ ./redis-benchmark -q -n 100000
SET: 53684.38 requests per second
GET: 45497.73 requests per second
INCR: 39370.47 requests per second
LPUSH: 34803.41 requests per second
LPOP: 37367.20 requests per second

另外一个是 64 位 Xeon L5420 2.5 GHz 的结果：

$ ./redis-benchmark -q -n 100000
PING: 111731.84 requests per second
SET: 108114.59 requests per second
GET: 98717.67 requests per second
INCR: 95241.91 requests per second
LPUSH: 104712.05 requests per second
LPOP: 93722.59 requests per second

高性能硬件下面的基准测试
Redis 2.4.2
默认连接数，数据包大小 256 bytes。
Linux 是 SLES10 SP3 2.6.16.60-0.54.5-smp，CPU 是 Intel X5670 @ 2.93 GHz.
固定 CPU，但是使用不同 CPU 内核。
使用 unix domain socket：

$ numactl -C 6 ./redis-benchmark -q -n 100000 -s /tmp/redis.sock -d 256
PING (inline): 200803.22 requests per second
PING: 200803.22 requests per second
MSET (10 keys): 78064.01 requests per second
SET: 198412.69 requests per second
GET: 198019.80 requests per second
INCR: 200400.80 requests per second
LPUSH: 200000.00 requests per second
LPOP: 198019.80 requests per second
SADD: 203665.98 requests per second
SPOP: 200803.22 requests per second
LPUSH (again, in order to bench LRANGE): 200000.00 requests per second
LRANGE (first 100 elements): 42123.00 requests per second
LRANGE (first 300 elements): 15015.02 requests per second
LRANGE (first 450 elements): 10159.50 requests per second
LRANGE (first 600 elements): 7548.31 requests per second

使用 TCP loopback：

$ numactl -C 6 ./redis-benchmark -q -n 100000 -d 256
PING (inline): 145137.88 requests per second
PING: 144717.80 requests per second
MSET (10 keys): 65487.89 requests per second
SET: 142653.36 requests per second
GET: 142450.14 requests per second
INCR: 143061.52 requests per second
LPUSH: 144092.22 requests per second
LPOP: 142247.52 requests per second
SADD: 144717.80 requests per second
SPOP: 143678.17 requests per second
LPUSH (again, in order to bench LRANGE): 143061.52 requests per second
LRANGE (first 100 elements): 29577.05 requests per second
LRANGE (first 300 elements): 10431.88 requests per second
LRANGE (first 450 elements): 7010.66 requests per second
LRANGE (first 600 elements): 5296.61 requests per second



转自 http://www.redis.cn/topics/benchmarks.html

英文版 http://redis.io/topics/benchmarks
```