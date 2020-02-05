## 示例 byArvin
- 下载文件-通过MD5验证完整性
```
download.sh

#!/bin/sh
echo "下载文件"
filePath="/root/package"
fileNamePrefix="hyperspace-console-api"
tarFile="$filePath/$fileNamePrefix.tar"
jarFile="$filePath/$fileNamePrefix.jar"
md5File="$filePath/$fileNamePrefix.jar.MD5"
if [ ! -f $tarFile ];then
echo "文件不存在"
exit 0
fi
echo "解压文件"
tar -xf $tarFile
echo "验证文件是否完整?"
jarFileMd5Val=$(md5sum $jarFile)
echo $jarFileMd5Val
jarFileMd5ExpectVal=$(cat -A $md5File |cut -b 1-32)
echo $jarFileMd5ExpectVal
if [[ $jarFileMd5Val =~ $jarFileMd5ExpectVal ]]
then
    echo -e "\033[31m 验证文件完整性-成功 \033[0m"
	rm -f $md5File
	rm -f $jarFile
else
    echo -e "\033[33m 验证文件完整性-失败 \033[0m"
	rm -f $md5File
	rm -f $jarFile
fi
echo "显示文件列表"
ls -lh 

apply ./download
```