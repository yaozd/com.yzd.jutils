## Nginx-配置生成
- [NGINX Config-在线生成工具](https://www.digitalocean.com/community/tools/nginx)
- 常用命令：
```
yum install -y nginx
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
```
- 示例：shopapi.conf
```
upstream shopapi {
        server 127.0.0.1:8090;
}
server {
        listen 80;
        listen [::]:80;

        server_name shopapi.demo.com;
        # root /var/www/shopapi.demo.com/public;

        # security
        # include nginxconfig.io/security.conf;

        # logging
        access_log /var/log/nginx/shopapi.demo.com.access.log;
        error_log /var/log/nginx/shopapi.demo.com.error.log warn;

        # reverse proxy
        location / {
                proxy_pass http://127.0.0.1:8090/inventory/saveOrUpdate;
                # include nginxconfig.io/proxy.conf;
                proxy_http_version 1.1;
                proxy_set_header Upgrade $http_upgrade;
                proxy_set_header Connection "upgrade";
        }

        # additional config
        # include nginxconfig.io/general.conf;
}
```