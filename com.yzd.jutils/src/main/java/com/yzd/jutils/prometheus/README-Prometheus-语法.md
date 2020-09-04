## [Prometheus查询语法学习总结](https://www.jianshu.com/p/b7174bc11e55)
```
例：http_requests_total{label1="xxx"}
这个查询语句就会返回维度label1为xxx的序列。
tip：过滤语句经常会使用的操作符有：
1. =：精确匹配，等于
2. !=：精确匹配，不等于
3. =~：正则匹配，等于
4. !~：正则匹配，不等于
如：http_requests_total{label1=~"aaa|bbb", label2!="ccc"}

```