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
