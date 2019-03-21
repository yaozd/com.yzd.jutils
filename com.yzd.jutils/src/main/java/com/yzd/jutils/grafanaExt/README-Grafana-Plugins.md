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
> Grafana 时间间隔调整-》$__interval
```
时间间隔调整,可以通过自定义变量，其中“Step count”是把当前时间段切割为多少分的意思。
如：500，则是分为500段，有500个时间指标。平均分段的意思
``` 
> Grafana 页面时间区间参数向子页面模板传值-》$__from 与$__to 
```
/d/aCoc7hCik/test237-userdetail?orgId=1&var-userName=$__cell_1&from=$__from&to=$__to

```
> [plugins ajax](https://grafana.com/plugins/ryantxu-ajax-panel)
```
AJAX Panel for Grafana
https://grafana.com/plugins/ryantxu-ajax-panel
```
> [Plotly-散点图](https://grafana.com/plugins/natel-plotly-panel)
```
Scatter plots and more
https://grafana.com/plugins/natel-plotly-panel
```
> [Pie Chart-饼图](https://grafana.com/plugins/grafana-piechart-panel)
```
https://grafana.com/plugins/grafana-piechart-panel
```
