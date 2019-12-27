##　参数详解
- TIME_WAIT(TIME_WAIT的产生是由：本机发起关闭信号)
    ```
    PS:主要是针对客户端有影响，服务器端可以通过配置重用端口
    TIME_WAIT 
    
    #Enable reuse of TIME-WAIT sockets for new connections when it is safe from protocol viewpoint
    #重用端口，PS:如果没有打开端口重用，nginx可能为报502
    net.ipv4.tcp_tw_reuse = 1
    ```