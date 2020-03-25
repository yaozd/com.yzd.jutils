> PowerDesigner中SQL文件、数据库表反向生成PDM
- [PowerDesigner中SQL文件、数据库表反向生成PDM](https://blog.csdn.net/duanchangqing90/article/details/38089557)
    ```
    1.导入前需要先删除navicat生成的头信息
    /*
    Navicat MySQL Data Transfer
    
    Date: 2019-09-16 18:10:07
    */
    
    SET FOREIGN_KEY_CHECKS=0;
    
    -- ----------------------------
    -- Table structure for container_config
    -- ----------------------------
    ```
- [PowerDesigner16.5快速入门显示，注释comment配置方法，以及创建sql文件过程中需要注意的一些问题](https://blog.csdn.net/qq496013218/article/details/70267611)

> 方案一
- 通过Excel生成PowerDesigner表结构设计
- [通过Excel生成PowerDesigner表结构设计](https://www.cnblogs.com/xianhan/p/7364324.html)
- 附：脚本文件
```

'开始
Option Explicit
 
Dim mdl ' the current model
Set mdl = ActiveModel
If (mdl Is Nothing) Then
    MsgBox "There is no Active Model"
End If
 
Dim HaveExcel
Dim RQ
RQ = vbYes 'MsgBox("Is  Excel Installed on your machine ?", vbYesNo + vbInformation, "Confirmation")
If RQ = vbYes Then
    HaveExcel = True
    ' Open & Create  Excel Document
    Dim x1 '
    Set x1 = CreateObject("Excel.Application")
    x1.Workbooks.Open "D:\Temp.xlsx" '指定 excel文档路径
Else
    HaveExcel = False
End If
 
a x1, mdl
 
sub a(x1, mdl)
dim rwIndex 
dim tableName
dim colname
dim table
dim col
dim count
dim sheetIndex 
on error Resume Next
For sheetIndex = 1 To 200 '指定要遍历的 Sheet
    x1.Workbooks(1).Worksheets(sheetIndex).Activate '指定要打开的sheet名称
    With x1.Workbooks(1).Worksheets(sheetIndex)
    If .Cells(1, 1).Value = "" Then
        Exit For
    End If
    set table = mdl.Tables.CreateNew '创建一个 表实体
    table.Name = .Cells(1, 2).Value '指定 表名，如果在 Excel文档里有，也可以 .Cells(rwIndex, 3).Value 这样指定
    table.Code = .Cells(1, 4).Value '指定 表名
    count = count + 1
 
    For rwIndex = 3 To 1000 '指定要遍历的 Excel行标 由于第2行是列的 表头， 从第3行开始
         
            If .Cells(rwIndex, 1).Value = "" Then
            Exit For
            End If
 
            set col = table.Columns.CreateNew '创建一列/字段
            'MsgBox .Cells(rwIndex, 1).Value, vbOK + vbInformation, "列"
            If .Cells(rwIndex, 1).Value = "" Then
                col.Name = .Cells(rwIndex, 2).Value '指定列名-----如果第1列(Name)为空，则显示第2列的Code
            Else
                col.Name = .Cells(rwIndex, 1).Value
            End If
            'MsgBox col.Name, vbOK + vbInformation, "列"
            col.Code = .Cells(rwIndex, 2).Value '指定列名-------第2列是Code
            col.DataType = .Cells(rwIndex, 3).Value '指定列数据类型-----第3列是类型
            col.Comment = .Cells(rwIndex, 5).Value '指定列说明-------第5列是列说明
            If .Cells(rwIndex, 4).Value = "否" Then
                col.Mandatory = true '指定列是否可空 true 为不可空 ------第4列指定列是否允许为空
            End If
            If rwIndex = 3 Then
                col.Primary = true '指定主键-------第3行是主键列
            End If
         
    Next
    MsgBox "生成数据 表结构共计 " + CStr(count), vbOK + vbInformation, " 表"
    End With
Next
Exit Sub
End sub

```
> 方案二
- 通过原生SQL直接创建，然后再逆向工程到PowerDesigner中
```
CREATE TABLE `tb_address` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `a_login_id` bigint(11) DEFAULT NULL COMMENT '用户登录id',
  `a_truename` varchar(100) DEFAULT NULL COMMENT '收货人姓名',
  `a_address` varchar(300) DEFAULT NULL COMMENT '收货地址',
  `a_tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `a_is_del` int(2) DEFAULT NULL COMMENT '1-有效，2-无效',
  `a_createtime` datetime DEFAULT NULL,
  `a_updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=348 DEFAULT CHARSET=utf8 COMMENT='收货地址表';
```