### Java执行linux命令
- [SSH-2实现java连接远程服务器并执行脚本命令](https://blog.csdn.net/chuanxincui/article/details/80916205)
- 使用场景
    ```
    T1:对Linux进行集群管理
    ```

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
### -[大文件日志查看-less](https://www.cnblogs.com/aijianshi/p/5750911.html)
```
less test2.log
1.全屏导航
ctrl + F - 向前移动一屏
ctrl + B - 向后移动一屏
ctrl + D - 向前移动半屏
ctrl + U - 向后移动半屏
2.单行导航
j - 向前移动一行
k - 向后移动一行
3.其它导航
G - 移动到最后一行
g - 移动到第一行
q / ZZ - 退出 less 命令
```
- [expect简介和使用例子](https://www.cnblogs.com/iops/p/expect-tutorial-and-example.html)
```
#!/usr/bin/expect
set ip XX.XX.XX.XX
set timeout 40
spawn ssh -p XX XX@$ip
send "/bin/bash /home/reload/reload_schedule.sh\r"
expect "]*"
send "exit\r"
expect eof
```