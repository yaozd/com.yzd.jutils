### MYSQL
- mysql未知项目数据库优化

```
1.查询mysql->每个表的数据总量
2.找出大表相关的sql语句
3.性能分析与优化
```
- [mysql 查看数据库中所有表的记录数](https://www.cnblogs.com/xzhg/p/6472878.html) 

```
use information_schema;

select table_name,table_rows from tables

where TABLE_SCHEMA = 'testdb'

order by table_rows desc;
```
- [MySQL 查看表结构简单命令](https://www.cnblogs.com/zhangyuhang3/p/6873895.html)

```
一、简单描述表结构，字段类型
desc tabl_name;
显示表结构，字段类型，主键，是否为空等属性，但不显示外键。
例如：desc table_name
==
二、查询表中列的注释信息
select * from information_schema.columns
where table_schema = 'db' #表所在数据库
and table_name = 'tablename' ; #你要查的表
==
三、只查询列名和注释
select column_name, column_comment from information_schema.columns where table_schema ='db' and table_name = 'tablename' ;
==
四、#查看表的注释
select table_name,table_comment from information_schema.tables where table_schema = 'db' and table_name ='tablename'
==
五、查看表生成的DDL
show create table table_name;
```
- Navicat Premium提供的数据同步功能-数据传输

```
数据库->数据传输
```