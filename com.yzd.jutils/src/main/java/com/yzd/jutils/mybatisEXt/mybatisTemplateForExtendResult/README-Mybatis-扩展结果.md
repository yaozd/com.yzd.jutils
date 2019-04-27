## 使用场景：返回结果超出现有字段
- Mybatis-扩展结果
-
```
扩展结果-extends="BaseResultMap"
<resultMap id="ResultMapWithBLOBs" type="entity.ifcert.TbIfcertPushLogWithBLOBs" extends="BaseResultMap" >
    <result column="request_msg" property="requestMsg" jdbcType="LONGVARCHAR" />
    <result column="response_msg" property="responseMsg" jdbcType="LONGVARCHAR" />
</resultMap>
```