### 基于 Upsync 模块实现 Nginx 动态配置-(已验证-byArvin-181108)
-----
```
最终选择方案-新浪微博
nginx-upsync-module
https://github.com/weibocom/nginx-upsync-module
For http protocol. nginx-upsync-module, sync upstreams from consul or etcd and so on, dynamiclly modify backend-servers attributes(weight, max_fails, down...), needn't reload nginx
```
### reload与upsync性能对比

```
架构之微服务设计(Nginx + Upsync)
https://www.cnblogs.com/InCsharp/p/6810106.html
应用案例：
模块已经应用在微博的各类业务中，下面图表对比分析使用模块前后的 QPS 与耗时变化。
从数据可以得出，reload 操作时造成 nginx 的请求处理能力下降约 10%，Nginx 本身的耗时会增长 50%+。若是频繁的扩容缩容，reload 操作造成的开销会更加明显。
```

### CentOS7使用firewalld打开关闭防火墙与端口

```
CentOS7使用firewalld打开关闭防火墙与端口
1、firewalld的基本使用
启动： systemctl start firewalld
关闭： systemctl stop firewalld
查看状态： systemctl status firewalld 
开机禁用  ： systemctl disable firewalld
开机启用  ： systemctl enable firewalld
```

### 相关软下
```
百度云-》开发工具-》N-Nginx-》Nginx-Upsync动态配置
```

### 安装Consul:

- 1.安装Consul
```
yum -y install unzip
1.cd /usr/local/
2.wget https://releases.hashicorp.com/consul/0.7.5/consul_0.7.5_linux_amd64.zip
3.unzip consul_0.7.5_linux_amd64.zip
----
如果解压出现该错误
-bash: unzip: 未找到命令
解决办法
yum -y install unzip
----
执行./consul出现如下内容说明已安装成功
```

- 1.Consul
```
1.  ./consul 
2.  ./consul agent -dev
## 必须要绑定本机IP,不然其他机器无法访问
3.  ./consul agent -dev -client 192.168.1.239
```

### 安装 Upsync

- 1.安装PCRE库
```
安装PCRE库：
1.cd /usr/local/
2.wget http://jaist.dl.sourceforge.net/project/pcre/pcre/8.33/pcre-8.33.tar.gz
3.tar -zxvf pcre-8.33.tar.gz
4cd pcre-8.33
5. ./configure
6.make && make install
```

- 2.安装SSL库：
```
安装SSL库：
1.cd /usr/local/
2.wget http://www.openssl.org/source/openssl-1.0.1j.tar.gz
3.tar -zxvf openssl-1.0.1j.tar.gz
4cd openssl-1.0.1j
5. ./config
6.make && make install
```

- 3.安装zlib库 
```
安装zlib库：
1.cd /usr/local/
2.wget http://zlib.net/zlib-1.2.11.tar.gz
3.tar -zxvf zlib-1.2.11.tar.gz
4.cd zlib-1.2.11
5. ./configure
6.make && make install
```

- 4.安装nginx-upsync-module：
```
安装nginx-upsync-module：
1.cd /usr/local/
1.yum -y install unzip
2.wget https://github.com/weibocom/nginx-upsync-module/archive/master.zip
unzip nginx-upsync-module-master.zip 
如果解压出现该错误
-bash: unzip: 未找到命令
解决办法
yum -y install unzip
```

- 5.安装Nginx
```
安装Nginx:
1.cd /usr/local/
2.wget http://nginx.org/download/nginx-1.9.9.tar.gz
3.tar -zxvf nginx-1.9.9.tar.gz
3.cd nginx-1.9.0
## 重新编译nginx,增加nginx-upsync-module-master模块
3../configure --add-module=/usr/local/nginx-upsync-module-master
----
此时如果报错SSL modules require the OpenSSL library
解决办法
yum -y install openssl openssl-devel，然后重新执行以上代码
----
3.make && make install
----------------------
4.groupadd nginx
5.useradd -g nginx -s /sbin/nologin nginx
6.mkdir -p /var/tmp/nginx/client/
7.mkdir -p /usr/local/nginx
----------------------
启动：
/usr/local/nginx/sbin/nginx 
ps -ef|grep nginx
/usr/local/nginx/sbin/nginx -s stop
ps -ef|grep nginx
```

- 6.Nginx.config配置文件:

```
#user  nobody;
worker_processes  1;
events{
    worker_connections 1024;
    }
http{
    upstream myproject {
	#这个不用管，固定的
        server 127.0.0.1:11111;
        #连接ConculServer，动态获取upstream配置负载均衡信息
        upsync 192.168.1.239:8500/v1/kv/upstreams/test upsync_timeout=6m upsync_interval=500ms upsync_type=consul strong_dependency=off;
        #动态获取ConculServer相关负载均衡信息持久化到硬盘
        upsync_dump_path /usr/local/nginx/conf/servers/servers_test.conf;
        
    }
    server {
        listen 8080;
        server_name localhost;
        
        location / {
            proxy_pass http://myproject;
        }
        #查看当前代理的信息
	    location /upstream_list {
         	upstream_show;
     	}
    }
	
}
```

- 重新启动ngix
```
/usr/local/nginx/sbin/nginx 
ps -ef|grep nginx
/usr/local/nginx/sbin/nginx -s stop
ps -ef|grep nginx
/usr/local/nginx/sbin/nginx  -s reload
```

#### 添加后台服务Consul

- [打开Consul](http://192.168.1.239:8500/ui)
```
http://192.168.1.239:8500/ui
```

- 添加后台服务Consul

```

curl -X PUT -d '{"weight":10, "max_fails":2, "fail_timeout":10, "down":0}' http://192.168.1.239:8500/v1/kv/upstreams/test/192.168.1.239:2225

curl -X PUT -d '{"weight":10, "max_fails":2, "fail_timeout":10, "down":0}' http://192.168.1.239:8500/v1/kv/upstreams/test/192.168.1.239:2225

curl -X PUT -d '{"weight":10, "max_fails":2, "fail_timeout":10, "down":0}' http://192.168.1.239:8500/v1/kv/upstreams/test/192.168.1.239:1222
```
- 验证Nginx

```
原服务：
http://192.168.1.239:1222/html/vue-css.html
Ngnix代理：
http://192.168.1.239:8080/html/vue-css.html
查看当前Nginx代理的信息
http://192.168.1.239:8080/upstream_list
```



### 参考：
- [基于 Upsync 模块实现 Nginx 动态配置](https://80imike.github.io/posts/1084.html)
- [nginx+upsync+consul 构建动态nginx配置系统](http://blog.51cto.com/lee90/2056182)
- [Consul+Nginx+Upsync+Linux+Keepalived+Lvs的动态负载均衡](http://www.cnblogs.com/lzh110/p/9452463.html)
- [基于Nginx dyups模块的站点动态上下线并实现简单服务治理](https://www.cnblogs.com/beyondbit/p/6063132.html)
- [Nginx容器动态流量管理方案-nginx-upsync-module+nginx_upstream_check_module初体验](https://blog.csdn.net/yueguanghaidao/article/details/52801043)
- [lazy-balancer(可视化管理页面-参考)](https://github.com/v55448330/lazy-balancer)
