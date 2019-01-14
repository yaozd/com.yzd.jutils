
> README-Mybatis打印SQL-（spring boot ）

1-properties格式
```
###############SQL Output start#################
#其中com.yzd.db.account.dao.mapper修改为你自己的java文件的Mapper所在的目录即可
logging.level.com.yzd.db.account.dao.mapper=DEBUG
###############SQL Output end###################

```
2-XML配置格式
```
<logger name="com.yzd.db.account.dao.mapper" additivity="true">
<level>DEBUG</level>
</logger>  
```