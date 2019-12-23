## redis-备份还原
- [Redis RDB方式数据备份与恢复](http://www.chenxm.cc/article/552.html)
```
Redis数据备份:
bgsave
====
1、Redis恢复数据:
CONFIG GET dir
PS: CONFIG GET dir 输出的 redis 备份目录
2、停止redis服务:
src/redis-cli -p 6379 shutdown
src是redis安装目录
3、拷贝redis备份文件（dump.rdb）到 /usr/local/redis/bin目录下
4、重新启动redis服务
```