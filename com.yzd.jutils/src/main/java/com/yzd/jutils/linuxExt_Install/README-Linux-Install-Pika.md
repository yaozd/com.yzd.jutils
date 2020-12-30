# pika

## install protoc
- 参考：
    - [linux 安装protoc](https://blog.csdn.net/weixin_42547619/article/details/108137459)
    - [linux环境下安装protobuf详解（转载）](https://blog.csdn.net/bodybo/article/details/79036593)
- 安装步骤
```
下载：
linux
链接: https://pan.baidu.com/s/1Du6OFFp_A6g6mgeHUMWqKg 提取码: atvy
1.
yum -y install autoconf automake libtool curl make g++ unzip
unzip protobuf-master.zip
cd protobuf-master
./autogen.sh 
## 修改安装目录通过 ./configure --prefix=命令,统一安装在/usr/local/protobuf下
./configure --prefix=/usr/local/protobuf
make
make check
make install
## refresh shared library cache.
ldconfig 
#检查是否安装成功
protoc --version
2.
//修改环境变量
vim /etc/profile
# 添加：
export PATH=$PATH:/usr/local/protobuf/bin/
export PKG_CONFIG_PATH=/usr/local/protobuf/lib/pkgconfig/
# 保存并执行
source /etc/profile
# 动态库
vim /etc/ld.so.conf
#新起一行 添加
/usr/local/protobuf/lib
#保存退出后执行
#检查是否安装成功
protoc --version
```
## install pika
- 直接使用二进制包
```
https://github.com/Qihoo360/pika/releases
pika-linux-x86_64-v3.3.6.tar.bz2
//
1.
/bin/pika -c ./conf/pika.conf
2.
nohup ./bin/pika -c ./conf/pika.conf >> output-pika.log 2>&1 &
```