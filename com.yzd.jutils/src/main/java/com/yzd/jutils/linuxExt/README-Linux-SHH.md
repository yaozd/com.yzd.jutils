
### [解决ssh执行脚本无法使用环境变量](https://blog.csdn.net/u014449046/article/details/79979640)

```
通过“source /etc/profile;”加载环境变量

解决ssh执行脚本无法使用环境变量
手动执行一次source, 如 
ssh user@host “source /etc/profile; /path/script.sh” 
/etc/profile文件设置环境变量
---------------------------------------
例如（-Rundeck）：
#!/bin/bash -l
source /etc/profile
cd /data/server-package/script
rm -rf /data/server-package/http-server-demo-0.0.1-SNAPSHOT.jar.bak > /dev/null 2>&1 
mv /data/server-package/http-server-demo-0.0.1-SNAPSHOT.jar /data/server-package/http-server-demo-0.0.1-SNAPSHOT.jar.bak > /dev/null 2>&1 
wget -qP /tmp http://192.168.1.5/root/operation.tl.exmaple.delopy/raw/master/http/http-server-demo-0.0.1-SNAPSHOT.jar
mv /tmp/http-server-demo-0.0.1-SNAPSHOT.jar /data/server-package
sh http2.sh start
```

### -此命令的作用：一秒执行一次‘’内的命令

```
watch -n 1 'ps aux |grep java'
可以查看程序是否重启成功
```
### -日志查看tailf

```
tailf：日志跟踪打印
```