- 访问地址
    - [http://localhost:8080/actuator](http://localhost:8080/actuator)
- [Spring boot 2.0 Actuator 的健康检查](https://www.jianshu.com/p/1aadc4c85f51)
- [Spring Boot Actuator:健康检查、审计、统计和监控](https://www.jianshu.com/p/d5943e303a1f)

- Actuator 添加 Git 和 Build 信息
    - [Maven插件之git-commit-id-plugin](https://blog.csdn.net/wangjunjun2008/article/details/10526151)
    - 参考：hyperspace-console
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
    =========================================================
    <plugins>
        <plugin>
            <groupId>pl.project13.maven</groupId>
            <artifactId>git-commit-id-plugin</artifactId>
            <version>4.0.0</version>
            <executions>
                <execution>
                    <goals>
                        <goal>revision</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <!--,构建过程中,是否打印详细信息;-->
                <verbose>false</verbose>
                <generateGitPropertiesFile>true</generateGitPropertiesFile>
                <injectAllReactorProjects>true</injectAllReactorProjects>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${springboot.version}</version>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                        <goal>build-info</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <finalName>${finalName}</finalName>
            </configuration>
        </plugin>
        <!--打包后拷贝到指定目录-->
        <plugin>
            <artifactId>maven-antrun-plugin</artifactId>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                        <goal>run</goal>
                    </goals>
                    <configuration>
                        <tasks>
                            <mkdir dir="${target.dir}"/>
                            <copy overwrite="true" todir="${target.dir}">
                                <fileset dir="${project.build.directory}" erroronmissingdir="false">
                                    <include name="${finalName}.jar"/>
                                </fileset>
                            </copy>
                        </tasks>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
    ```