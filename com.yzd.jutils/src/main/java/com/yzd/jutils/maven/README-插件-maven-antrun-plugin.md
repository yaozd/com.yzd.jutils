## maven-antrun-plugin
- [maven 将指定的包打成jar包之maven-jar-plugin](https://blog.csdn.net/yhylovezl/article/details/89187283)
- [maven-antrun-plugin的简单使用(复制jar包到指定目录,复制文件到服务器,连接服务器执行命令)](https://blog.csdn.net/qq_33547169/article/details/83059858)
```
 <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-antrun-plugin</artifactId>
        <version>1.8</version>
        <executions>
            <execution>
                <phase>install</phase>
                <goals>
                    <goal>run</goal>
                </goals>
                <configuration>
                    <tasks>
                        <echo>Building 描述信息 START....</echo>
                        <!-- 删除目录 -->
                        <delete dir="target/dir"/>
                        <!-- 删除目录 -->
                        <mkdir dir="target/dir"/>
 
                        <!-- 复制目录文件 复制到-todir 要复制的目录-fileset -->
                        <copy todir="target/dir/xxx">
                            <fileset dir="target/classes/config"/>
                        </copy>
                        <!-- 删除文件 -->
                        <delete>
                            <fileset dir="target/xxx/lib/xxx" includes="xxx*.jar"/>
                            <fileset dir="target/xxx/lib/xxx" includes="xxx-*.jar"/>
                        </delete>
 
                        <!-- 将指定目录压缩ZIP -->
                        <copy todir="target/tempbuild/xxx">
                            <fileset dir="target/xxx"/>
                        </copy>
 
                        <tstamp>
                            <format property="current.date.time" pattern="yyyyMMddHHmmss"/>
                        </tstamp>
 
                        <zip destfile="target/xxx_${version}_${current.date.time}.zip">
                            <zipfileset dir="target/tempbuild"/>
                        </zip>
 
                        <checksum file="target/xxx_${version}_${current.date.time}.zip"
                                  forceOverwrite="yes" algorithm="MD5" fileext=".MD5"></checksum>
                        <!--上传文件
                            file: 文件路径或者文件名称
                            	${project.build.directory} 指向target目录
                            	${project.build.finalName} 打包名称
                            todir: 目标服务器ip地址和文件路径
                        -->
                        <scp file="${project.build.directory}\${project.build.finalName}.jar"
                             todir="username:password@ip:/root/jar_project" trust="true"/>
                        <!--连接虚拟机
                        	file: 文件路径或者文件名称
                        		command 执行命令
                        -->
                        <sshexec host="ip"
                                 username="username"
                                 password="password"
                                 command="ls"
                                 trust="true"/>
                        <delete dir="target/tempbuild"/>
                        <echo>Building 描述信息 END</echo>
                    </tasks>
                </configuration>
            </execution>
        </executions>
    </plugin>
```