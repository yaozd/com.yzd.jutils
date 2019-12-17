### Maven常用参数说明
- [Maven常用参数说明](https://blog.csdn.net/bugzeroman/article/details/88888907)

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

#### scope的分类
```
1.compile：默认值 他表示被依赖项目需要参与当前项目的编译，还有后续的测试，运行周期也参与其中，是一个比较强的依赖。打包的时候通常需要包含进去

2.test：依赖项目仅仅参与测试相关的工作，包括测试代码的编译和执行，不会被打包，例如：junit

3.runtime：表示被依赖项目无需参与项目的编译，不过后期的测试和运行周期需要其参与。与compile相比，跳过了编译而已。例如JDBC驱动，适用运行和测试阶段

4.provided：打包的时候可以不用包进去，别的设施会提供。事实上该依赖理论上可以参与编译，测试，运行等周期。相当于compile，但是打包阶段做了exclude操作

5.system：从参与度来说，和provided相同，不过被依赖项不会从maven仓库下载，而是从本地文件系统拿。需要添加systemPath的属性来定义路径

scope的依赖传递
A依赖B，B依赖C。当前项目为A，只当B在A项目中的scope，那么c在A中的scope是如何得知呢？

当C是test或者provided时，C直接被丢弃，A不依赖C；（排除传递依赖）

否则A依赖C，C的scope继承与B的scope
————————————————
```