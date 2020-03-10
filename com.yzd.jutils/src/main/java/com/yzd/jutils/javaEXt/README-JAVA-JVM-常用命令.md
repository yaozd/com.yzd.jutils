- 查看内存
    - jmap
    ```
    查看JVM堆中对象详细占用情况
    jmap -histo [pid]
    
    导出整个JVM 中内存信息
    jmap -dump:format=b,file=文件名 [pid]
    eg:
    jps -l
    jmap -dump:format=b,file=1224.hprof 1224
    eclipse Memory Analyzer
    Eclipse 提供的一个用于分析JVM 堆Dump文件的插件。借助这个插件可查看对象的内存占用状况，引用关系，分析内存泄露等。
    http://www.eclipse.org/mat/
    ```
- 查看GC情况
    - jstat
    - [ jstat命令查看jvm的GC情况 （以Linux为例）](https://www.cnblogs.com/yjd_hycf_space/p/7755633.html) 推荐参考byArvin
    ```
    jstat -gcutil ：
    
    eg:
    jstat -gcutil 28115 1000 10
    PS:jstat -gcutil 28115(PID) 1000(间隔时间) 10(执行次数)
    结果信息：
    
    S0 — Heap上的 Survivor space 0 区已使用空间的百分比 
    S1 — Heap上的 Survivor space 1 区已使用空间的百分比 
    E — Heap上的 Eden space 区已使用空间的百分比 
    O — Heap上的 Old space 区已使用空间的百分比 
    P — Perm space 区已使用空间的百分比 
    YGC — 从应用程序启动到采样时发生 Young GC 的次数 
    YGCT– 从应用程序启动到采样时 Young GC 所用的时间(单位秒) 
    FGC — 从应用程序启动到采样时发生 Full GC 的次数 
    FGCT– 从应用程序启动到采样时 Full GC 所用的时间(单位秒) 
    GCT — 从应用程序启动到采样时用于垃圾回收的总时间(单位秒)
    ————————————————
    原文链接：https://blog.csdn.net/jiankunking/article/details/79688903
    ```
- 用jstack定位CPU占用率高的代码
    - [高手是怎么使用jstack精确找到异常代码的](https://jingyan.baidu.com/article/4f34706e3ec075e387b56df2.html) 
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
- 参考堆栈信息
    ```
    jstack 26731 |grep runnable -A 50|grep EPoll -A 10
    jstack -l <pid> | grep <thread-hex-id> -A 10 命令显示出错的堆栈信息
    PA: -A 10 参数用来指定显示行数，否则只会显示一行信息
    ```