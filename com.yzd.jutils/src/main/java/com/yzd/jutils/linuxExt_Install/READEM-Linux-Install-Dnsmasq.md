## DNS 查询
- nslookup
```
nslookup baidu.com
nslookup baidu.com 114.114.114.114
nslookup baidu.com 192.168.56.112
```
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

## 示例 byArvin
```
[root@localhost etc]# ll |grep dns
dnsmasq.conf
dnsmasq.d   (PS:dnsmasq.d是目录)
hosts.dnsmasq
resolv.dnsmasq.conf

[root@localhost etc]# cat dnsmasq.conf |grep -v ^# |grep -v ^$
resolv-file=/etc/resolv.dnsmasq.conf
listen-address=127.0.0.1,192.168.56.112
addn-hosts=/etc/hosts.dnsmasq
conf-dir=/etc/dnsmasq.d,.rpmnew,.rpmsave,.rpmorig

[root@localhost etc]# cat hosts.dnsmasq |grep -v ^# |grep -v ^$
10.0.0.1 dns.test.demo.com
10.0.0.2 dns.test.demo.com
10.0.0.3 dns.test.demo.com

[root@localhost etc]# cat resolv.dnsmasq.conf |grep -v ^# |grep -v ^$
nameserver 114.114.114.114
nameserver 8.8.8.8
nameserver 172.16.0.75

## 启动命令：
systemctl stop dnsmasq.service
systemctl start dnsmasq.service
systemctl restart dnsmasq.service
systemctl status dnsmasq.service
systemctl enable dnsmasq.service

PS:
过滤注释行和空白行
cat elasticsearch.yml  | grep -v  ^#  |grep -v ^$
```