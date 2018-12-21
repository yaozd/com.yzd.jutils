###　Zstack-安装
- [zstack快速安装文档](http://www.cnblogs.com/IPYQ/p/7771109.html)
- [zstack.properties](https://www.cnblogs.com/IPYQ/p/7771103.html)

- zstack.properties
```
特别需要注意的是，zstack.properties是ZStack的核心配置文件。
它会存放在每一个管理节点中。 
zstack.properties文件中会存放诸如数据库URL，用于数据库访问用户名密码，RabbitmMQ的IP地址等等。
每一个管理节点上的zstack.properties文件的内容基本上是一致的。
它的路径可以通过zstack-ctl status来获得。
如果是默认安装的话，它会存放在/usr/local/zstack/apache-tomcat/webapps/zstack/WEB-INF/classes/zstack.properties 。 
你可以手动编辑它，也可以通过zstack-ctl configure来完成配置。
不过通常情况下， 当用户在使用zstack-ctl命令来安装或者部署对应的服务的时候，zstack-ctl`会自动的完成部署。
```
### Zstack-重启
```
zstack-ctl start_node
zstack-ctl start_ui
```
