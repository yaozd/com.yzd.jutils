## 示例 byArvin
- 下载文件-通过MD5验证完整性
```
download.sh

#!/bin/sh
filePath="/root/package/tmp"
fileNamePrefix="hyperspace-console-api"
downloadLastFileUrl="http://192.168.56.112:9999/downloadLastFile"
tarFile="$filePath/$fileNamePrefix.tar"
jarFile="$filePath/$fileNamePrefix.jar"
md5File="$filePath/$fileNamePrefix.jar.MD5"
echo "删除临时目录:$filePath"
rm -rf $filePath
if [ ! -d $filePath ];then
mkdir $filePath
fi
echo "下载文件"
wget -O $tarFile $downloadLastFileUrl
if [ ! -f $tarFile ];then
echo "文件不存在"
exit 0
fi
echo "解压文件"
tar -xf $tarFile -C $filePath
if [ ! -f $jarFile ];then
echo "jarFile文件不存在"
exit 0
fi
if [ ! -f $md5File ];then
echo "md5File文件不存在"
exit 0
fi
echo "验证文件是否完整?"
jarFileMd5Val=$(md5sum $jarFile)
echo $jarFileMd5Val
jarFileMd5ExpectVal=$(cat -A $md5File |cut -b 1-32)
echo $jarFileMd5ExpectVal
if [[ $jarFileMd5Val =~ $jarFileMd5ExpectVal ]]
then
    echo -e "\033[31m 验证文件完整性-成功 \033[0m"
	## rm -f $md5File
	## rm -f $jarFile
else
    echo -e "\033[33m 验证文件完整性-失败 \033[0m"
	## rm -f $md5File
	## rm -f $jarFile
	exit 0
fi
if [ -f "./$fileNamePrefix.jar" ];then
echo "备份旧的jarFile文件"
mv ./$fileNamePrefix.jar ./$fileNamePrefix.jar.bak.$(date "+%Y%m%d%H%M%S")
fi
echo "复制新的jarFile文件"
cp $jarFile .
echo "显示文件列表"
ls -lh 


apply ./download
```