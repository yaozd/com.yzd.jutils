
> 配置属性列表
- [DruidDataSource配置属性列表](https://github.com/alibaba/druid/wiki/DruidDataSource配置属性列表)-阿里官方


> druid连接池带来的坑
- [使用druid连接池带来的坑testOnBorrow=false](https://blog.csdn.net/lx348321409/article/details/76095751/)

> 数据库连接池Druid使用总结(结合各位大牛总结的)
- [数据库连接池Druid使用总结(结合各位大牛总结的)](https://blog.csdn.net/shawemou/article/details/81069363)
```
<!-- 加载配置属性文件 -->
<context:property-placeholder ignore-unresolvable="true" location="classpath:cfg.properties" />

<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource" init-method="init" destroy-method="close"> 
    <!-- 数据源驱动类可不写，Druid默认会自动根据URL识别DriverClass -->
    <property name="driverClassName" value="${jdbc.driver}" />

    <!-- 基本属性 url、user、password -->
    <property name="url" value="${jdbc.url}" />
    <property name="username" value="${jdbc.username}" />
    <property name="password" value="${jdbc.password}" />

    <!-- 配置初始化大小、最小、最大 -->
    <property name="initialSize" value="${jdbc.pool.init}" />
    <property name="minIdle" value="${jdbc.pool.minIdle}" /> 
    <property name="maxActive" value="${jdbc.pool.maxActive}" />

    <!-- 配置获取连接等待超时的时间 -->
    <property name="maxWait" value="60000" />

    <!-- 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 -->
    <property name="timeBetweenEvictionRunsMillis" value="60000" />

    <!-- 配置一个连接在池中最小生存的时间，单位是毫秒 -->
    <property name="minEvictableIdleTimeMillis" value="300000" />

    <property name="validationQuery" value="${jdbc.testSql}" />
    <property name="testWhileIdle" value="true" />
    <property name="testOnBorrow" value="false" />
    <property name="testOnReturn" value="false" />

    <!-- 打开PSCache，并且指定每个连接上PSCache的大小（Oracle使用）
    <property name="poolPreparedStatements" value="true" />
    <property name="maxPoolPreparedStatementPerConnectionSize" value="20" /> -->

    <!-- 配置监控统计拦截的filters -->
    <property name="filters" value="stat" /> 
</bean>
--------------------- 
jdbc.type=mysql
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8
jdbc.username=root
jdbc.password=root

#pool settings
jdbc.pool.init=1
jdbc.pool.minIdle=3
jdbc.pool.maxActive=20

#jdbc.testSql=SELECT 1
jdbc.testSql=SELECT 1
--------------------- 

```
- []()
- []()
