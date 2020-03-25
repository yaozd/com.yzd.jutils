# nginx-故障-499
- 环境说明：
    ```
    client(http1.1)=>nginx(http1.1)转为http1.0=>api-router(http1.0转为http1.1)=>serivce(http1.1)
    PS:nginx 默认使用的http版本：http 1.0
    
    ```
- nginx错误信息
    ```
    nginx的日志格式：
    
    error_log  logs/error.log  info;
    ===========================
    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                          '$status $body_bytes_sent "$http_referer" '
                          '"$http_user_agent" "$http_x_forwarded_for"';
    
    access_log  logs/access.log  main;
    ===========================
    access.log:
    192.168.8.219 - - [26/Dec/2019:13:42:49 +0800] "GET http://x.x.x.x/metric?uuid=0 HTTP/1.1" 499 0 "-" "-" "-"
    ===========================
    error.log:
    2019/12/24 09:44:29 [info] 6574#0: *2190106131 epoll_wait() reported that client prematurely closed connection, so upstream connection is closed too while
    reading upstream, client: 100.x.x.x, server: m.demo.com, request: "POST /xx/xx/xx?groupID=255194 HTTP/1.0", upstream: "http://172.x.x.x:
    80/x/x/x?groupID=255194", host: "m.x.x"
    
    ```
-   使用工具
    ```
    1.抓包工具
        tcpdump
        Wireshark
    ```
-   问题分析
    ```
    前提：客户端发起的是一个404的请求地址
    499：是由客户端主动关闭连接产生
    Nginx 使用http 1.0版本发起请求时，connect close;需要api router 关闭。由于api router没有发送fin包，则nginx会处于挂起状态（pending）,请求挂起等待超时。
    由于服务器请求挂起后，响应时间变长，客户则主动关闭。产生499.
    ```
-   解决方案
    - nginx 升级使用http1.1协议
        ```
        server {
                listen       80;
                server_name  localhost;
                #proxy_set_header Connection "";
                #proxy_http_version 1.1;
                #charset koi8-r;
        
                #access_log  logs/host.access.log  main;
                #access_log  "pipe:rollback logs/host.access_log interval=1d baknum=7 maxsize=2G"  main;
        
                location / {
                    #root   html;
                    #index  index.html index.htm;
                    proxy_set_header Host dohko.http-demo.9091.demo.com;
                    proxy_pass http://pro;
                    #proxy_set_header Connection "";
                    #proxy_http_version 1.1;       
                }
                location /proxy {
                    proxy_set_header Host dohko.online.h5api.demo.com;
                    proxy_pass http://pro;
                    proxy_http_version   1.1;
                }
        
                location /status {
                        vhost_traffic_status_display;
                        vhost_traffic_status_display_format html;
                }
        PS:建议直接在location中添加：proxy_set_header Connection "";与proxy_http_version 1.1;  
        ```
    - API-ROUTER发送fin包
        ```
        nginx http 1.0 下请求头head中connection: close
        API-ROUTER中检测connection: close后，主动关闭，发送FIN包
        ```