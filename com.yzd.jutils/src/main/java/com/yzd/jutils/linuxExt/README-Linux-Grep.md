## grep
- linux命令之grep：过滤注释行和空白行
```
## 复杂方式
cat elasticsearch.yml | grep "^\s*[^# \t].*$"
## 简单方式
cat elasticsearch.yml  | grep -v  ^#  |grep -v ^$
```