## nginx强制禁止缓存 
```
server {
    listen  19081;

    set $root "/data/local/hyperspace-console-frontend/build";

    client_max_body_size 4G;
    server_name  localhost;
    #nginx强制禁止缓存 
    add_header 'Cache-Control' 'no-store, no-cache, must-revalidate, proxy-revalidate, max-age=0';

    index index.html index.htm;

    location ~* \.map$ {
      root "${root}";
    }

    location / {
      root "${root}";
      index index.html;
      try_files $uri /index.html;
    }
}

```