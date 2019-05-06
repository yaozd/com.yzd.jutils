### 查询mysql数据库中所有表名
```
1.所有数据库
SHOW DATABASES
2.数据库中所有表名
select table_name from information_schema.tables where table_schema='当前数据库'
eg:
select table_name from information_schema.tables where table_schema='wyait'

```

### mysql中dual表
```
select 1 from dual
```
### MySQL create database 指定utf-8编码
```
如下脚本创建数据库yourdbname，并制定默认的字符集是utf8。
CREATE DATABASE IF NOT EXISTS yourdbname DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
如果要创建默认gbk字符集的数据库可以用下面的sql:
create database yourdb DEFAULT CHARACTER SET gbk COLLATE gbk_chinese_ci;
--------------------- 
```

### [mysql 按照月份统计](https://blog.csdn.net/u010918487/article/details/80285025)
```
select ud_create_time AS "time",count(*) AS total  FROM tb_user_details WHERE  ud_is_del=1 GROUP BY DATE_FORMAT(ud_create_time, '%Y-%m')
```
### [MySql计算百分比](https://blog.csdn.net/wmin0909/article/details/7865037)
```
SELECT total/total_us_card from (SELECT count(1) as total from tb_user_details where ud_is_del=1) T1,(SELECT count(1) as total_us_card from tb_user_details where ud_bank_card_no is not null and ud_is_del=1)T2;
```

### 判断用户当日是否签到

```
执行速度相差10倍
1.执行时间是3毫秒
select sr_id from tb_sign_record where sr_login_id=139866 ORDER BY sr_id DESC LIMIT 1
2.执行时间是48毫秒
select sr_id, sr_login_id, sr_count, sr_gold_count, sr_create_time, sr_update_time, sr_voucher, sr_medal, sr_type, sr_ii_id, sr_gold from tb_sign_record 
where sr_login_id=139866 ORDER BY sr_create_time DESC LIMIT 0,1
3.执行时间是48毫秒
select sr_id, sr_login_id, sr_count, sr_gold_count, sr_create_time, sr_update_time, sr_voucher, sr_medal, sr_type, sr_ii_id, sr_gold from tb_sign_record 
where sr_login_id=139866 ORDER BY sr_create_time DESC LIMIT 0,1

```

### mysql身份证信息脱敏

```
MySQL对身份证号、手机号等敏感数据脱敏教程
https://www.2cto.com/database/201802/719575.html

-- 身份证脱敏-1脱敏后4位；2脱敏中间11位
SELECT ud_id_number,INSERT(ud_id_number, 15, 4, '****'),INSERT(ud_id_number, 4, 11, '****') from tb_user_details LIMIT 10

-- 姓名脱敏
SELECT ud_true_name,INSERT(ud_true_name, 2, 4, '某某') from tb_user_details LIMIT 10
```

### mysql生成一个连续的时间序列-按月或者日

```
【(select @i:= -1) t 必须加上】
select date_add('2021-01-01',interval @i:=@i+1 day) as date 
from ( select 1 union all select 1 union all select 1 union all select 1) as tmp,(select @i:= -1) t
---------
【(select @i:= -1) t 必须加上】

select date_add('2016-04-01',interval @i:=@i+1 MONTH) as date 
from ( select 1 union all select 1 union all select 1 union all select 1
) as tmp,(select @i:= -1) t

```

### [mysql语句里时间格式化](https://www.cnblogs.com/fan-lp/p/5659921.html)

```
SELECT DATE_FORMAT(now(),'%Y%m')

## 201811

SELECT DATE_FORMAT(now(),'%Y-%m-%d')

##2018-11-12
```

### mysql中通过身份证计算年纪与性别

```
SELECT 
person_invest_no,substring(person_invest_no,7,8) birth,
year(now())- year(substring(person_invest_no,7,8)) age ,
IF(LEFT(SUBSTRING(person_invest_no,17),1)%2=1,"男","女") AS sex
from tmp_table_person_nopay_money_type_1111

----

SELECT person_invest_no,substring(person_invest_no,7,8) birth,year(now())- year(substring(person_invest_no,7,8)) age from tmp_table_person_nopay_money_type_1111
```

### 解决MySql导出数据到Excel身份证格式错误

```
推荐方法：测试可行
SELECT ud_true_name,CONCAT("\t",ud_id_number) as idnumber  from tb_user_details LIMIT 290

-------------------------------------
SELECT ud_true_name as 姓名,CONCAT("=",ud_id_number) as 身份证号 
from tmp_table_income_user_1111 as A JOIN tb_user_details as B on A.ind_login_id=B.ud_login_id where ud_is_del=1
------------
然后再把“=”替换为空
```

### 通过select查询结果创建新表（场景：作为临时表提供查询使用）
```
Create table tmp_table_fullname(
select ind_bp_id,ud_true_name,ud_phone,concat(ud_true_name,ud_phone)as fullName from tb_income_detail as tid JOIN tb_user_details as tud on tid.ind_login_id=tud.ud_login_id 
where ind_fund_type=1 and ind_money>=1000  )
```
### 将多行变为一行
```
-group_concat(fullName Separator ';')
--
Create table tmp_table_touziren(
select ind_bp_id ,group_concat(fullName Separator ';') as touziren,count(*) as countNum from tmp_table_fullname group by ind_bp_id
)
```
### 时间操作-减一个季度
```
Create table tmp_table_plan(
select bp_id, bp_number,bp_name,bp_true_name,bp_loan_money,bp_rate,bp_interest_st,
date_sub(bp_repay_time,interval 1 day) as '第1季度',
date_add(date_sub(bp_repay_time,interval 1 day), interval -3 month) as '第2季度',
date_add(date_sub(bp_repay_time,interval 1 day), interval -6 month) as '第3季度',
date_add(date_sub(bp_repay_time,interval 1 day), interval -9 month) as '第4季度',
bp_status,bp_cycle_type,bp_interest_pay_type   
from tb_bid_plan where  bp_loan_money>=1000  and bp_status=10 and bp_interest_st <'2018-09-01' and bp_is_del=1 
)
```
### 将两个字段合并为一个字段
```
-concat(ud_true_name,ud_phone)as fullName
--
Create table tmp_table_fullname2(
select ind_bp_id,ind_bp_number,ud_true_name,ud_phone,concat(ud_true_name,ud_phone)as fullName from tb_income_detail as tid JOIN tb_user_details as tud on tid.ind_login_id=tud.ud_login_id 
where ind_fund_type=1 and ind_money>=1000)
```