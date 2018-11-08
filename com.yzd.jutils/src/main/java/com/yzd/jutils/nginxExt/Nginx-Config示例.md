### 示例一：最简单的反向代理-(已验证-byArvin-181108)

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
--------------------------------
### 示例二：最简单的反向代理-Upsync-Consul-(已验证-byArvin)
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
        }
}
```
--------------------------------
### 示例三：最简单的反向代理

- nginx.conf文件：
```
user  nginx;
worker_processes  5;
error_log  /var/log/nginx/error.log warn;
pid        /var/run/nginx.pid;
events {
    worker_connections  1024;
}
http {
    include       /usr/local/nginx/conf/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;
    keepalive_timeout  65;
    #gzip  on;
    include /usr/local/nginx/conf/conf.d/*.conf;
}

```
- site.conf文件：

```
upstream test {
        #这个不用管，固定的
        server 127.0.0.1:11111;
        #连接ConculServer，动态获取upstream配置负载均衡信息
        upsync 192.168.1.239:8500/v1/kv/upstreams/test upsync_timeout=6m upsync_interval=500ms upsync_type=consul strong_dependency=off;
        #动态获取ConculServer相关负载均衡信息持久化到硬盘
        upsync_dump_path /usr/local/nginx/conf/servers/servers_test.conf;
    }
    server {
        listen 80;
        server_name  localhost;
        location = / {
        proxy_pass http://test;      
        index index.html index.htm;
        proxy_connect_timeout 1;
        proxy_send_timeout 1;
        proxy_read_timeout 1;
        }
    }
```
--------------------------------
