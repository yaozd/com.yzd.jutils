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