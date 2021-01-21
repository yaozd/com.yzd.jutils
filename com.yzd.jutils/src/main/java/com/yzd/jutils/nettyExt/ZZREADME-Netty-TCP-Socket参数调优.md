# Socket参数调优

## 重要参考
- [漫画 | 一台Linux服务器最多能支撑多少个TCP连接？](https://www.cnblogs.com/cangqinglang/p/14131541.html)
    ```
  系统级：当前系统可打开的最大数量，通过fs.file-max参数可修改
  用户级：指定用户可打开的最大数量，修改/etc/security/limits.conf
  进程级：单个进程可打开的最大数量，通过fs.nr_open参数可修改
  //
  缓存区:
  1.接收缓存区大小   值默认是4K
  通过sysctl命令就可以查看。"
  $ sysctl -a | grep rmem
  net.ipv4.tcp_rmem = 4096 87380 8388608
  net.core.rmem_default = 212992
  net.core.rmem_max = 8388608
  "其中在tcp_rmem"中的第一个值是为你们的TCP连接所需分配的最少字节数。该值默认是4K，最大的话8MB之多。也就是说你们有数据发送的时候我需要至少为对应的socket再分配4K内存，甚至可能更大。
  2.发送缓存区的大小    值默认是4K
  $ sysctl -a | grep wmem
  net.ipv4.tcp_wmem = 4096 65536 8388608
  net.core.wmem_default = 212992
  net.core.wmem_max = 8388608
  "在net.ipv4.tcp_wmem"中的第一个值是发送缓存区的最小值，默认也是4K。当然了如果数据很大的话，该缓存区实际分配的也会比默认值大。"
  ```
## 重要事说3遍！！！
- [Linux内核 TCP/IP、Socket参数调优](https://www.cnblogs.com/pangguoping/p/5830328.html) 特别推荐参考byArvin
    ```
  调整linux系统参数时，必看！！！！！！
  ```
- [Linux内核 TCP/IP、Socket参数调优](https://www.cnblogs.com/pangguoping/p/5830328.html) 特别推荐参考byArvin
    ```
  调整linux系统参数时，必看！！！！！！
  ```
- [Linux内核 TCP/IP、Socket参数调优](https://www.cnblogs.com/pangguoping/p/5830328.html) 特别推荐参考byArvin
    ```
  调整linux系统参数时，必看！！！！！！
  ```
- []()