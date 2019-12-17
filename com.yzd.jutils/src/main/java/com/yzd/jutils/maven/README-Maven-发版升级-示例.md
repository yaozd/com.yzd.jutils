## 通过maven-release-plugin进行打包发版打标签
- 参考项目：hyperspace-test 已经测试通过byArvin
- plugin
    ```
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-release-plugin</artifactId>
        <configuration>
            <arguments>-DskipTests</arguments>
            <tagNameFormat>V@{project.version}</tagNameFormat>
        </configuration>
    </plugin>
    ```
- scm
    ```
    <scm>
    	<developerConnection>scm:git:http://git.spt.com/team/supply.git</developerConnection>
    	<url>http://git.spt.com/team/supply.git</url>
    </scm>
    ``` 
- 执行命令
    ```
    执行命令发布：
    mvn release:clean release:prepare
    非交互式模式的发布
    从属性文件release.properties中读取发布参数，进行无人值守的自动发布
    mvn -B release:clean release:prepare
    ==========================
    执行命令回滚：
    mvn release:rollback
    ==========================
    更新版本
    mvn release:update-versions
    ==========================
    release:prepare的可配置的参数：
    username, 访问SCM的用户名
    password, 访问SCM的密码
    ```
- 
- 