- 示例:Maven 打包-MD5-压缩 PS:maven-antrun-plugin
- 使用场景：
    ```
    程序发版验证
    ```
```
<build>
        <plugins>
            <plugin>
                <groupId>pl.project13.maven</groupId>
                <artifactId>git-commit-id-plugin</artifactId>
                <version>${git.commit.id.plugin.version}</version>
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
                                <!-- 打包后拷贝到指定目录-->
                                <copy overwrite="true" todir="${target.dir}">
                                    <fileset dir="${project.build.directory}" erroronmissingdir="false">
                                        <include name="${finalName}.jar"/>
                                    </fileset>
                                </copy>
                                <!-- MD5：验证包的完整性-->
                                <checksum file="${target.dir}/${finalName}.jar"
                                          forceOverwrite="yes" algorithm="MD5" fileext=".MD5"></checksum>
                                <!--
                                <tar destfile="${target.dir}/${finalName}.tar">
                                    <zipfileset dir="${target.dir}"/>
                                </tar>
                                -->
                                <!-- 压缩-->
                                <zip destfile="${target.dir}/${finalName}.tar">
                                    <zipfileset dir="${target.dir}"/>
                                </zip>
                            </tasks>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```