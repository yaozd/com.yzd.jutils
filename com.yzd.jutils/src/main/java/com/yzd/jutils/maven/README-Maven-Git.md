
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