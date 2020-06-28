## nginx安装内核参数和配置参数总结-by元召
| 内核参数  | 值 | 解释 |
| ------------- | ------------- |------------- |
/proc/sys/net/ipv4/tcp_max_syn_backlog|2048|调整半连接队列的大小
/proc/sys/net/core/somaxconn|2048|tcp最大连接数
连接取决于这两个值的最小值| | 
net.ipv4.ip_forward|1|打开转发
/proc/sys/net/ipv4/tcp_tw_recycle|0|time wait快速回收，千万不要打开，1是打开，会出现connect timeout的问题 对客户端和服务器同时起作用，开启后在 3.5*RTO 内回收，负载会把 timestamp 都给清空
/proc/sys/net/ipv4/tcp_tw_reuse|0|复用timewaite连接，打开的意义不大，因为这个状态出现在客户端

| NGINX参数  | 值 | 解释 |
| ------------- | ------------- |------------- |
worker_processes |8|工作进程数量,和cpu数量相等就行
use epoll;| |使用epoll
worker_connections|10240|单个工作进程允许建立的最多连接数
server_names_hash_bucket_size|512|server name 的hash表大小
server_names_hash_max_size|1024|最大的大小
underscores_in_headers|on|开启使用自定义http头部的选项，支持特定的业务
sendfile |on|使用sendfile 好处：两个描述符之间直接传输数据，完全在内核操作，不需要先 read 再 write，没有上下文切换开销
tcp_nopush|on|启用了sendfile才生效，启用后数据包增加到一定大小才会发送，提升网络效率
tcp_nodelay  |on|禁用 Nagle 算法， 加快发送数据，keepalived连接才会启用
/|/|上面两个的参数同时开启，最终的效果是先填满包，再尽快发送。
keepalive_timeout |600s|长链接超时时间
keepalive_requests|409600|一个长链接可以处理的客户端的请求数量最大值
client_header_buffer_size|512k|请求头缓存大小，若请求头大，应该设置，减少内存分配的次数
client_max_body_size|2000M|请求头body的大小，默认1M。不设置上传文件会发生413
client_body_buffer_size|100M|body缓冲区大小，若超过写入文件
large_client_header_buffers|4 1024k|当超过 上面的buffersize 使用这个，最大使用四个大小
proxy_buffer_size|8192k|代理缓存的大小，nginx-后端服务的缓存大小
proxy_buffers|32 8192k|到后端服务的缓存超过后最多可以申请多少缓存块
proxy_headers_hash_bucket_size|6400|代理的后端服务的hash表大小，一次分配的大小
proxy_headers_hash_max_size|51200|代理的后端服务hash表最大可以多少
(proxy_connect_timeout/proxy_read_timeout/proxy_send_timeout) |60|	这三个值和后端服务同时的超时时长，设置成一样就可以
