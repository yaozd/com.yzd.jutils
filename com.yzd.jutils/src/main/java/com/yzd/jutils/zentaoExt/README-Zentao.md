#### -[项目管理工具-禅道开源版使用帮助](https://www.zentao.net/book/zentaopmshelp/256.html) 
- [通过看板和树状图查看任务](https://www.zentao.net/book/zentaopmshelp/256.html)
- [禅道开源版使用手册-百度文库](https://wenku.baidu.com/view/f679050f2379168884868762caaedd3383c4b5e6.html)
- [禅道使用帮助手册-百度文库](https://wenku.baidu.com/view/dd6d08a5dc3383c4bb4cf7ec4afe04a1b071b04a.html)
- [禅道操作手册-包含关系图-推荐byArvin-百度文库](https://wenku.baidu.com/view/50db8a315b8102d276a20029bd64783e09127dee.html)

#### -禅道安装
```
cd /usr/local/src/
wget http://dl.cnezsoft.com/zentao/10.3/ZenTaoPMS.10.3.stable.int.zbox_64.tar.gz
tar zxf ZenTaoPMS.10.3.stable.int.zbox_64.tar.gz -C /opt/


重启Apache和Mysql
/opt/zbox/zbox restart
echo "/opt/zbox/zbox restart" >> /etc/rc.local

apache端口改为8080，mysql端口改为3307
/opt/zbox/zbox -ap 80 -mp 3307

浏览器直接访问 http://禅道服务器ip:apache端口
禅道默认管理员帐号是 admin，密码 123456
```
####　ZenTaoPMS.10.3.stable.int.zbox_64.tar.gz（备份下载）
```
1.百度云-》软件开发-java-》C-禅道
```
