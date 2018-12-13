
###　
- [mvn 打包 maven-replacer-plugin 插件中文乱码问题](https://blog.csdn.net/u014752221/article/details/84985571)
- [maven-replacer-plugin 静态资源版本号解决方案（css/js等）](https://www.jianshu.com/p/a80b9d05d2f7)
- [maven-replacer-plugin 静态资源打包方案js css](https://blog.csdn.net/winy_lm/article/details/78124554)
- [maven打包加时间戳](https://blog.csdn.net/z410970953/article/details/50680603)

示例：byArvin-com.yzd.frame.v2.root已测试可用-20181213-1628
```
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <!--静态资源版本号解决方案-Begin-->
            <!--此插件用于获取当前时区当前时间-->
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>build-helper-maven-plugin</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <id>timestamp-property</id>
                        <goals>
                            <goal>timestamp-property</goal>
                        </goals>
                        <configuration>
                            <name>build.time</name>
                            <pattern>MMddHHmmss</pattern>
                            <timeZone>GMT+8</timeZone>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
            <!-- maven-replacer-plugin -->
            <!--maven-replacer-plugin 静态资源版本号解决方案（css/js等）-->
            <plugin>
                <groupId>com.google.code.maven-replacer-plugin</groupId>
                <artifactId>maven-replacer-plugin</artifactId>
                <version>1.4.1</version>
                <executions>
                    <execution>
                        <phase>prepare-package</phase>
                        <goals>
                            <goal>replace</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <includes>
                        <include>**/classes/velocity/**/*.vm</include>
                        <include>**/classes/templates/**/*.vm</include>
                    </includes>
                    <replacements>
                        <replacement>
                            <token>\.css\"\></token>
                            <value>.css?v=${build.time}\"\></value>
                        </replacement>
                        <replacement>
                            <token>\.css\'\></token>
                            <value>.css?v=${build.time}\'\></value>
                        </replacement>
                        <replacement>
                            <token>\.js\"\></token>
                            <value>.js?v=${build.time}\"\></value>
                        </replacement>
                        <replacement>
                            <token>\.js\'\></token>
                            <value>.js?v=${build.time}\'\></value>
                        </replacement>
                    </replacements>
                    <!--解决mvn 打包 maven-replacer-plugin 插件中文乱码问题-->
                    <encoding>utf-8</encoding>
                </configuration>
            </plugin>
            <!--静态资源版本号解决方案-End-->
        </plugins>
```