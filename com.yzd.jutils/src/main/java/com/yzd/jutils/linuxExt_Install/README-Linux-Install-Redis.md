## Redis
- [Linux下最新版Redis5.0.5的详细安装及配置过程](https://blog.csdn.net/qq_36737803/article/details/90578860)
- [【redis】1.redis-windows安装+配置介绍](https://www.cnblogs.com/sxdcgaq8080/p/7204878.html) -参考中文说明
```
yum -y install gcc-c++
//
tar -xf redis-5.0.7.tar.gz 
cd redis-5.0.7/
make
cd src/
make install
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
//============================================================================
vim redis.conf 
PS:修改主配置文件 https://blog.csdn.net/qq_36737803/article/details/90578860
//
注释掉 bind 127.0.0.1 这一行（解决只能特定网段连接的限制）
#bind 127.0.0.1
//将 protected-mode 属性改为 no （关闭保护模式，不然会阻止远程访问）
protected-mode no
//将 daemonize 属性改为 yes （这样启动时就在后台启动）
daemonize yes
//配置dump文件保存路径
dir /usr/local/redis
//============================================================================
//启动redis
cd ..
./bin/redis-server /usr/local/redis/etc/redis.conf 

/usr/local/redis/bin/redis-server /usr/local/redis/etc/redis.conf

```

- redis配置开机启动
    - [redis linux下的开机启动](https://www.cnblogs.com/zsg88/p/8323475.html)
    ```
    1.在redis/utils找到redis_init_script 将它拷贝到  /etc/init.d 目录并重命名为redis
    cd /etc/init.d
    cp /usr/local/programs/redis4/utils/redis_init_script  redis
    //
    2.编辑redis脚本
    //
    6.启动 Redis 服务
    # service redis start
    7.停止 Redis 服务
    # service redis stop
    ```