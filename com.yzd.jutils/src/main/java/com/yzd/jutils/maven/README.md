

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