# nginx V1.8
## 使用nginx实现grpc负载均衡
- [使用nginx实现grpc负载均衡](https://blog.csdn.net/zhangzhen02/article/details/105949938)
- [grpc+nginx架构部署指导](https://blog.csdn.net/liitdar/article/details/79803128)
- [http://nginx.org/en/docs/http/ngx_http_grpc_module.html](http://nginx.org/en/docs/http/ngx_http_grpc_module.html)- grpc
- [http://nginx.org/en/docs/http/ngx_http_v2_module.html](http://nginx.org/en/docs/http/ngx_http_v2_module.html) -http2 
```
HTTP2 + grpc (nginx 1.16+)

#Worker size
worker_processes 8;
#Worker cpu affinity
worker_cpu_affinity 00000001 00000010 00000100 00001000 00010000 00100000 01000000 10000000;
#Worker open files size
worker_rlimit_nofile 204800;
  
events {
    # use epoll model
    use epoll;
    # Each worker connection size
    worker_connections 20480;
    # Accept all connections at a time
    multi_accept on;
}
  
http {
    access_log off;
    # Transfer data in kernal space,allow zero copy
    sendfile            on;
    # Optimizes the amount of data sent at once,activated with sendfile
    tcp_nopush          on;
    # Forces a socket to send the data in its buffer
    tcp_nodelay         on;
     
    server {
        listen  8089 http2;
        http2_max_concurrent_streams 1000000;
        http2_max_requests 2147483646;
        http2_recv_timeout 120s;
        http2_idle_timeout 5m;
        location / {
            grpc_pass grpc://127.0.0.1:30009;
        }
    }
}

```