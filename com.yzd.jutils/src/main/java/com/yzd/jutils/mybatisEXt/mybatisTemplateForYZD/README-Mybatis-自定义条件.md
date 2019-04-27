- Mybatis-自定义条件（mapWhere）
-
```
Query_Where_ClauseForMapWhere
@Param("mapWhere")Map<String, Object> mapWhere
名字规则：字段名+“4”+作用
时间：
mapWhere.sendTime4EndTime （结束时间）
mapWhere.sendTime4StartTime （结束时间）
数值：
mapWhere.interfaceType4MinVal（最小值）
mapWhere.interfaceType4MaxVal（最大值）
字符串：
mapWhere.batchNum4LikeAll（全模糊 :like * txt *）
mapWhere.batchNum4LikeRight（右模糊 :like txt*）
mapWhere.batchNum4LikeRight（左模糊:like *txt）
```