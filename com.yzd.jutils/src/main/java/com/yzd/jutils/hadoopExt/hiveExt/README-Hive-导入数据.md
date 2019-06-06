## Hive不支持Insert插入操作(虽然有insert方法但太慢，本地导入比较快)
```
用jdbc sql方式往hive插入数据的时候,是不是很慢啊? 有什么办法使它能高效一点呢?
Hive原是不支持插入的，插入不如Load data来的爽快，后来加入insert，并不推荐使用
参考：
https://blog.csdn.net/cry970795248/article/details/82693888#comments
```
> PS:hive不支持用insert语句一条一条的进行插入操作，也不支持update操作。数据是以load的方式加载到建立好的表中，数据一旦导入就不可以修改
### 1.Hive-导入数据
- [Hive的insert操作](https://blog.csdn.net/yeruby/article/details/23039009)
- [第十二天 - Hive基本操作 - Hive导入数据、统计数据 - HiveJDBC操作Hive](https://blog.csdn.net/cry970795248/article/details/82668597)
- [一文搞定hive之insert into 和 insert overwrite与数据分区](https://blog.csdn.net/su83362368/article/details/78502542)
- [sqoop从hive到mysql,mysql到hive](https://wenku.baidu.com/view/0dfa662cf8c75fbfc67db2ad.html)
- []()

### 使用insert插入大量数据的个人经验总结
- []()
- []()

#### 本地导入示例：
```
create external table t3(
  user_id     int     comment 'userID',
  user_t     int     comment 'userName',
  user_name     int     comment 'userName'
   )
ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';
--------------
load data local inpath '/data/t1.txt' into table t3;
--------------
t1.txt内容如下：
370984	141127	-1
370985	140201	-1
370986	131857	-1
370987	141143	-1
370988	131261	-1
---------------
测试验证：
SELECT COUNT(*) FROM t3;
SELECT COUNT(*) FROM t3 WHERE user_id>100000
SELECT user_t,COUNT(user_t) FROM t3 GROUP BY user_t;
```
#### 示例：
```
CREATE TABLE test_insert (name VARCHAR(64), age INT, gpa DECIMAL(3, 2)) CLUSTERED BY (age) INTO 2 BUCKETS STORED AS ORC;
insert into table students select * from test_insert;
insert into table test_insert select * from students;
===
SELECT * FROM test_insert
```