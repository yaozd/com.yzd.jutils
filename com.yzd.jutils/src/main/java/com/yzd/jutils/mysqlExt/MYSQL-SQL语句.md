### ���MySql�������ݵ�Excel���֤��ʽ����
```
SELECT ud_true_name as ����,CONCAT("=",ud_id_number) as ���֤�� 
from tmp_table_income_user_1111 as A JOIN tb_user_details as B on A.ind_login_id=B.ud_login_id where ud_is_del=1
------------
Ȼ���ٰѡ�=���滻Ϊ��
```

### ͨ��select��ѯ��������±���������Ϊ��ʱ���ṩ��ѯʹ�ã�
```
Create table tmp_table_fullname(
select ind_bp_id,ud_true_name,ud_phone,concat(ud_true_name,ud_phone)as fullName from tb_income_detail as tid JOIN tb_user_details as tud on tid.ind_login_id=tud.ud_login_id 
where ind_fund_type=1 and ind_money>=1000  )
```
### �����б�Ϊһ��
```
-group_concat(fullName Separator ';')
--
Create table tmp_table_touziren(
select ind_bp_id ,group_concat(fullName Separator ';') as touziren,count(*) as countNum from tmp_table_fullname group by ind_bp_id
)
```
### ʱ�����-��һ������
```
Create table tmp_table_plan(
select bp_id, bp_number,bp_name,bp_true_name,bp_loan_money,bp_rate,bp_interest_st,
date_sub(bp_repay_time,interval 1 day) as '��1����',
date_add(date_sub(bp_repay_time,interval 1 day), interval -3 month) as '��2����',
date_add(date_sub(bp_repay_time,interval 1 day), interval -6 month) as '��3����',
date_add(date_sub(bp_repay_time,interval 1 day), interval -9 month) as '��4����',
bp_status,bp_cycle_type,bp_interest_pay_type   
from tb_bid_plan where  bp_loan_money>=1000  and bp_status=10 and bp_interest_st <'2018-09-01' and bp_is_del=1 
)
```
### �������ֶκϲ�Ϊһ���ֶ�
```
-concat(ud_true_name,ud_phone)as fullName
--
Create table tmp_table_fullname2(
select ind_bp_id,ind_bp_number,ud_true_name,ud_phone,concat(ud_true_name,ud_phone)as fullName from tb_income_detail as tid JOIN tb_user_details as tud on tid.ind_login_id=tud.ud_login_id 
where ind_fund_type=1 and ind_money>=1000)
```