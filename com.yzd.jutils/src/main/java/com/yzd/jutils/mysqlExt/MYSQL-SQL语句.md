### 解决MySql导出数据到Excel身份证格式错误
```
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