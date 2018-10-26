### [logback不同业务的日志打印到不同文件](https://blog.csdn.net/mggwct/article/details/77718122)

```
版权声明：本文为博主原创文章，未经博主允许不得转载。	https://blog.csdn.net/mggwct/article/details/77718122
在业务逻辑较为多的系统中，为了能快速的排查线上的问题和清楚的查询各个业务的日志信息，往往需要对不同业务线的日志进行分开记录： 
比如现在系统中有对小金库用户发放奖励和白条用户进行发放奖励。

logback.xml配置文件如下：

<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder charset="UTF-8">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] %level [%thread] %file:%line - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="SYS_INFO" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${catalina.base}/program/info.log</File>
        <append>true</append>
        <!--过滤器,只打INFO级别的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${catalina.base}/program/info.log.%d</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>

        <encoder charset="UTF-8">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] %level [%thread] %file:%line - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="SYS_ERROR" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${catalina.base}/program/error.log</File>
        <append>true</append>
        <!--过滤器,只打ERROR级别的日志-->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${catalina.base}/program/error.log.%d</fileNamePattern>
            <maxHistory>12</maxHistory>
        </rollingPolicy>

        <encoder charset="UTF-8">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] %level [%thread] %file:%line - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!--不同业务逻辑的日志打印到不同文件-->
    <appender name="baitiaoUserAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${catalina.base}/program/bt.log</File>
        <append>true</append>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${catalina.base}/program/bt.log.%d</fileNamePattern>
            <maxHistory>12</maxHistory>
        </rollingPolicy>
        <encoder charset="UTF-8">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] %level [%thread] %file:%line - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="xjkUserAppender" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <File>${catalina.base}/program/xjk.log</File>
        <append>true</append>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>INFO</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${catalina.base}/program/xjk.log.%d</fileNamePattern>
            <maxHistory>12</maxHistory>
        </rollingPolicy>
        <encoder charset="UTF-8">
            <pattern>[%d{yyyy-MM-dd HH:mm:ss.SSS}] %level [%thread] %file:%line - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <!-- 不同的业务逻辑日志打印到指定文件夹-->
    <logger name="baitiaoUser" additivity="false" level="INFO">
        <appender-ref ref="baitiaoUserAppender"/>
    </logger>
    <logger name="xjkUser" additivity="false" level="INFO">
        <appender-ref ref="xjkUserAppender"/>
    </logger>

    <logger name="com.act.award" level="INFO"/>
    <logger name="com.act.award" level="DEBUG"/>

    <!--info和error分开打印-->
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="SYS_INFO"/>
        <appender-ref ref="SYS_ERROR"/>
    </root>

</configuration>

代码：

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring/application.xml")
public class MainTest {
    Logger XJK_USER_LOG = LoggerUtils.Logger(LogFileName.XJK_USER);
    Logger BAITIAO_USER_LOG = LoggerUtils.Logger(LogFileName.BAITIAO_USER);

    @Test
    public void testGetBusinessAccount() throws Exception {
        XJK_USER_LOG.info("小金库用户进来了...");
        BAITIAO_USER_LOG.info("白条用户进来了...");
    }

}

public enum LogFileName {

    //配置到logback.xml中的logger name="vipUser"
    XJK_USER("xjkUser"),
    BAITIAO_USER("baitiaoUser");

    private String logFileName;

    LogFileName(String fileName) {
        this.logFileName = fileName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public static LogFileName getAwardTypeEnum(String value) {
        LogFileName[] arr = values();
        for (LogFileName item : arr) {
            if (null != item && StringUtils.isNotBlank(item.logFileName)) {
                return item;
            }
        }
        return null;
    }
}

public class LoggerUtils {
    public static <T> Logger Logger(Class<T> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 打印到指定的文件下
     *
     * @param desc 日志文件名称
     * @return
     */
    public static Logger Logger(LogFileName desc) {
        return LoggerFactory.getLogger(desc.getLogFileName());
    }
}

日志文件形式：

 
bt.log内容： 
INFO [main] MainTest.java:23 - 白条用户进来了… 
xjk.log内容： 
INFO [main] MainTest.java:22 - 小金库用户进来了…
--------------------- 
作者：jd_guojiangjiang 
来源：CSDN 
原文：https://blog.csdn.net/mggwct/article/details/77718122 
版权声明：本文为博主原创文章，转载请附上博文链接！
```