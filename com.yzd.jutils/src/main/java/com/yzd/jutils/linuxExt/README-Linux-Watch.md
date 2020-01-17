- watch
```
每隔1s显示连接数的变量：
watch -n 1 -d 'netstat -nt|grep ES|wc -l'

watch -n 1 -d 'curl http://127.0.0.1:9311 |grep "0.0.0.0"'

watch -n 5 -d '> localhost.localdomain-hyperspace-route.log'

watch -n 3 -d 'ls -l'
```