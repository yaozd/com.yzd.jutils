## 问题一:解决redis连接错误：MISCONF Redis is configured to save RDB snapshots, but it is currently not able to persi
- redis.clients.jedis.exceptions.JedisException: Could not get a resource from the pool
- [解决redis连接错误：MISCONF Redis is configured to save RDB snapshots, but it is currently not able to persi](https://blog.csdn.net/wuhounuanyangzhao/article/details/79669821)
```
 命令行修改方式示例： 127.0.0.1:6379> config set stop-writes-on-bgsave-error no 
 
缓解方案（不能根本解决问题）： 
3.1 修改redis.conf文件中配置项stop-writes-on-bgsave-error no （默认值为yes），即当bgsave快照操作出错时停止写数据到磁盘，这样后面写错做均会失败，为了不影响后续写操作，故需将该项值改为no 
```

##　Redis 2种持久化模式的缺陷 (特别推荐参考-byArvin)
－[Redis 2种持久化模式的缺陷](https://blog.csdn.net/hexieshangwang/article/details/47254087)
```
1.问题描述： 
并发200路，模拟不断写Redis，持续4小时后，接口调用开始出现大量失败，错误信息如下：
二、AOF持久化模式缺陷

1.问题1描述： 
Redis主从节点均开启AOF模式，并发200路，模拟不断写Redis，持续15分钟后，接口调用开始出现大量失败，且Redis所在的Linux虚拟服务器挂起。
```

