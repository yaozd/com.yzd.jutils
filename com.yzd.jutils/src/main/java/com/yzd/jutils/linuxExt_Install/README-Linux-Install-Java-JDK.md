- JAVA JDK 
    - [centos7 删除自带openJDK，安装所需JDK和JDK环境变量配置](https://blog.csdn.net/weixin_43081559/article/details/82381077)
    ```
    查看自带的openJDK
    rpm -qa | grep java
    rpm -qa|grep ^java-
  
    逐一将显示的openJDK删除
    rpm -e --nodeps java-1.8.0-openjdk-headless-1.8.0.101-3.b13.el7_2.x86_64
    rpm -e --nodeps java-1.8.0-openjdk-1.8.0.101-3.b13.el7_2.x86_64
    rpm -e --nodeps java-1.7.0-openjdk-headless-1.7.0.111-2.6.7.2.el7_2.x86_64
    rpm -e --nodeps java-1.7.0-openjdk-1.7.0.111-2.6.7.2.el7_2.x86_64
    ————————————————
    https://www.oracle.com/technetwork/java/javase/downloads/index.html
    下载JDK
    Java SE Development Kit 8 下载地址（推荐此版本）
    
    安装JDK
    注：在JDK的rpm包下载目录下安装JDK(本文以jdk-8u181-linux-x64.rpm为例)
    
    rpm -ivh jdk-8u181-linux-x64.rpm
    或者
    yum -y install jdk-8u181-linux-x64.rpm
    
    三、JDK环境变量配置
    编辑配置文件
    vim /etc/profile
    
    在文末增加如下内容
    JAVA_HOME=/usr/java/jdk1.8.0_181-amd64
    CLASSPATH=./:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/rt.jar
    PATH=$JAVA_HOME/bin:$PATH
    export JAVA_HOME CLASSPATH PATH
    注：JAVA_HOME 为 JDK 安装目录
    
    使环境变量配置生效
    source /etc/profile
    ```