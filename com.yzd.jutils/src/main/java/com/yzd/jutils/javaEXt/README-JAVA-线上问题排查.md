## *JAVA-线上问题排查*
- [java应用监测(4)-线上问题排查套路](https://blog.csdn.net/chongshuang2128/article/details/101006203)
- [Java 线上问题排查思路与工具使用](https://blog.csdn.net/GitChat/article/details/79019454)
- CPU使用过高问题：
    - [一次线上java应用响应时间过长问题的排查](https://blog.csdn.net/xinzhongtianxia/article/details/101544986)
    - [记一次java程序CPU占用过高问题排查](https://blog.csdn.net/puhaiyang/article/details/78663942) -推荐参考byArvin
- [java线上服务问题排查](https://blog.51cto.com/13293070/2380036)
- []()

## *visualVMExt* 
> PS:下面的也可以参考：
>

- 常用 使用--help，查看命令具体使用
```
 jps -v
 
 jstat -gc 118694 500 5 
 
 jmap -dump:live,format=b,file=dump.hprof 29170
 
 jmap -heap 29170
 
 jmap -histo:live 29170 | more
 
 jmap -permstat 29170
 
 jstack -l 29170 |more
```

 
