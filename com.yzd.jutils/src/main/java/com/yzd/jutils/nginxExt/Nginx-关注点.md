## 关注点
- [nginx会去掉带有下划线的Header键值](https://sunzhy.blog.csdn.net/article/details/71516649)
```
在本地可以获取前端header传的参数，但是部署到服务器获取的就是null（服务器地址用nginx做了代理）
原因：
nginx对header name的字符做了限制，默认 underscores_in_headers 为off，表示如果header name中包含下划线，则忽略掉，部署后就获取不到。
解决：
在header里不要用 “_” 下划线，可以用驼峰命名或者其他的符号（如减号-）代替。nginx默认忽略掉下划线可能有些原因。
在nginx里的 nginx.conf文件中配置http的部分添加 ： underscores_in_headers on;（默认值是off）
不过看标准的http header里面都用的连字符, 不用下划线. 我们还是避免吧:
Accept、Accept-Charset、Accept-Encoding、Accept-Language、Authorization、From、Host、If-Modified-Since、If-Match、
If-None-Match、If-Range、If-Range、If-Unmodified-Since、Max-Forwards、Proxy-Authorization、Range、Referer、User-Agent
```