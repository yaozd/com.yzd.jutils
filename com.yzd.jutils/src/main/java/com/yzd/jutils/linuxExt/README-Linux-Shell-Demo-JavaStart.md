## [使用shell脚本运行java程序](https://www.jianshu.com/p/7220bcfb1e7f)
```
#!/bin/sh
# radius server 运行脚本

# jdk路径
JAVAHOME=$JAVA_HOME

# 运行程序的用户
RUNNING_USER=$USER

# 项目根目录的绝对地址
BASEPATH=$(cd `dirname $0`; pwd)

# 程序路径,项目是用maven编译打包的,所以jar在target文件夹中,绝对路径在执行时构建,方便项目迁移
APP_HOME=$BASEPATH/target/radiusServer-1.0-SNAPSHOT-jar-with-dependencies.jar

# main方法类
MAIN_CLASS=your.main.class

#构建完整的classpath,除了main_class外,还需包含程序运行必须的依赖包
#因为项目是用maven打包的,所以依赖包会打包到target/lib目录下
CLASSPATH=$APP_HOME/classes
for i in $APP_HOME/lib/*.jar; do
    CLASSPATH="$CLASSPATH":"$i"
done

################################################################
# 判断程序是否已经启动,若已启动,则初始化全局psid(程序的id),方便下面停止程序时使用.
#
# psid: 全局变量,程序的在系统中的id
# jps: java的一个命令行工具,用于查找本机上正在运行的java程序及其pid
# grep: 正则表达式搜索命令
# awk: linux上的分割函数,分割后的结果中,$0表示整行,$1表示第一个字段,以此类推
#
################################################################

psid=0
initPsid(){
    javaps=`$JAVAHOME/bin/jps -l | grep $APP_HOME`
    
    if [ -n "$javaps" ]; then
        psid=`echo $javaps | awk '{ print $1}'`
    else
        psid=0
    fi
}

################################################################
# 启动程序
# 1.首先判断程序是否已经启动
# 2.已启动,则提示用户;未启动,则执行命令启动程序
# 3.最后输出启动启动程序的结果
#
# -ne: not equal
# nohup 其他命令 &: 让程序在后台运行的的命令,
# 其中,./program >/dev/null 表示将程序普通的输出流输出到dev目录下null文件中(该文件会自动清空,相当于黑洞),
# 2>log.out 表示将程序的异常及错误日志输出到/home/log.out这个文件中
# su - user -c commandline: 以user身份运行commandline这句命令
# &? 表示上一句命令或函数执行的结果
#
################################################################

start(){
    initPsid
    if [ ! -n "$1" ]; then
        echo "没有指定配置文件,使用默认配置文件..."
        configPath=$BASEPATH/conf.json
    else
        echo "使用指定的配置文件: $1"
        configPath=$1
    fi

    if [ $psid -ne 0 ]; then
        echo "=================================================="
        echo "|         server has already started          |"
        echo "=================================================="
    else
        echo "starting $MAIN_CLASS ..."
        JAVA_CMD="nohup $JAVAHOME/bin/java -jar $APP_HOME -c $configPath ./program >log.out &"
        su - $RUNNING_USER -c "$JAVA_CMD"
        initPsid
        if [ $psid -ne 0 ]; then
            echo "start [OK] pid=$psid"
        else
            echo "start [FAILED], $?"
        fi
    fi
}

################################################################
#
# 停止程序
# 执行kill pid来使程序退出
# kill命令默认是退出,非强制,服务端收到退出指令后将执行停止操作(如关闭线程池之类的),
# 执行完才算真正退出
#
################################################################

stop(){
    initPsid

    if [ $psid -ne 0 ]; then
        echo -n "Stopping $MAIN_CLASS pid=$psid ..."
        su - $RUNNING_USER -c "kill $psid"
        if [ $? -eq 0 ]; then
            echo "Stop [OK]"
        else
            echo "Stop [FAILED]"
        fi
    else
        echo "=================================================="
        echo "|      WARN: $MAIN_CLASS is not running!      |"
        echo "=================================================="
    fi
}

#################################################################
#
# 获取服务端程序是否正在运行
#
#################################################################

status(){
    initPsid
    if [ $psid -ne 0 ]; then
        echo "Running"
    else
        echo "not Running"
    fi
}

##################################################################
#
# 输出程序运行的环境
#
##################################################################

info(){
    echo "********* System Information ***********"
    echo `head -n 1 /etc/issue`
    echo `uname -a`
    echo
    echo "JAVAHOME=$JAVAHOME"
    echo `$JAVAHOME/bin/java -version`
    echo
    echo "USER=$RUNNING_USER"
    echo "BASEPATH=$BASEPATH"
    echo "APP_HOME=$APP_HOME"
    echo "MAIN_CLASS=$MAIN_CLASS"
    echo "*****************************************"
}

#################################################################
#
# 根据参数确定调用哪个函数
#
# 函数传递参数的方式: funcName param1 param2
# 在函数中获取参数的方式: $1 $2,第一个参数就是$1,第n个就是$n
#
#################################################################

case "$1" in
    'start')
        start $2
        ;;
    'stop')
        stop
        ;;
    'restart')
        stop
        start $2
        ;;
    'status')
        status
        ;;
    'info')
        info
        ;;
    *)

    echo "Usage $0 { start | stop | status | restart | info }"
    exit
esac
exit 0
```