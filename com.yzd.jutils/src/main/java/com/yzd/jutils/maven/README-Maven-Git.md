
### [Maven插件获取Git信息](https://blog.csdn.net/i_love_t/article/details/82261372)
- [maven插件 maven-git-commit-id-plugin](https://blog.csdn.net/shog808/article/details/79404009)
- [Maven插件之git-commit-id-plugin-详细配置信息说明](https://blog.csdn.net/wangjunjun2008/article/details/10526151)

```
目前使用的配置-byArvin推荐
<!--主要功能：maven打jar包时带上 git commit相关信息，可以更加方便的识别当前执行的版本-->
<plugin>
    <groupId>pl.project13.maven</groupId>
    <artifactId>git-commit-id-plugin</artifactId>
    <version>2.2.0</version>
    <executions>
        <execution>
            <goals>
                <goal>revision</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <!--verbose=false可以节省时间-->
        <verbose>false</verbose>
        <generateGitPropertiesFile>true</generateGitPropertiesFile>
        <injectAllReactorProjects>true</injectAllReactorProjects>
        <dateFormat>yyyy-MM-dd HH:mm:ss</dateFormat>
    </configuration>
</plugin>
```
```
<plugin>
	<groupId>pl.project13.maven</groupId>
	<artifactId>git-commit-id-plugin</artifactId>
	<version>2.1.5</version>
	<executions>
		<execution>
			<goals>
				<goal>revision</goal>
			</goals>
		</execution>
	</executions>
	<configuration>
		<!--日期格式;默认值:dd.MM.yyyy '@' HH:mm:ss z;-->
		<dateFormat>yyyyMMddHHmmss</dateFormat>
		<!--,构建过程中,是否打印详细信息;默认值:false;-->
		<verbose>true</verbose>
		<!-- ".git"文件路径;默认值:${project.basedir}/.git; -->
		<dotGitDirectory>${project.basedir}/.git</dotGitDirectory>
		<!--若项目打包类型为pom,是否取消构建;默认值:true;-->
		<skipPoms>false</skipPoms>
		<!--是否生成"git.properties"文件;默认值:false;-->
		<generateGitPropertiesFile>true</generateGitPropertiesFile>
		<!--指定"git.properties"文件的存放路径(相对于${project.basedir}的一个路径);-->
		<generateGitPropertiesFilename>git.properties</generateGitPropertiesFilename>
		<!--".git"文件夹未找到时,构建是否失败;若设置true,则构建失败;若设置false,则跳过执行该目标;默认值:true;-->
		<failOnNoGitDirectory>true</failOnNoGitDirectory>
 
		<!--git描述配置,可选;由JGit提供实现;-->
		<gitDescribe>
			<!--是否生成描述属性-->
			<skip>false</skip>
			<!--提交操作未发现tag时,仅打印提交操作ID,-->
			<always>false</always>
			<!--提交操作ID显式字符长度,最大值为:40;默认值:7;
				0代表特殊意义;后面有解释; 
			-->
			<abbrev>7</abbrev>
			<!--构建触发时,代码有修改时(即"dirty state"),添加指定后缀;默认值:"";-->
			<dirty>-dirty</dirty>
			<!--always print using the "tag-commits_from_tag-g_commit_id-maybe_dirty" format, even if "on" a tag.
				The distance will always be 0 if you're "on" the tag.
			-->
			<forceLongFormat>false</forceLongFormat>
		</gitDescribe>
	</configuration>
</plugin>

```

## Maven: jar包名自动添加git commit id
- [Maven: jar包名自动添加git commit id](https://blog.csdn.net/wuzhimang/article/details/79393815)
```
插件名：maven-git-commit-id-plugin
pom.xml plugin配置样例
<plugin>
    <groupId>pl.project13.maven</groupId>
    <artifactId>git-commit-id-plugin</artifactId>
    <version>2.2.4</version>
    <executions>
      <execution>
        <id>get-the-git-infos</id>
        <goals>
          <goal>revision</goal>
        </goals>
      </execution>
    </executions>
    <configuration>
      <!-- 使properties扩展到整个maven bulid 周期
      Ref: https://github.com/ktoso/maven-git-commit-id-plugin/issues/280 -->
      <injectAllReactorProjects>true</injectAllReactorProjects>
      <dateFormat>yyyy.MM.dd HH:mm:ss</dateFormat>
      <verbose>true</verbose>
      <!-- 是否生 git.properties 属性文件 -->
      <generateGitPropertiesFile>true</generateGitPropertiesFile>
      <!--git描述配置,可选;由JGit提供实现;-->
      <gitDescribe>
        <!--是否生成描述属性-->
        <skip>false</skip>
        <!--提交操作未发现tag时,仅打印提交操作ID,-->
        <always>false</always>
        <!--提交操作ID显式字符长度,最大值为:40;默认值:7; 0代表特殊意义;后面有解释; -->
        <abbrev>7</abbrev>
        <!--构建触发时,代码有修改时(即"dirty state"),添加指定后缀;默认值:"";-->
        <dirty>-dirty</dirty>
        <!--always print using the "tag-commits_from_tag-g_commit_id-maybe_dirty" format, even if "on" a tag. The distance will always be 0 if you're "on" the tag. -->
        <forceLongFormat>false</forceLongFormat>
      </gitDescribe>
    </configuration>
</plugin>
————————————————
pom.xml jar包名配置样例（结合maven-shade-plugin）
同时会在target/classes下生成git.properties文件
<plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>2.4.3</version>
        <executions>
          <execution>
            <phase>package</phase>
            <goals>
              <goal>shade</goal>
            </goals>
            <configuration>
              <artifactSet>
                <includes>
                  <include>*:*</include>
                </includes>
              </artifactSet>
              <!-- 生成的jar文件名 with git.commit.id.abbrev -->
              <outputFile>
                ${project.build.directory}/${project.artifactId}-${git.commit.id.abbrev}.jar
              </outputFile>
            </configuration>
          </execution>
        </executions>
      </plugin>
————————————————

```