## [使用Maven的assembly插件实现自定义打包](https://www.cnblogs.com/sidesky/p/10721638.html)
- [https://github.com/yaozd/assembly-demo](https://github.com/yaozd/assembly-demo)
- [Maven3种打包方式之一maven-assembly-plugin的使用](https://blog.csdn.net/qq_32736999/article/details/93395246)
PS:可参考grpc-benchmark的pom
```
 <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>${maven-assembly-plugin.version}<version>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <!-- 绑定到package生命周期 -->
                        <phase>package</phase>
                        <goals>
                            <!-- 只运行一次 -->
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
                <configuration>
                    <!-- 配置描述符文件 -->
                    <descriptor>src/main/assembly/assembly.xml</descriptor>
                    <!-- 也可以使用Maven预配置的描述符
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs> -->
                </configuration>
            </plugin>
        </plugins>
    </build>
```