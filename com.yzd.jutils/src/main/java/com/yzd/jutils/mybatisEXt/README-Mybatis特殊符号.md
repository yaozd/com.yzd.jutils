> [MyBatis 大于等于、小于等于的写法](https://blog.csdn.net/jeikerxiao/article/details/79727536 )
```
标准写法如下：
--------------------- 
第一种写法：
原符号	<	<=	>	>=	&	'	"
替换符号	&lt;	&lt;=	&gt;	&gt;=	&amp;	&apos;	&quot;
SQL示例如下：

create_time &gt;= #{startTime} 
and  create_time &lt;= #{endTime}
--------------------- 
第二种写法：
大于等于	<![CDATA[ >= ]]>
小于等于	<![CDATA[ <= ]]>
SQL示例如下：

create_time <![CDATA[ >= ]]> #{startTime} 
and  create_time <![CDATA[ <= ]]> #{endTime}
--------------------- 
原文：https://blog.csdn.net/jeikerxiao/article/details/79727536 

```