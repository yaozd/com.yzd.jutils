## *RPM安装*
- [linux中yum与rpm区别](https://www.cnblogs.com/ryanzheng/p/11322375.html)
- 一、源代码形式
- 二、RPM
- 三：YUM
```
使用rpm –i software.rpm(安装)；

rpm -e software.rpm(卸载)；

rpm –U software.rpm(升级形式安装)；

rpm –ivh http://www.linuxcast.net/software.rpm(支持通过http\ftp协议形式安装)

-v 显示详细信息；-h显示进度条

查询功能：rpm –qa 列出全部已经安装的.rpm软件  rpm –qa |grep ***
```