
- [Node.js安装及环境配置之Windows篇](https://www.cnblogs.com/zhouyu2017/p/6485265.html)
-
```
1、Node.js简介
简单的说 Node.js 就是运行在服务端的 JavaScript。Node.js 是一个基于 Chrome V8 引擎的 JavaScript 运行环境。Node.js 使用了一个事件驱动、非阻塞式 I/O 的模型，使其轻量又高效。Node.js 的包管理器 npm，是全球最大的开源库生态系统。
2、下载Node.js
打开官网下载链接:https://nodejs.org/en/download/ 我这里下载的是node-v6.9.2-x64.msi,如下图：
3.DOS打开控制面板
- control
控制面板\系统和安全\系统

```
### node 下载
- [https://nodejs.org/en/download/current/](https://nodejs.org/en/download/current/)

### node 启动
```
//安装模块
npm -i 
// 打包
./build.sh :uat
===================
编译参数	
mkdir -p /data/local
git clone 	http://github.com/console-web.git /data/local/console-web
cd /data/local/console-web
npm i 
npm run build
//
启动参数	启动nginx即可
//
编译环境	
node v13.0.1

npm v6.12.0

typescript v3.4.5
//
服务类型	nginx
nginx 部署要求	
server {
  listen 80;

  set $root "/data/local/console-web/build";
 
  server_name localhost;

  index index.html index.htm;

  location ~* \.map$ {
    root "${root}";
  }
 
  location / {
    root "${root}";
    index index.html;
    add_header Cache-Control no-cache;
    try_files $uri /index.html;
  }
}
```

## 参考
- [npm i和npm install的区别](https://blog.csdn.net/chern1992/article/details/79193211)
- []()
- []()