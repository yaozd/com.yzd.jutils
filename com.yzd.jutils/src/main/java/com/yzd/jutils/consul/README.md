### consul官网
-[https://www.consul.io/downloads.html](https://www.consul.io/downloads.html)
### 下载备份
> 百度云=》开发工个=》C-Counsul服务发现

### consul参考
-  [rickfast/consul-client](https://github.com/rickfast/consul-client)
-  [consul-02.consul服务注册实现（java）](https://blog.csdn.net/Sukiyou_xixi/article/details/80378391)
-  [第二十章 springboot + consul（1）](https://www.cnblogs.com/java-zhao/p/5527779.html)
-  [Ecwid/consul-api](https://github.com/Ecwid/consul-api)
-  [Prometheus 通过consul动态修改Targets接入](https://blog.csdn.net/poorcoder_/article/details/79120218)

### 2.Consul-INSTALL

1.Consul-run.bat
```
//-dev 代表是开发模板不存储在磁盘，数据存储在内存
consul agent -dev
```
2.Consul-open-consul.bat
```
//Windows dos下
start http://127.0.0.1:8500/ui/dc1/services
```
参考：[【微服务No.1】Consul服务发现在windows下简单使用](https://www.cnblogs.com/yanbigfeg/p/9199590.html)
### Prometheus通过consul实现自动发现
```
[Prometheus 通过consul动态修改Targets接入](https://blog.csdn.net/poorcoder_/article/details/79120218)
```

### rickfast/consul-client -byArvin推荐使用-20180905
- https://github.com/rickfast/consul-client
- https://github.com/yaozd/consul-client

### consul健康检查
```
consul健康检查包括两种方式：
1.TCP 端口
2.HTTP检查

```
###　默认情况下，TCP checks中，请求超时时间等于调用请求的间隔时间，最大10秒。也是可以自由配置的。

### 最终确定的写法通过tcp方式来进行检查
```
//如果在服务注册的时候使用的是http方式，则getResponse中会带有大量的响应信息-output。使用传输的数据变大
//因此推荐使用TCP方式进行服务的检查
//目前的功能主要是consul作为prometheus的注册发现,jmx_exporter输出的信息比较多,所以推荐tcp注册方式
//https://github.com/prometheus/jmx_exporter
参考代码
@Test
public void testAdd_finall()
```
### Consul帮助文档
- README-consul-client-[1.2.3].md