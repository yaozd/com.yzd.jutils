## Nginx-监控
- [Nginx添加nginx-module-vts监控](https://blog.csdn.net/ywd1992/article/details/85245559) 推荐参考byArvin
- [用Prometheus细化Nginx监控](https://blog.51cto.com/xujpxm/2080146)
- []()

## 安装
- 环境：
    - 安装依赖包
    > yum install gcc gcc-c++ make unzip pcre pcre-devel zlib zlib-devel libxml2 libxml2-devel  readline readline-devel ncurses ncurses-devel perl-devel perl-ExtUtils-Embed openssl-devel -y
    - [Tengine-2.3.2.tar.gz](http://tengine.taobao.org/download_cn.html)
    - [nginx-module-vts v0.1.18](https://github.com/vozlt/nginx-module-vts/releases/tag/v0.1.18)
    - 执行命令
    ```
    cd /data/package/tengine-2.3.2
    ./configure --add-module=/data/package/nginx-module-vts-0.1.18
    make && make install
    -----------------------------------------------------------------
    cd /usr/local/nginx/
    
    ```
    - [http://192.168.99.101/status](http://192.168.99.101/status)
    - []()
- nginx.conf
    ```
    #user  nobody;
    worker_processes  auto;
    worker_rlimit_nofile 1040000;
    
    #error_log  logs/error.log;
    #error_log  logs/error.log  notice;
    #error_log  logs/error.log  info;
    #error_log  "pipe:rollback logs/error_log interval=1d baknum=7 maxsize=2G";
    
    #pid        logs/nginx.pid;
    
    
    events {
        worker_connections  1040000;
        multi_accept on;
    }
    
    
    http {
        include       mime.types;
        default_type  application/octet-stream;
        vhost_traffic_status_zone;
        #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
        #                  '$status $body_bytes_sent "$http_referer" '
        #                  '"$http_user_agent" "$http_x_forwarded_for"';
    
        #access_log  logs/access.log  main;
        #access_log  "pipe:rollback logs/access_log interval=1d baknum=7 maxsize=2G"  main;
    
        sendfile        on;
        #tcp_nopush     on;
    
        #keepalive_timeout  0;
        keepalive_timeout  65;
        #keepalive_requests 100000;
        #gzip  on;
        upstream pro {
        #keepalive_requests 100000;
        server 127.0.0.1:8081;
        }
        server {
            listen       80;
            server_name  localhost;
    
            #charset koi8-r;
    
            #access_log  logs/host.access.log  main;
            #access_log  "pipe:rollback logs/host.access_log interval=1d baknum=7 maxsize=2G"  main;
    
            location / {
                root   html;
                index  index.html index.htm;
            }
    	location /proxy {
                proxy_pass http://pro;
            }
    
    	location /status {
    		vhost_traffic_status_display;
    		vhost_traffic_status_display_format html;
            }
    
            #error_page  404              /404.html;
            #
            error_page   500 502 503 504  /50x.html;
            location = /50x.html {
                root   html;
            }
    
       }
    
    }

    ```