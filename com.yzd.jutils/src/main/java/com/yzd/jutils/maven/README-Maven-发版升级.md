##　Maven发版升级-release
- 常用命令
    ```
    mvn release:prepare -Darguments="-DskipTests"
    mvn release:perform -Darguments="-DskipTests"
    ```
- 参考
    - [maven release 忽略测试](https://blog.csdn.net/iteye_16572/article/details/82653379)
    ```
    mvn release:prepare -Darguments="-DskipTests"
    mvn release:perform -Darguments="-DskipTests"
    ————————————————
    <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-release-plugin</artifactId>
          <configuration>
            <arguments>-DskipTests</arguments>
          </configuration>
    </plugin>
    ```
    - []()
    - []()
    - []()