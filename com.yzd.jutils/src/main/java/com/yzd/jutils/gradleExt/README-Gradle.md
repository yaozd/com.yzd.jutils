## gradle
```
构建命令
清理命令

gradle clean

构建打包命令

gradle clean build

编译时跳过测试，使用-x,-x参数用来排除不需要执行的任务

gradle clean build -x test

eg:
gradle clean build -x test   -PskipAndroid=true
```