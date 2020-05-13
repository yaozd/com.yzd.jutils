## DNS
- [Linux在shell终端中清空DNS缓存,刷新DNS的方法(ubuntu,debian)](https://blog.csdn.net/zhangpeterx/article/details/83895446)
- [Linux中搭建DNS服务器](https://blog.csdn.net/qq_36119192/article/details/82752515)
- []()
- []()

##　dnsjava-DNS BIND之dnsjava java客户端操作
- [Java Lookup.setCache方法代码示例](https://vimsky.com/examples/detail/java-method-org.xbill.DNS.Lookup.setCache.html)
- [DNS BIND之dnsjava java客户端操作](https://blog.csdn.net/zhu_tianwei/article/details/45128537)
- [java读取DNS中的各种解析记录](https://blog.csdn.net/wjw521wjw521/article/details/72844421)

## DNS 优先级别
```
优先级：  本地DNS缓存 > hosts文件  > DNS服务器
windows中hosts文件存放路径：  C:\Windows\System32\drivers\etc\hosts
Linux中hosts文件存放路径：       /etc/hosts
```

## DNS 查询
- nslookup
```
nslookup baidu.com
nslookup baidu.com 114.114.114.114
nslookup baidu.com 192.168.56.112
```

## jvm dns缓存问题解决方式
- [jvm dns缓存问题解决方式](https://www.cnblogs.com/langke93/archive/2012/11/29/2794439.html) -推荐参考byArvin
    ```
    //设置解析成功的域名记录JVM中缓存的有效时间，JVM默认是永远有效，
    这样一来域名IP重定向必须重启JVM，这里修改为5秒钟有效，0表示禁止缓存，-1表示永远有效
    java.security.Security.setProperty("networkaddress.cache.ttl", "5");
    //设置解析失败的域名记录JVM中缓存的有效时间，JVM默认是10秒，0表示禁止缓存，-1表示永远有效
    java.security.Security.setProperty("networkaddress.cache.negative.ttl","2");
    ```
- [Netty Client使用域名重连的问题](https://blog.csdn.net/weixin_33918114/article/details/92007940)
- []()

## linux dnsmasq
- [dnsmasq使用注意事项](https://blog.csdn.net/kalman2008/article/details/44854977)
- [如果要支持一个域名对应多个 IP，就必须使用 addn-hosts 选项](http://www.imooc.com/article/277475)
    ```
    /etc/hosts.dnsmasq 文件内容如下：
    
    $ cat /etc/hosts.dnsmasq
    10.0.0.1 blackpiglet.com
    10.0.0.2 blackpiglet.com
    10.0.0.3 blackpiglet.com
    修改完成后重启 DNSMasq
    
    $ sudo systemctl restart dnsmasq.service
    //
    netstat -ntpl
    kill -9 1503
    netstat -ntpl
    systemctl restart dnsmasq.service
    systemctl status dnsmasq.service
     
    ```
- [DNSMasq 域名解析配置](http://www.imooc.com/article/277475)

## DNS-dnsmasq安装配置
- [dnsmasq安装配置](https://blog.csdn.net/weixin_30706691/article/details/96451360)
- [DNS-dnsmasq安装配置](https://www.cnblogs.com/taoyuxuan/p/11205491.html)
- []()