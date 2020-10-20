## Shell
- [Shell 开发在运维中的经验总结](https://blog.51cto.com/welcomeweb/2451663)
- [shell脚本中一些特殊符号](https://blog.51cto.com/13293070/2446727)
- [Shell脚本判断文件是否存在](https://blog.csdn.net/qingfenglu/article/details/100545946)
- [shell 判断文件夹或文件是否存在](https://www.cnblogs.com/37yan/p/6962563.html)
- [linux文件查看与删除换行符](https://blog.csdn.net/qq_34538534/article/details/85078751)
- [如何在Shell读取文件并赋值](https://www.cnblogs.com/rayment/p/8446939.html)
- [shell将命令执行的结果赋值给变量](https://blog.csdn.net/lemontree1945/article/details/79126819)
- [Shell判断字符串包含关系的几种方法](https://www.cnblogs.com/AndyStudy/p/6064834.html)
- [Linux下Shell的for循环语句](https://www.cnblogs.com/EasonJim/p/8315939.html)
- [Bash Shell 获取进程 PID](https://www.cnblogs.com/lovychen/p/6211209.html)
- [echo 命令显示带颜色的字](https://blog.csdn.net/u013027894/article/details/89631979)
- [Shell 基本运算符](https://www.runoob.com/linux/linux-shell-basic-operators.html)
- [shell脚本实现取当前时间](https://www.cnblogs.com/janezhao/p/9732157.html)
- [su - user -c commandline: 以user身份运行commandline这句命令]()
- [Linux运行shell脚本提示No such file or directory错误的解决办法](https://blog.csdn.net/u013626215/article/details/88050425)
- [linux 中的test 命令](https://blog.csdn.net/liudsl/article/details/79240347)
- [使用“:”命令定义一个无限循环实例](http://blog.sina.com.cn/s/blog_6436b8ec0102xfyb.html)
- 文件备份-backup

### Shell-示例
- start.sh
```
#!/bin/sh
nohup java -jar /data/package/http-demo/http-demo-1.0-SNAPSHOT.jar &

apply:
chmod 777 start.sh
./start.sh
```
- [Linux下校验下载文件的完整性(MD5,SHA1,PGP)](https://blog.csdn.net/weixin_33998125/article/details/85672464)
```
$ md5sum your-downloaded-file-name
fd4a1b802373c57c10c926eb7ac823d8  your-downloaded-file-name
 
#将MD5 Hash值保存到md5-hash.txt文件中.
$ md5sum your-downloaded-file-name > md5-hash.txt
# 显示输出的md5-hast.txt内容
$ cat md5-hash.txt
fd4a1b802373c57c10c926eb7ac823d8  your-downloaded-file-name
 
# 通过md5-hash.txt来校验你下载的文件是否正确
$ md5sum -c md5-hash.txt
your-downloaded-file-name: OK
```
- [linux文件查看与删除换行符](https://blog.csdn.net/qq_34538534/article/details/85078751)
```
1. linux查找文本中是否存在换行符
cat -A filename.txt
以含有换行符的文件id.txt示例:
2. 删除换行符
根据文本的特点，我使用cut命令
3.Linux下校验下载文件的完整性
md5sum hyperspace-console-api.jar| grep ` cat -A hyperspace-console-api.jar.MD5 |cut -b 1-32`
PS: cut -b 1-32 通过文本截取的方式删除换行符
```
- [如何在Shell读取文件并赋值](https://www.cnblogs.com/rayment/p/8446939.html)
```
sys_info=$(cat /usr/local/sysconfig.txt)
var=`echo   $sys_info   |   awk   -F ', '   '{print   $0} '   |   sed   "s/,/   /g "`
ip=$(echo $var | awk '{print $1}')
netmask=$(echo $var | awk '{print $2}')
gateway=$(echo $var | awk '{print $3}')
db_ip=$(echo $var | awk '{print $4}')
record_ip=$(echo $var | awk '{print $5}')
```
- [shell将命令执行的结果赋值给变量](https://blog.csdn.net/lemontree1945/article/details/79126819)
```
1.用` `,（尖号）把命令括起来，然后赋值给变量
dir=`pwd`
2.采用   变量=$(pwd)
dir=$(pwd)
```
- [Shell判断字符串包含关系的几种方法](https://www.cnblogs.com/AndyStudy/p/6064834.html)
```
方法二：利用字符串运算符
复制代码
strA="helloworld"
strB="low"
if [[ $strA =~ $strB ]]
then
    echo "包含"
else
    echo "不包含"
fi
复制代码
利用字符串运算符 =~ 直接判断strA是否包含strB。（这不是比第一个方法还要简洁吗摔！）
--------------------
方法三：利用通配符
复制代码
A="helloworld"
B="low"
if [[ $A == *$B* ]]
then
    echo "包含"
else
    echo "不包含"
fi
复制代码
这个也很easy，用通配符*号代理strA中非strB的部分，如果结果相等说明包含，反之不包含。
```
- [Linux下Shell的for循环语句](https://www.cnblogs.com/EasonJim/p/8315939.html)
```
for1-1.sh

#!/bin/bash  
  
for((i=1;i<=10;i++));  
do   
echo $(expr $i \* 3 + 1);  
done  
```
- [Shell脚本判断文件是否存在](https://blog.csdn.net/qingfenglu/article/details/100545946)
- [shell 判断文件夹或文件是否存在](https://www.cnblogs.com/37yan/p/6962563.html)
```
#shell判断文件夹是否存在
 
#如果文件夹不存在，创建文件夹
if [ ! -d "/Top" ]; then
 mkdir -p /Topfi
 
#shell判断文件,目录是否存在或者具有权限
 
folder="/Top"
file="/Top/test.txt"
 
# -x 参数判断 $folder 是否存在并且是否具有可执行权限
if [ ! -x "$folder"]; then
 mkdir "$folder"
fi
 
# -d 参数判断 $folder 是否存在
if [ ! -d "$folder"]; then
 mkdir "$folder"
fi
 
# -f 参数判断 $file 是否存在
if [ ! -f "$file" ]; then
 touch "$file"
fi

# -n 判断一个"变量"是否有值
if [ ! -n "$file" ]; then
 echo "$file 变量为空！"
 exit 0
fi
 
# 判断两个变量的字符串内容是否相同
if [ "$file1" = "$file2" ]; then
 echo "$file1 equal $file2"
else
 echo "$file1 not equal $file2"
fi
```

```
#!/bin/sh
# 说明：判断文件是否存在

 
myPath="/Top"
myFile="/Top/access.log"

# 这里的-x 参数判断$myPath是否存在并且是否具有可执行权限
if [ ! -x "$myPath"]; then
 mkdir "$myPath"
fi
# 这里的-d 参数判断$myPath是否存在
if [ ! -d "$myPath"]; then
 mkdir "$myPath"
fi
 
# 这里的-f参数判断$myFile是否存在
if [ ! -f "$myFile" ]; then
 touch "$myFile"
fi


# 其他参数还有-n,-n是判断一个变量是否是否有值
if [ ! -n "$myVar" ]; then
 echo "$myVar 变量为空！"
 exit 0
fi
 
# 判断$file字符串内容是否是“123123”相同
if [ "$file1" = "123123" ]; then
 echo "$file1 equal $file2"
else
 echo "$file1 not equal $file2"
fi
```

- [Bash Shell 获取进程 PID](https://www.cnblogs.com/lovychen/p/6211209.html)
- [shell 脚本 后台启动springboot 详解](https://blog.csdn.net/ff445566/article/details/100561027)
    - start.sh
    ```
    #!/bin/bash  
    nohup java -jar qzznnb-0.0.1-SNAPSHOT.jar -Xms256m -Xmx1024m > qzznnb-0.0.1-SNAPSHOT.out 2>&1 &
    ```
    - stop.sh
    ```
    
    #!/bin/bash
    PID=$(ps -ef | grep qzznnb-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
    if [ ${PID} ];
    then
        echo 'Application is stpping...'
        echo kill $PID DONE
        kill $PID
    else
        echo 'Application is already stopped...'
    fi
    ```

- [echo 命令显示带颜色的字](https://blog.csdn.net/u013027894/article/details/89631979)
```
格式如下：
`echo -e "\033[字背景颜色；文字颜色m字符串\033[0m"`
字颜色：30—–37
　　echo -e "\033[30m 黑色字 \033[0m" 
　　echo -e "\033[31m 红色字 \033[0m" 
　　echo -e "\033[32m 绿色字 \033[0m" 
　　echo -e "\033[33m 黄色字 \033[0m" 
　　echo -e "\033[34m 蓝色字 \033[0m" 
　　echo -e "\033[35m 紫色字 \033[0m" 
　　echo -e "\033[36m 天蓝字 \033[0m" 
　　echo -e "\033[37m 白色字 \033[0m"
echo -e "\033[31m ============================= \033[0m"
```
- su - user -c commandline: 以user身份运行commandline这句命令
```
su - user -c commandline: 以user身份运行commandline这句命令
eg:
#!/bin/bash
# jdk路径
JAVAHOME=$JAVA_HOME
# 运行程序的用户
RUNNING_USER=$USER
JAVA_CMD="nohup $JAVAHOME/bin/java -jar $APP_HOME -c $configPath ./program >log.out &"
su - $RUNNING_USER -c "$JAVA_CMD"
```

- 使用“:”命令定义一个无限循环实例
```
使用“:”命令定义一个无限循环实例
[root@aa ~]# cat infinitewhile_colon.sh
#!/bin/bash -

while :
do
    echo "Do something..."
    echo "Hit [ CTRL+C ] to stop!"
    sleep 3
done
```

- 文件备份-backup
```
1-backup.sh
//
#!/bin/sh
set -x
set -e
file_name=hyperspace-container-0.0.1-SNAPSHOT.jar
if ! test -e ${file_name}
then
echo -e "\033[31m $file_name not found \033[0m"
exit 0
fi
time=$(date +%F-%H%M)
file_bak_name=${file_name}_${time}
echo ${file_bak_name}
mv ${file_name}  ${file_bak_name}
ls -lht
==========================================================
#!/bin/sh
set -x
set -e
file_name=hyperspace-container-0.0.1-SNAPSHOT.jar
time=$(date +%F-%H%M)
file_bak_name=${file_name}_${time}
echo ${file_bak_name}
mv ${file_name}  ${file_bak_name}
```