### Mybatis的示例-(Mybatis插件生成参考)
- Temp-Mybatis-Generator-Root-2019-01-31-1056-备份-[shop-generator-by小龙推荐].zip(推荐参考byArvin-Mybatis插件生成参考)
```
下载地址：
百度云-》软件开发java-》J-M-Mybatis的示例
```
### mybatis代码生成模板-POM.XML的配置
#### 参考代码
- [com.yzd.transaction.root](https://github.com/yaozd/com.yzd.transaction.root)
> com.yzd.transaction.root>>com.yzd.transaction.mybatis.root
- [com.yzd.frame.v2.root](https://github.com/yaozd/com.yzd.frame.v2.root)
> com.yzd.frame.v2.root>>Temp-Mybatis-Generator-Root

> pom.xml配置
```
<!--mybatis代码生成需要的插件-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.2</version>
                <configuration>
                    <verbose>true</verbose>
                    <overwrite>true</overwrite>
                </configuration>
                <dependencies>
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.30</version>
                    </dependency>
                    <!-- 增加mybatis 通用mapper -->
                    <dependency>
                        <groupId>tk.mybatis</groupId>
                        <artifactId>mapper</artifactId>
                        <version>3.4.0</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>
```
