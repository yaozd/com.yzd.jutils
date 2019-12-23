## Redis
- [Linux下最新版Redis5.0.5的详细安装及配置过程](https://blog.csdn.net/qq_36737803/article/details/90578860)
- [【redis】1.redis-windows安装+配置介绍](https://www.cnblogs.com/sxdcgaq8080/p/7204878.html) -参考中文说明
```
yum -y insta gcc-c++
//
tar -xf redis-5.0.7.tar.gz 
cd redis-5.0.7/
make
cd src/
make insta
//
cd /usr/local/
mkdir redis
cd redis/
mkdir bin
mkdir etc
//
cd /data/package/redis-5.0.7/
cp redis.conf /usr/local/redis/etc/
cd src/
mv mkreleasehdr.sh redis-benchmark redis-check-aof redis-check-rdb redis-cli redis-sentinel  redis-server redis-trib.rb /usr/local/redis/bin/
//
cd /usr/local/redis/etc/
vim redis.conf 
cd ..
./bin/redis-server /usr/local/redis/etc/redis.conf 

```