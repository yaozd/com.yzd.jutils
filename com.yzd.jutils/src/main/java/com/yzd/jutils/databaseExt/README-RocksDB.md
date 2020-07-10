# rocksDB
- [Java RocksDB的详细介绍](https://blog.csdn.net/qy20115549/article/details/89393512)
```
RocksDB简介
RocksDB是 Facebook 开发的一款高效的数据库软件，其采用C++编写。其是一款key-value型数据存储软件，其具有四大特点。
**高性能：**RocksDB使用一套日志结构的数据库引擎，为了更好的性能，这套引擎是用C++编写的。 Key和value是任意大小的字节流。

```
## 使用场景：
- 应用数据缓存,如：raft
- 像MyRocks这样的数据存储引擎
- 一些嵌入式工作量