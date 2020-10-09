# maven占位符的使用
> maven占位符默认的是${},也可以自己指定
- [maven 编译替换占位符](https://www.cnblogs.com/Leechg/p/10280911.html)
- [Spring Boot使用Maven打包替换资源文件占位符](https://www.cnblogs.com/funnyboy0128/p/7693834.html)
- [maven占位符的使用](https://www.pianshen.com/article/6687120139/)
- []()


- sample:1
- sample:1
```
关键配置：
<resources>
    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
    </resource>
</resources>
```
## 参数说明：
```
maven占位符默认的是${},也可以自己指定，如下：

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>2.5</version>
    <configuration>
        <useDefaultDelimiters>false</useDefaultDelimiters>
        <delimiters>
        <delimiter>$[*]</delimiter>
        </delimiters>
        <encoding>UTF-8</encoding>
    </configuration>
</plugin>
其中configuration中的设置就是指定占位符使用的方式：   
参数说明：
useDefaultDelimiters    是否使用默认占位符
delimiter   占位符
encoding	占位符中value的编码方式
```