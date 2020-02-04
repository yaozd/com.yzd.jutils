## Shell
- [Shell 开发在运维中的经验总结](https://blog.51cto.com/welcomeweb/2451663)
- [shell脚本中一些特殊符号](https://blog.51cto.com/13293070/2446727)
- []()

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