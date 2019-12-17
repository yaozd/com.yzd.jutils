## jstack示例
- [高手是怎么使用jstack精确找到异常代码的](https://jingyan.baidu.com/article/4f34706e3ec075e387b56df2.html) -CPU占用时间长
    ```
    java -jar JavaStudy.jar
    //找到CPU利用率持续比较高的进程,获取进程号，此处PID为3036
    top 
    //找到上述进程中，CPU利用率比较高的线程号TID（十进制数）,此处为3046
    ps p 3036 -L -o pcpu,pid,tid,time,tname,cmd
    //将获取的线程号（十进制数）转换成十六进制,此处为0xb46
    printf "%x\n"  3046
    //查看进程PID为3036中,nid为0xb46的线程信息。
    jstack -l 3036
    jstack 3036 | grep -A 20 nid=0xbe6
    jstack -l <pid> | grep <thread-hex-id> -A 10 命令显示出错的堆栈信息
     -A 10 参数用来指定显示行数，否则只会显示一行信息
    PS:
    可以看到jstack命令的输出结果是相当准确的：
    显示耗CPU比较高的代码与实际情况相同，都是第13行
    ```
- [jstack性能问题定位案例分析详解](https://blog.csdn.net/mlljava1111/article/details/81170787)
- [使用jstack定位线程堆栈信息,精确找到异常代码](https://blog.csdn.net/AAA821/article/details/80078905)
- [jstack和线程dump分析](https://blog.csdn.net/iteye_13353/article/details/82064224)
- [教你怎么用jstack定位CPU占用率高的代码](https://blog.csdn.net/erbon_st/article/details/81739412)