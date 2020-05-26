## Maven-打包非springboot程序
- [非springboot的maven项目打包可执行jar包](https://blog.csdn.net/u014578266/article/details/99630729)
```
在pom.xml中配置
<build>
    <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-shade-plugin</artifactId>
              <version>3.2.1</version>
              <executions>
                  <execution>
                      <phase>package</phase>
                      <goals>
                          <goal>shade</goal>
                      </goals>
                      <configuration>
                          <transformers>
                              <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                                  <mainClass>your.main.Class</mainClass>
                              </transformer>
                          </transformers>
                      </configuration>
                  </execution>
              </executions>
          </plugin>
    </plugins>
</build>
```