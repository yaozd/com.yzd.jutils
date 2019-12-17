##　Maven发版升级-release
- maven-release-plugin
- 常用命令
    ```
    mvn release:prepare -Darguments="-DskipTests"
    mvn release:perform -Darguments="-DskipTests"
    执行命令发布：
    mvn release:clean release:prepare
    执行命令回滚：
    mvn release:rollback
    --------------------------
    配置git地址
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
    - []()