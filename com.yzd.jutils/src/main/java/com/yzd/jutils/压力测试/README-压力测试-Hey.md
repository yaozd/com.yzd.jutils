## README-压力测试-Hey
- [hey压力测试工具介绍](http://www.v218.com/a126)
- [https://github.com/rakyll/hey](https://github.com/rakyll/hey)
- [轻量级压测工具hey介绍与实践](https://www.cnblogs.com/wintersun/p/12492096.html)
- [压测工具:hey](https://blog.csdn.net/qq_15022971/article/details/92797569)
- []()
- []()

## Hey支持http2
```
可以先通过wireshark把http2的请求脚本录制下来。
然后再能Hey进行发压
```

###　常用命令
```
官方源码：https://github.com/rakyll/hey

安装命令：go get -u github.com/rakyll/hey

Hey基于goroutine，开发语言是go语言，因为他是通过异步IO，所以它可以只需要有限的线程数量就可以短时间内产生大量并发，只支持http请求。支持命令行，高并发。用于替代ab。

用法：hey [options...] <url>

如：hey -n 500 -c 100 -m POST -T "application/x-www-form-urlencoded" -d "username=1&id=10" http://www.taobao.com

参数 [options]：

-n 要运行的请求数。默认是200。

-c 并发运行的请求数。请求的总数不能小于并发级别。默认是50。

-q 速率限制，以每秒查询(QPS)为单位。默认没有限制。

-z 发送请求的应用程序配置。当时间到了，应用程序停止并退出。如果指定持续时间，则忽略n。例子:- z 10s - z 3m。

-o 输出类型。如果没有提供，则打印摘要。"csv"是唯一受支持的替代方案。转储文件的响应以逗号分隔值格式的度量。

-m  HTTP method, one of GET, POST, PUT, DELETE, HEAD, OPTIONS.

-H 自定义HTTP头。您可以通过重复标记指定所需的数量 For example, -H "Accept: text/html" -H "Content-Type: application/xml" 

-t 每个请求的超时时间(以秒为单位)。默认值是20，使用0表示无穷大。

-A  HTTP Accept header.

-d  HTTP request body.

-D  HTTP request body from file. For example, /home/user/file.txt or ./file.txt.

-T  Content-type, defaults to "text/html".

-a  Basic authentication, username:password.

-x  HTTP Proxy address as host:port.

-h2 Enable HTTP/2.

-host   HTTP Host header.

-disable-compression  禁用压缩。
-disable-keepalive    禁用keep-alive，防止重用TCP不同HTTP请求之间的连接。
-disable-redirects   禁用HTTP重定向的后续操作
-cpus        使用的cpu核数。(默认为当前机器核数) 
```