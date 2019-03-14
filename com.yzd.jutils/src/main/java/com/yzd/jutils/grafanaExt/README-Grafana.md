# 当前使用的Grafana的版-6.0
> 用户行为跟踪
```
河边走分析：对产品详情页面的访问统计
视图模板：template>tongji-详细访问-[河边走].txt
PS:可以把视图模板导入到grafana中
```

SQL示例:
```
SELECT SUM("count") FROM "productdetail_metric" WHERE time >= 1551801600000ms and time <= 1551887999999ms GROUP BY "userName" limit 500
```
> 用户访问-每日产品列表
```
 SELECT resourceId,count  FROM (SELECT COUNT(count) FROM "productdetail_metric" WHERE $timeFilter AND userName='$userName'  GROUP BY resourceId , time(1d) )
```
1. 产品详情-用户访问率
```
PS:
Type:Table
SQL:SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter GROUP BY "userName"
```
2. 访问总数
```
PS:
Type:Singlestat
SQL:SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter fill(null)
```
3. 产品详情-访问趋势
```
PS:
Type:Graph
SQL:
SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter GROUP BY time($__interval) fill(null)
SELECT SUM("count") FROM "productdetail_metric" WHERE $timeFilter GROUP BY time(5m) fill(null)
```
4. 独立用户数
```
PS:
SQL:SELECT  COUNT(DISTINCT(userId)) FROM "productdetail_metric" WHERE $timeFilter fill(null)

```
