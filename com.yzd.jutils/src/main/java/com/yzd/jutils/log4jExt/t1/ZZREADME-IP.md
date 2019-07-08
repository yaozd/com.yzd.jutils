##　Logback日志扩展
- [https://github.com/yaozd/com.yzd.logback.extend.root](https://github.com/yaozd/com.yzd.logback.extend.root)

###　通过System变量设置本机IP地址
－　设置 "local-ip" system 变量，给log4j2,logback 配置使用：

### 使用方式
- logback
```
[%property{local-ip}]
[${local-ip}]
<property name="logPattern" value="[ELK=][%property{local-ip}][${local-ip}][%X{TRACE_ID}]-[%d{yyyy-MM-dd HH:mm:ss.SSS}]├%thread┤[%level]-[%logger]- %msg|%n"/>
```
- log4j
```
${sys:local-ip}
pattern: "${sys:local-ip} %d{yyyy-MM-dd HH:mm:ss,SSS}:%4p %t (%F:%L) - %m%n"
```

### 参考：
- [log4j2 记录本机ip](https://blog.csdn.net/ClementAD/article/details/85112215)
- [Java中System.setProperty()用法](https://blog.csdn.net/qq_39781497/article/details/78425668)
- []()