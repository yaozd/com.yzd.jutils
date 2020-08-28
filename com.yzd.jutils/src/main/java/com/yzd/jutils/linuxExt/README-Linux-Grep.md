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