# Nginx-官网
- [http://nginx.org/en/docs/](http://nginx.org/en/docs/)
# Nginx-
- [http://localhost:8080/](http://localhost:8080/html/vue-css.html)

### Nginx-Window启动、关闭服务命令

- Window平台要在Dos下运行，不要在powershell下运行。
- 执行目录不要有中文

```

pwd
cd /D pwd
查看任务进程是否存在，dos或打开任务管理器都行-同时也可查看内存
tasklist /fi "imagename eq nginx.exe"
---|---
启动nginx | start nginx
修改配置后重新加载生效| nginx -s reload
重新打开日志文件| nginx -s reopen
测试nginx配置文件是否正确| nnginx -t -c nginx.conf
关闭nginx ：快速停止nginx | nginx -s stop
完整有序的停止nginx |nginx -s quit

```
### Nginx-Config示例

```
#user  nobody;
worker_processes  1;


events{
    worker_connections 1024;
    }
http{
    upstream myproject {
        #server 127.0.0.1:8888;
        server 127.0.0.1:1222;
        
    }
    server {
        listen 8080;
        server_name localhost;
        
        location / {
            proxy_pass http://myproject;
            }
        }
}
```

### Nginx-Window安装

- [Nginx Windows详细安装部署教程](https://www.cnblogs.com/taiyonghai/p/9402734.html)
- [windows 下配置 Nginx 常见问题](https://www.cnblogs.com/fengh/p/4225909.html)

```
三、常见错误

如果启动失败 可以看下logs目录下 error.log 文件里的错误信息。

我在第一次安装的时遇到两个错误，也是最容易碰到的问题，在这里列出来方便大家碰到相同的问题时快速解决。

1.  端口占用问题

 我的配置文件里服务侦听的是 80 端口，由于机器上部署了IIS，80端口被默认站点占用，把站点关闭就可以了，这个问题在错误日志里记录是这样的。

 

2015/01/15 10:44:12 [emerg] 8800#5988: bind() to 0.0.0.0:80 failed (10013: An attempt was made to access a socket in a way forbidden by its access permissions)
 

碰到类似的错误，请确认端口是否被占用或被防火墙屏蔽

 

2.Nginx所在目录有中文

错误日志大致输出一下内容

2015/01/15 11:55:55 [emerg] 5664#8528: CreateFile() "E:\软件\nginx-1.7.8/conf/nginx.conf" failed (1113: No mapping for the Unicode character exists in the target multi-byte code page)
3. 启用缓存时报错

2015/01/15 17:26:50 [emerg] 17068#20356: shared zone "cache_one" has no equal addresses: 02CF0000 vs 02A20000
2015/01/15 17:26:50 [alert] 11536#11228: worker process 17068 exited with code 1
我一直没有找到解决的方法，有人说重启服务，或者缓存设置大一点就可以了，我试了一下没有用的，官网 原文是这样讲的，只能认为windwos下无解了。

: The cache and other modules which require shared memory support do 
: not work in Windows Vista and later due to address space layout 
: randomization being enabled in these Windows versions.
```


> [Nginx URL重写（rewrite）配置及信息详解](https://www.cnblogs.com/czlun/articles/7010604.html)
```
rewrite ^/(.*) http://www.czlun.com/$1 permanent;
```