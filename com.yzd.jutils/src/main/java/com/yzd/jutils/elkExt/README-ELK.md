
### logback-logPattern-2018-11-26

```
1.日志模板
[ELK=][%d{yyyy-MM-dd HH:mm:ss.SSS}]├%thread┤[%level]-[%logger]- %msg|%n
2.日志数据示例
[ELK=][2018-11-26 14:56:58.919]├Main┤[INFO]-[org.springframework.jmx.export.annotation.AnnotationMBeanExporter]- Registering beans for JMX exposure on startup|
3.正则-数据提取-filebeat
multiline.pattern: '^\[ELK=]'
4.正则-数据提取-logstash
\[ELK=]\[(?<createTime>[^]]*)]├[^┤]+┤\[(?<level>[^]]*)]-\[(?<logger>[^]]*)][\s\S]*
```

### GenerateBigLog
```
 * 生成1亿数据，用于ES框架搭建使用
 * 日志生成在当前目录下
 百云度->软件开发-java->J-B-BigLog->com.yzd.generate.big.log-生成1亿测试数据-ES框架测试.zip
```
