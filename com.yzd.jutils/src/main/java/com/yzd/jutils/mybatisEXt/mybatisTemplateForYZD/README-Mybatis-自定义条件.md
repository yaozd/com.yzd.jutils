- Mybatis-自定义条件（mapWhere）
-
```
Query_Where_ClauseForMapWhere
@Param("mapWhere")Map<String, Object> mapWhere
名字规则：字段名+“4”+作用
时间：
mapWhere.sendTime4EndTime （结束时间）
mapWhere.sendTime4StartTime （结束时间）
数值：
mapWhere.interfaceType4MinVal（最小值）
mapWhere.interfaceType4MaxVal（最大值）
mapWhere.interfaceType4Sum（求合）--group by的情况
mapWhere.interfaceType4Count（数量）--group by的情况
字符串：
mapWhere.batchNum4LikeAll（全模糊 :like * txt *）
mapWhere.batchNum4LikeRight（右模糊 :like txt*）
mapWhere.batchNum4LikeLeft（左模糊:like *txt）
LIKE CONCAT('%',#{mapWhere.batchNum4LikeAll},'%' )
LIKE CONCAT(#{mapWhere.batchNum4LikeRight},'%' )
LIKE CONCAT('%',#{mapWhere.batchNum4LikeLeft})
```
- XML脚本
-
```
  <sql id="Page_Where_Clause">
    <if test="pageWhere != null">limit #{pageWhere.begin},#{pageWhere.pageSize}</if>
  </sql>
  <sql id="Query_Where_ClauseForPojo" >
    <where>
      <include refid="Query_Where_Clause" />
    </where>
  </sql>
  <sql id="Query_Where_ClauseForMapWhere" >
    <where>
      <include refid="Query_Where_Clause" />
      <if test="mapWhere != null" >
        <if test="mapWhere.sendTime4EndTime != null"> AND send_time &lt; #{mapWhere.sendTime4EndTime} </if>
      </if>
    </where>
  </sql>
    <select id="selectListForPage" resultMap="BaseResultMap">
      SELECT <include refid="Base_Column_List"/>
      FROM tb_ifcert_push_log
      <include refid="Query_Where_ClauseForMapWhere" />
      ORDER BY id
      <include refid="Page_Where_Clause" />
    </select>
```