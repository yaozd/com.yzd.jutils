###　1.[mysql 快速插入1000万条测试数据方法](https://blog.csdn.net/qq_19524879/article/details/80240754)
>已经测试可用
```
CREATE TABLE `logs1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `logtype` varchar(255) DEFAULT NULL,
  `logurl` varchar(255) DEFAULT NULL,
  `logip` varchar(255) DEFAULT NULL,
  `logdz` varchar(255) DEFAULT NULL,
  `ladduser` varchar(255) DEFAULT NULL,
  `lfadduser` varchar(255) DEFAULT NULL,
  `laddtime` datetime DEFAULT NULL,
  `htmlname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  AUTO_INCREMENT=1811 DEFAULT CHARSET=utf8 COMMENT='日志表';
 
 
-- 创建存储过程
 
DROP PROCEDURE IF EXISTS my_insert;
CREATE PROCEDURE my_insert()
BEGIN
   DECLARE n int DEFAULT 1;
        loopname:LOOP
            INSERT INTO `logs1`(`logtype`,`logurl`,`logip`,`logdz`,`ladduser` ,`lfadduser`,`laddtime`,`htmlname`) VALUES ( 2, '/index', '0:0:0:0:0:0:0:1', null, null, 'null', '2018-05-03 14:02:42', '首页');
            SET n=n+1;
        IF n=10000000 THEN
            LEAVE loopname;
        END IF;
        END LOOP loopname;
END;
 
 
-- 执行存储过程
CALL my_insert();
```
### 使用随机数版
- [mysql生成指定位数的随机数](https://blog.csdn.net/zhou520yue520/article/details/82882994)
- [mysql 快速插入1000万条测试数据方法](https://blog.csdn.net/qq_19524879/article/details/80240754)
```
CREATE TABLE `logs1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `AA` int(11) DEFAULT NULL,
  `BB` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  AUTO_INCREMENT=1811 DEFAULT CHARSET=utf8 COMMENT='日志表';
 
 
-- 创建存储过程
 
DROP PROCEDURE IF EXISTS my_insert;
CREATE PROCEDURE my_insert()
BEGIN
   DECLARE n int DEFAULT 1;
        loopname:LOOP
            INSERT INTO `logs1`(`AA`,`BB`) VALUES ( CEILING(RAND()*9000+1000),CEILING(RAND()*900+100));
            SET n=n+1;
        IF n=10000000 THEN
            LEAVE loopname;
        END IF;
        END LOOP loopname;
END;
 
 
-- 执行存储过程
CALL my_insert();
```