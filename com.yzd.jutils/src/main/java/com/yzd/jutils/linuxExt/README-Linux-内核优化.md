## 1.Linux-内核优化
- [nginx连接数优化](https://www.cnblogs.com/vicowong/p/9560967.html) -推荐参考byArvin
- [Nginx的一些优化 突破十万并发（转）](https://www.cnblogs.com/vicowong/p/11663667.html)
- [缓解DDoS && cc 的最佳Linux内核设置 (转)](https://www.cnblogs.com/vicowong/p/11748142.html)

- [linux 最大文件打开数nofile及nr_open、file-max说明](https://blog.csdn.net/wh0426/article/details/52311683)
    ```
  nr_open定义是单进程最大file-handles
  ```
- [https://github.com/smallnest/1m-go-tcp-server](https://github.com/smallnest/1m-go-tcp-server) 百万 Go TCP 连接的思考
    ```
  tune the linux:
  
  sysctl -w fs.file-max=2000500
  sysctl -w fs.nr_open=2000500
  sysctl -w net.nf_conntrack_max=2000500
  ulimit -n 2000500
  
  sysctl -w net.ipv4.tcp_tw_recycle=1
  sysctl -w net.ipv4.tcp_tw_reuse=1
  ```