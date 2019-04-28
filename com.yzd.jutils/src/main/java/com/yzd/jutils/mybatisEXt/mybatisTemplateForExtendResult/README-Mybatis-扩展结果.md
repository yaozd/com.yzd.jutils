## Ideal-插件-codehelper.generator --生成代码
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
- Mybatis-批量导入-insertList
-
```
 <insert id="insertList">
   INSERT INTO tb_ifcert_push_log(
   <include refid="all_column"/>
   )VALUES
   <foreach collection="pojos" item="pojo" index="index" separator=",">
       (
       #{pojo.id},
       #{pojo.batchNum},
       #{pojo.interfaceType},
       #{pojo.sendDate},
       #{pojo.sendTime},
       #{pojo.checkCode},
       #{pojo.totalNum},
       #{pojo.pushStatus},
       #{pojo.balanceStatus},
       #{pojo.balanceTime},
       #{pojo.storageStatus},
       #{pojo.gmtCreateTime},
       #{pojo.gmtIsDeleted}
       )
   </foreach>
 </insert>
```