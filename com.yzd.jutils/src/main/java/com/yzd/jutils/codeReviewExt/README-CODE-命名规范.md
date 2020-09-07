## 命名规范参考：
- 对象的动作：Out|In (输出对象|输入对象)
    - XxOutVo|XxInVo
    - XxOutDto|XxInDto
- 示例参考：
```
一、方法类

query+业务对象(inVo: Query+业务对象+InVo!, pageInfo: RequestPageInfo): Query+业务对象+outVo

create+业务对象(inVo: Create+业务对象+InVo!): MsgOutVo

update+业务对象(inVo: Update+业务对象+InVo!): MsgOutVo

delete+业务对象(inVo: Delete+业务对象+InVo!): MsgOutVo

二、字段类

isActive 类型 TINYINT 默认 1（依据业务判断默认值）  注释 是否启用 1启用 2禁用

createBy、updateBy  VARCHAR(40)

createTime 类型 TIMESTAMP  默认 CURRENT_TIMESTAMP

actionTime 类型 TIMESTAMP  默认 CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

isDelete 类型TINYINT  默认 2  注释 是否删除 1删除 2未删除

三、接口&服务类

接口：业务对象+ServiceI

接口实现：业务对象+ServiceImpl
```