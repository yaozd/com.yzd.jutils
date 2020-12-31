- nohup
```
nohup 命令
用途：不挂断地运行命令
语法：nohup Command [ Arg … ] [　& ]
示例：
nohup java -jar BiuBiuBiu.jar >output 2>&1 &
一般不需要查看日志的就直接丢掉丢掉
nohup java -jar BiuBiuBiu.jar >/dev/null 2>&1 &
nohup ./3-all-test.sh >/dev/null 2>&1 &
//
nohup ./bin/pika -c ./conf/pika.conf >> output-pika.log 2>&1 &

nohup /usr/local/node/bin/node /www/im/chat.js >> /usr/local/node/output.log 2>&1 &
```