#### [SpringBoot中的日志配置，多环境日志配置](https://my.oschina.net/u/1456911/blog/822392)
#### [logback为单独的包配置日志输出文件](https://blog.csdn.net/cgyf520/article/details/52870759?utm_source=blogxgwz1)


###log日志输入，在生产环境禁止使用console控制台输出，因为控制台输出使 log4j2 狂打日志导致 Netty 的 NIO 线程阻塞

```
为什么用Logback，这个建议大家上网搜一下，用log4j的建议更换了，此处的其实用的是sl4j日志，Logback只是他的实现，Logback性能比log4j高很多。

#Logback配置 SpringBoot本身就集成了日志记录组件，所以这节就没有日志依赖了。本来是要写Druid集成及监控的，突然发现前面没有写日志，就这样补上去。

#第一种方式，配置文件配置 SpringBoot默认就配置了配置文件，简单监控及日志输出配置也很简单，增加如下的配置就可以了，file指定日志记录地址及名称，level是监控级别，不是很重要的项目这样子就OK了。这写法无法显示sql，如果用jpa有单独配置可以显示，mybatis不能显示（企业级项目博主不推荐这写法）

logging:
  file: ./application.log
  level: debug
#第二种方式，logback配置（推荐） SpringBoot官方介绍，可以在resource下创建一个logback.xml文件，程序会自动加载，同时官方建议这个xml命名为logback-spring.xml。

当logback-spring.xml与配置文件中配置同时存在的时候，以.xml配置为准，配置文件无效。配置内容如下代码，博主写前logback的版本1.1.7，这个版本有个小bug在1.1.8才能修复，配置文件中写。

<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <logger name="org.springframework.web" level="INFO" />
	<!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->  
    <property name="LOG_PATH" value="D://" />  
	<!-- 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE"  class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_PATH}/TestWeb.log.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--日志文件保留天数-->
            <maxHistory>15</maxHistory>
            <!-- 当前版本bug，需要1.1.8才修复 -->
            <totalSizeCap>30GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>
	<!-- 异步记录文件 -->
    <appender name="asyncFileAppender" class="ch.qos.logback.classic.AsyncAppender">
        <discardingThreshold>0</discardingThreshold>
        <queueSize>500</queueSize>
        <appender-ref ref="FILE" />
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="asyncFileAppender" />
    </root>
    <!-- 打印sql -->
    <logger name="com.dengzy.kuj.mybatis.dao" level="DEBUG"></logger>
</configuration>
#多环境下的logback.xml配置 众所周知，SpringBoot可以在不同的配置环境下使用不同的配置文件，比如，你有application.yml,application-dev.yml,application-junit.yml,application-pro.yml，分别是公共配置，开发环境配置，单元测试环境配置，生产环境配置，切换环境只需要在application.yml中激活那个配置文件 ，如下

spring:
  profiles:
    active: dev #切换不同环境的配置信息
  output:
    ansi:
      enabled: detect #多彩输出日志，如果你的编辑器支持的话
  datasource:
    url: jdbc:mysxxxxx
	username: xxx
	。。。。
在切换不同配置文件下我们需要使用不同的日志记录，比如开发的只需要打印控制台，生产的写文件和控制台，单元测试之写文件等等策略，那上面的配置文件就需要改一下，如下

<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <logger name="org.springframework.web" level="INFO" />
    <appender name="consoleAppender" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE"  class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_PATH}/TestWeb.log.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--日志文件保留天数-->
            <maxHistory>15</maxHistory>
            <!-- 当前版本bug，需要1.1.8才修复 -->
            <totalSizeCap>30GB</totalSizeCap>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>10MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <appender name="asyncFileAppender" class="ch.qos.logback.classic.AsyncAppender">
        <discardingThreshold>0</discardingThreshold>
        <queueSize>500</queueSize>
        <appender-ref ref="FILE" />
    </appender>
    <springProfile name="dev">
        <root level="INFO">
            <appender-ref ref="consoleAppender" />
        </root>
    </springProfile>
    <springProfile name="junit">
        <root level="INFO">
            <appender-ref ref="consoleAppender" />
            <appender-ref ref="asyncFileAppender" />
        </root>
    </springProfile>
    <springProfile name="test">
        <root level="INFO">
            <appender-ref ref="asyncFileAppender" />
        </root>
    </springProfile>
    <springProfile name="pro">
        <root level="INFO">
            <appender-ref ref="asyncFileAppender" />
        </root>
    </springProfile>
    <!--<root level="INFO">
        <appender-ref ref="STDOUT" />
    <appender-ref ref="asyncFileAppender" />
</root>-->
    <!-- 打印sql -->
    <logger name="com.dengzy.kuj.mybatis.dao" level="DEBUG"></logger>
</configuration>
好了，至此配文件配置完毕。

2017-01-10 17:57:07.794 [http-nio-8080-exec-1] INFO  com.dengzy.kuj.mybatis.controller.UserCtrl - 日志测试啦啦啦
2017-01-10 17:57:08.090 [http-nio-8080-exec-1] WARN  org.apache.tomcat.jdbc.pool.ConnectionPool - maxIdle is larger than maxActive, setting maxIdle to: 20
2017-01-10 17:57:09.125 [http-nio-8080-exec-1] DEBUG c.d.kuj.mybatis.dao.UserMapper.queryUserInfoByName - ==>  Preparing: select * from user WHERE name = ? 
2017-01-10 17:57:09.231 [http-nio-8080-exec-1] DEBUG c.d.kuj.mybatis.dao.UserMapper.queryUserInfoByName - ==> Parameters: user(String)
2017-01-10 17:57:09.432 [http-nio-8080-exec-1] DEBUG c.d.kuj.mybatis.dao.UserMapper.queryUserInfoByName - <==      Total: 4
```