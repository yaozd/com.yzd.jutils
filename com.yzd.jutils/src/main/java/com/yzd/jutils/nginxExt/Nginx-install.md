## Nginx-配置生成
- [NGINX Config-在线生成工具](https://www.digitalocean.com/community/tools/nginx)
- 常用命令：
```
yum install -y nginx
//
nginx 支持grpc
./configure --with-http_ssl_module --with-http_v2_module
make && make install
//
运行nginx程序
./sbin/nginx
//
systemctl status nginx
systemctl enable nginx
systemctl start nginx
systemctl restart nginx
//
nginx -t
nginx -s reload
nginx -s restart
nginx -s stop
//
启动nginx | start nginx
修改配置后重新加载生效| nginx -s reload
重新打开日志文件| nginx -s reopen
测试nginx配置文件是否正确| nnginx -t -c nginx.conf
关闭nginx ：快速停止nginx | nginx -s stop
完整有序的停止nginx |nginx -s quit
```

- [相关目录](https://blog.csdn.net/u013545439/article/details/103868512)
```
nginx 日志文件 /var/log/nginx
nginx配置文件目录 /etc/nginx
nginx 可执行文件 /usr/sbin/nginx
nginx环境配置 /etc/sysconfig/nginx
nginx默认站点目录  /usr/share/nginx/html
```