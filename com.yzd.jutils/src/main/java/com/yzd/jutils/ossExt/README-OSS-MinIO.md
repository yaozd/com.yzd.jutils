# MinIO 对象存储服务

## MinIO部署快速入门
- [MinIO部署快速入门](https://docs.min.io/cn/)
```
微软Windows系统
1.
下载：
https://dl.min.io/server/minio/release/windows-amd64/minio.exe
2.
启动：
 .\minio.exe server D:\S-Software\miniio\data

 .\minio.exe server --address 0.0.0.0:60035 D:\S-Software\miniio\data
```

## 常见问题参考：
- [liunx环境修改minio默认端口和后台启动](https://www.cnblogs.com/haha66/p/13343426.html)
```
./minio server start --address 0.0.0.0:自定义端口号
#设置任何ip和自定义端口
nohup /usr/local/minio server --address 0.0.0.0:60035 /home/minio/data > /home/minio/data/minio.log 2>&1 &
```
- [MinIO Server config.json (v18) 指南]() 
```
配置目录
默认的配置目录是 ${HOME}/.minio，你可以使用--config-dir命令行选项重写之。MinIO server在首次启动时会生成一个新的config.json，里面带有自动生成的访问凭据。
Copyminio server --config-dir /etc/minio /data
2.
调整登录密码
    1.在config.json中可以调整登录密码
    2.通过环境变量的方式调整：
    export MINIO_ACCESS_KEY=admin
    export MINIO_SECRET_KEY=password
```

## 分布式部署参考
- [Minio 搭建对象存储服务](https://blog.csdn.net/m0_37263637/article/details/107787466)
```
作为Linux Service启动
https://github.com/minio/minio-service/tree/master/linux-systemd
1.
将下载好的minio程序软连接到 usr/local/bin 路径下，可以在全局访问到minio程序
sudo ln minio /usr/local/bin/minio
2.
创建 /etc/default/minio 配置
sudo vi /etc/default/minio
3.
/etc/default/minio

# Volume to be used for MinIO server.
MINIO_VOLUMES="/home/ubuntu/storage"
# Use if you want to run MinIO on a custom port.
MINIO_OPTS="--address :80"
# Access Key of the server.
MINIO_ACCESS_KEY="minioubuntu"
# Secret key of the server.
MINIO_SECRET_KEY="minioubuntu"
PS:
配置存储路径
配置运行ip及端口
配置minio AccessKey和SecretKey
```

## 使用winsw工具能够将部分exe 创建成服务
- [使用winsw工具能够将部分exe 创建成服务](https://www.cnblogs.com/jinanxiaolaohu/p/9695761.html)
- [Running MinIO as a service on Windows](https://github.com/minio/minio-service/tree/master/windows#manual-install-with-envs)
```
Manual install with ENVs
winsw is a wrapper to run any executable as an Windows service

Download WinSW.NET4.exe
Rename the WinSW.NET4.exe to minio-service.exe
Create a xml file minio-service.xml insert the configuration below
Open a cmd as Administrator and execute minio-service.exe install
<service>
  <id>MinIO</id>
  <name>MinIO</name>
  <description>MinIO is a high performance object storage server</description>
  <executable>minio.exe</executable>
  <env name="MINIO_ACCESS_KEY" value="minio"/>
  <env name="MINIO_SECRET_KEY" value="minio123"/>
  <arguments>server C:\Temp\minio</arguments>
  <logmode>rotate</logmode>
</service>
```