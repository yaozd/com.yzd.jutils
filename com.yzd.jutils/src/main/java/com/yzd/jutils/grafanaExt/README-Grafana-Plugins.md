# 当前使用的Grafana的版-6.0

> plugins table
```
table 中使用链接-表格内传值
http://www.baidu.com/?a=$__cell_1
PS:$__cell_1 代表当前行的参考
参考：
Drilldown link in table
https://community.grafana.com/t/drilldown-link-in-table/3484
```
> Grafana 页面传值或是模板传值
```
设置->变量（Variables）-》userId》图表引用方式“$userId”》页面传值的方式是var-userId
PS:变量的类型：Custom
1.$userId
2.var-userId
==========================
&var-userId=131093
http://192.168.1.237:3000/d/Wk_L9pCmz/new-dashboard-copy?orgId=1&var-userId=131093
```