

#### [ERROR] The goal you specified requires a project to execute but there is no POM in this directory

```
mvn install 本地库--》一定要在DOS下执行，不要在powershell下使用
必须在DOS下执行！！！！
===
mvn install:install-file -DgroupId="com.ckfinder" -DartifactId=ckfinder   -Dversion=2.3 -Dpackaging=jar  -Dfile=G:/thirdxsd/ckfinder-2.3.jar
必须在DOS下执行！！！！

mvn install:install-file -DgroupId=com.ckfinder -DartifactId=ckfinderplugin-fileeditor -Dversion=2.3 -Dpackaging=jar  -Dfile=G:/thirdxsd/ckfinderplugin-fileeditor-2.3.jar
必须在DOS下执行！！！！

mvn install:install-file   -Dfile=G:/thirdxsd/ckfinderplugin-imageresize-2.3.jar -DgroupId=com.ckfinder -DartifactId=ckfinderplugin-imageresize -Dversion=2.3 -Dpackaging=jar
```

#### [maven 添加jar包到本地仓库](https://blog.csdn.net/zhangfeng2124/article/details/72637072)

```
mvn install 本地库--》一定要在DOS下执行，不要在powershell下使用
必须在DOS下执行！！！！
```

#### [maven 打包时动态替换properties资源文件中的配置值](https://blog.csdn.net/xiao_jun_0820/article/details/49864285)

#### 版本号
- [maven 自动编译版本号 buildnumber-maven-plugin 1.4](https://blog.csdn.net/whl__csdn/article/details/78773837)

#### [Maven自动升级版本号并打包上传的脚本](https://shansun123.iteye.com/blog/983763)

#### [Maven多环境配置：开发环境、测试环境、生产环境打包配置](https://blog.csdn.net/twomr/article/details/78663115)
```
如果，你想打包的时候文件名称能够带上相应环境，那么将finalName标签配置如下即可：

<finalName>${project.artifactId}-${env}</finalName>
```

#### [maven打包加时间戳](https://blog.csdn.net/z410970953/article/details/50680603)