
#### -Nginx-灰度发布

```
1.通过map的方式做分流-（推荐byArvin）
```
#### -Map-$COOKIE-正则（~ 符号）

```
1.abcdexpid的值以“1”结尾
map $COOKIE_abcdexpid $group {
~*1$ apache001;
~*2$ apache002;
default root;
}
---------------------------------
map $COOKIE_ickey $group {
~*[0-5]$ zone01;     #cookie 0-5结尾走zone01
~*[6-9]$ zone02;     # cookie 6-9 结尾走zone02
default root;          #默认
}
---------------------------------
2.user_city的值等于“sh”,~ 符号代表正则的意思
map $cookie_user_city $upst {
    default web;
    ~sh	sh;
    ~nj	nj;
}
---------------------------------
3.id的值等于“700003508”，不是通过正则的方式，性能最优！！
map $COOKIE_id $group {
    700003508 admin;
    ~*3$ admin;
    default user;
}
```
#### Fiddler-灰度逻辑

```
1.过滤器-Fiddle过滤URL：URL是支持统配符操作
eg:
*.baidu.com 或 www.baidu.com
-----------------------------------
2.FiddleScript-Fiddle设置全局cookie-Nginx可以通过cookie进行数据分流
static function OnBeforeRequest(oSession: Session) {
//在OnBeforeRequest下面加入此代码
//request_version_id代表：请求的版本：正常版本还灰度版本
oSession.oRequest["Cookie"] = (oSession.oRequest["Cookie"] + ";request_version_id=gray");
-----------------------------------
```
### Nginx-Config示例--灰度

```
#user  nobody;
worker_processes  1;

#error_log  logs/error.log;
#error_log  logs/error.log  notice;
#error_log  logs/error.log  info;

#pid        logs/nginx.pid;

events {
    worker_connections  1024;
}

http{
	#----------------------------
	#www.yzd.com网站-正常版本
	upstream www_yzd_com_normal {
    server 127.0.0.1:28002;
	}
	#www.yzd.com网站-灰度版本
	upstream www_yzd_com_gray {
    server 127.0.0.1:28001;
	}
	#通过map形式切换版本
	map $cookie_request_version_id $www_yzd_com_upst {
    default www_yzd_com_normal;
    ~gray	www_yzd_com_gray;
	}
	#----------------------------
    server {
        listen 80;
        server_name www.yzd.com;
        location / {
            proxy_pass http://$www_yzd_com_upst;
			#proxy_set_header Host $host;特别重要，如果不设置则不能正常解析
			proxy_set_header Host $host;
        }
    }
}

```
#### 名词解释
```
www_yzd_com网站为例
网站-正常版本：www_yzd_com_normal
网站-灰度版本：www_yzd_com_gray
Cookie名称：request_version_id
Cookie值：gray
upstream变量：$www_yzd_com_upst
```
#### 相关资料下载地址
```
百度云：开发工具>N-Nginx-Nginx-灰度发版
```
#### 参考-cookie分流
- [Nginx高效分发之cookie篇-推荐byArvin](http://blog.51cto.com/leon0long/1917256)
- [nginx根据cookie里的信息分流](http://blog.51cto.com/feihan21/990804)
- [nginx 基于cookie分流](http://blog.51cto.com/dyc2005/2299567)
- [nginx根据cookie分流](https://blog.csdn.net/weixin_39891030/article/details/82761744)
- []()
