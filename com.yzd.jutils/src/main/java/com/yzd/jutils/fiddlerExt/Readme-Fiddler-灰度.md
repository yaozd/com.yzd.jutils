
#### Fiddler-灰度逻辑

```
1.过滤器-Fiddle过滤URL：URL是支持统配符操作
eg:
*.baidu.com 或 www.baidu.com
-----------------------------------
2.FiddleScript-Fiddle设置全局cookie-Nginx可以通过cookie进行数据分流
static function OnBeforeRequest(oSession: Session) {
//在OnBeforeRequest下面加入此代码
oSession.oRequest["Cookie"] = (oSession.oRequest["Cookie"] + ";YourCookieName=YourCookieValue");
-----------------------------------
```
参考：
- [Fiddler过滤指定域名的方法](https://blog.csdn.net/twlkyao/article/details/20567847)
- [如何使用Fiddler在请求中设置cookie？](https://cloud.tencent.com/developer/ask/110731)

如何使用Fiddler在请求中设置cookie？

```
在使用Fiddler向网站发出请求之前，我需要设置cookie。我该怎么做？
https://cloud.tencent.com/developer/ask/110731
若要使用FiddlerScript引擎，将下列代码添加到onBeforeRequest：
oSession.oRequest["Cookie"] = (oSession.oRequest["Cookie"] + ";YourCookieName=YourCookieValue");
这将保留已设置的任何其他cookie。
```

Fiddler-下载地址
```
Fiddler 官方版 v5.0.20182.28034-推荐byArvin-20181210-已测试可用
注：下载的版本是一个汉化版，来自：
http://www.downza.cn/soft/234727.html
备份：百度云：开发工具>F-Fiddler抓包-
```