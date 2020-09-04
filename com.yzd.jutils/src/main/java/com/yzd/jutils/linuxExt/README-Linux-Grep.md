## grep
- linux命令之grep：过滤注释行和空白行
```
## 复杂方式
cat elasticsearch.yml | grep "^\s*[^# \t].*$"
## 简单方式
cat elasticsearch.yml  | grep -v  ^#  |grep -v ^$
```

- 统计行数
    - [linux统计大文件行数的命令效率大对比](https://www.cnblogs.com/Go-Spurs-Go/p/10383335.html)
```
grep 'BothWayListener' NAME.log |wc -l
```

- [linux根据进程号PID查找启动程序的全路径](https://blog.csdn.net/lsbhjshyn/article/details/18764613)
```
netstat -ntpl
ps -ef|grep 16454
cd /proc/16454
ll
exe
```