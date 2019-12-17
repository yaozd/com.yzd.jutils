##　Maven发版升级-release
- maven-release-plugin
- 常用命令
    ```
    mvn release:prepare -Darguments="-DskipTests"
    mvn release:perform -Darguments="-DskipTests"
    PS:perform 会验证java DOC 一般不使用
    执行命令发布：
    mvn release:clean release:prepare
    执行命令回滚：
    mvn release:rollback
    非交互式模式的发布
    从属性文件release.properties中读取发布参数，进行无人值守的自动发布
    mvn -B release:clean release:prepare
    ==========================
    更新版本
    mvn release:update-versions
    ==========================
    release:prepare的可配置的参数：
    username, 访问SCM的用户名
    password, 访问SCM的密码
    --------------------------
    配置标签名：
    默认tag格式为@{project.artifactId}-@{project.version}，也可以配置<tagNameFormat>以设置tag格式。
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-release-plugin</artifactId>
        <configuration>
            <arguments>-DskipTests</arguments>
            <tagNameFormat>V@{project.version}</tagNameFormat>
        </configuration>
    </plugin>
    --------------------------
    配置git地址
    <project>
        <scm>
            <connection>scm:git:http://项目git地址</connection>
            <url>项目git地址（不加'.git后缀'）</url>
            <developerConnection>scm:项目git地址</developerConnection>
        </scm>
    </project>
    <scm>
    	<developerConnection>scm:git:http://git.spt.com/team/supply.git</developerConnection>
    	<url>http://git.spt.com/team/supply.git</url>
    	<tag>HEAD</tag>
    </scm>
    --------------------------
    配置nexus地址
    <distributionManagement>
    	<repository>
    		<id>releases</id>
    		<name>Internal Releases</name>
    		<url>http://192.168.2.164:8081/nexus/content/repositories/releases</url>
    	</repository>
    	<snapshotRepository>
    		<id>snapshots</id>
    		<name>Internal Snapshots</name>
    		<url>http://192.168.2.164:8081/nexus/content/repositories/snapshots</url>
    	</snapshotRepository>
    </distributionManagement>
    --------------------------

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
    - [maven发布插件：maven-release-plugin](https://blog.csdn.net/wlddhj/article/details/84106597)
    ```
    <plugin>
    	<groupId>org.apache.maven.plugins</groupId>
    	<artifactId>maven-release-plugin</artifactId>
    	<version>2.5.3</version>
    	<configuration>
    		<tagBase>http://git.spt.com/team/supply.git</tagBase>
    		<connectionUrl>http://git.spt.com/team/supply.git</connectionUrl>
    		<useReleaseProfile>false</useReleaseProfile>
    		<autoVersionSubmodules>true</autoVersionSubmodules>
    		<preparationGoals>clean deploy</preparationGoals>
    	</configuration>
    </plugin>
    ————————————————
    <scm>
    	<developerConnection>scm:git:http://git.spt.com/team/supply.git</developerConnection>
    	<url>http://git.spt.com/team/supply.git</url>
    	<tag>HEAD</tag>
    </scm>
    ————————————————
    <distributionManagement>
    	<repository>
    		<id>releases</id>
    		<name>Internal Releases</name>
    		<url>http://192.168.2.164:8081/nexus/content/repositories/releases</url>
    	</repository>
    	<snapshotRepository>
    		<id>snapshots</id>
    		<name>Internal Snapshots</name>
    		<url>http://192.168.2.164:8081/nexus/content/repositories/snapshots</url>
    	</snapshotRepository>
    </distributionManagement>
    ```
    - [【jenkins】自动修改工程版本号 以及 父pom版本号 （maven-release-plugin）](https://blog.csdn.net/u010900754/article/details/90582420)
    - [maven release plugin 使用](https://blog.csdn.net/qq_41253247/article/details/91824513)
    - [Apache Maven Release Plugin插件详解](https://blog.csdn.net/taiyangdao/article/details/82658799) 推荐参考byArvin