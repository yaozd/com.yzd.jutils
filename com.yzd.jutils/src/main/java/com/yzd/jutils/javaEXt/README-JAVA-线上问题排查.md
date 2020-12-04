## *JAVA-线上问题排查*
- [java应用监测(4)-线上问题排查套路](https://blog.csdn.net/chongshuang2128/article/details/101006203) -首要参考byArvin
- [Java 线上问题排查思路与工具使用](https://blog.csdn.net/GitChat/article/details/79019454)
- CPU使用过高问题：
    - [一次线上java应用响应时间过长问题的排查](https://blog.csdn.net/xinzhongtianxia/article/details/101544986)
    - [记一次java程序CPU占用过高问题排查](https://blog.csdn.net/puhaiyang/article/details/78663942) -推荐参考byArvin
- [java线上服务问题排查](https://blog.51cto.com/13293070/2380036)
- [曹工改bug：cpu狂飙，old gc频繁，线程神秘死亡连环案件调查报告](https://www.cnblogs.com/grey-wolf/p/13558194.html)
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

验证old区对象是否可以正常回收，触发fullgc
jmap -histo:live pid
```

- java不打印异常堆栈
- [java不打印异常堆栈](https://blog.csdn.net/tengdazhang770960436/article/details/91838820)
- [异常栈信息不见了之JVM参数OmitStackTraceInFastThrow](https://www.jianshu.com/p/cc1bd35466cb)
```
VM在默认启动的时候会加上OmitStackTraceInFastThrow参数，
含义是当大量抛出同样的异常的后，后面的异常输出将不打印堆栈。
原因是打印堆栈的时候底层会调用到Throwable.getOurStackTrace()方法，
而这个方法是synchronized的，对性能有比较明显对影响。所以这个参数设置是合理的。
1.执行优化选项
不需要对jvm参数进行设置，因为默认就是
-XX:+OmitStackTraceInFastThrow这么配置的
2.取消jvm的优化
在测试启动类里面配置上jvm的参数，去掉优化，
-XX:-OmitStackTraceInFastThrow
PS:
switch .. case ..部分可知，JVM只对几个特定类型异常开启了Fast Throw优化，这些异常包括：

NullPointerException
ArithmeticException
ArrayIndexOutOfBoundsException
ArrayStoreException
ClassCastException

```
 
