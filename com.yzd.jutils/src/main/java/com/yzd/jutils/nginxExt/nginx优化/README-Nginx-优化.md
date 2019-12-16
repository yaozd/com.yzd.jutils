-  nginx 配置优化 -性能：4万/s (反向代理模式)
    ```
    cat conf/nginx.conf
    
    #user  nobody;
    worker_processes  auto;
    worker_rlimit_nofile 1040000;
    
    #pid        logs/nginx.pid;
   
    events {
        worker_connections  1040000;
        multi_accept on;
    }

    ```
- HTTP (nginx 1.12.2; support 50K+ qps) -推荐参考byArvin
    ```
    cat conf/nginx.conf
    
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
        # Transfer data in kernal space,allow zero copy
        sendfile            on;
        # Optimizes the amount of data sent at once,activated with sendfile
        tcp_nopush          on;
        # Forces a socket to send the data in its buffer
        tcp_nodelay         on;
        # Keep alive time, unit seconds
        keepalive_timeout   65;
        # Max keep alive request count
        keepalive_requests 10000;
    }
    ```