### 1.[新功能：阿里云负载均衡SLB支持HTTP访问强制跳转HTTPS](https://blog.csdn.net/maoreyou/article/details/80047901)

### 2.[用 // 代替 http:// 有什么好处(自适应https)](https://www.jb51.net/web/599309.html)

```
//缺省协议
//缺省协议的使用，代表资源访问的协议和当前页面保持一致，如果当前页面是http ，采用http协议访问，如果是https，则使用 https 协议访问。这样用就不管是http还是升级到https都不用改动代码,现在很多CDN资源都是这样引用。一般使用在内链中，外链的协议头具有不确定性的原因。
----
// 缺点

直接打开本地文件调试时，使用的协议是文件协议(file://)
这个时候这个协议会变成 file://jb51.net/css/显然是不存在的
与当前网站的协议保持一致，快速发布与你当前协议相匹配的版本，同时减少SSL或其它协议版本的部署成本。开发者不需要管服务器云端提供什么协议，只要用//符号来代表一切最适应的匹配，这和nodeJS的思维是一脉相承的。

优点如下：
因为很多网站都将http升级为https，这样就可以防止我们的网址被劫持，前期为了在转换过程中我出差错我们没有强制跳转,就是当用户访问http或https都可以正常访问，那么里面的js，图片，链接等都不能用https或http，那么有什么解决方法呢，那么解决方法来了就是用//，不要带http:与https这样就可以了。
//这种写法是根据你请求的协议自动添加协议的。举个栗子：你的网站是http协议，那么其实你访问的就是http://xxxx 如果你的网站是https协议的，那么请求的地址会变成https://xxxx 要知道，如果你写成了http://xxx. 那么如果你们的网站线上是https，那么可能会报安全警告，有的浏览器甚至没法正常加载页面。如果你直接写成https，要知道，本地开发可是http啊...

```

### 3.[header中Content-Disposition的作用与使用方法](https://www.jb51.net/article/30565.htm)
```
Response.AppendHeader("Content-Disposition","attachment;filename=FileName.txt"); 
--------------------
服务端向客户端游览器发送文件时，如果是浏览器支持的文件类型，一般会默认使用浏览器打开，比如txt、jpg等，会直接在浏览器中显示。
如果需要提示用户保存，就要利用Content-Disposition进行一下处理，关键在于一定要加上attachment： 
```
