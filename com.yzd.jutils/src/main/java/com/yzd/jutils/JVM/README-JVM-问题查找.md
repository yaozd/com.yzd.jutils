## [SolrCloud 集群 ConcurrentLRUCache Full Gc 问题查找 即 JVM老年代无法回收](https://blog.csdn.net/tianpeng341204/article/details/78953445)
```
Solr  JVM ，一直FUll  GC，老年代对象一直存活，无法回收，初步分析： 使用 eclipse 插件  Memory  Analysis Tools ( MAT )分析老年代 内存占用情况
2.  进行步骤
A.导出  内存快照
jmap -dump:format=b,file=/tmp/heap.hprof  25477       
B.把  /tmp/heap.hprof  下载到本地，使用  MAT 进行分析
3.结果分析
--------------------- 
原文：https://blog.csdn.net/tianpeng341204/article/details/78953445 
```

## system.gc--FULL GC
- [记一次每隔一小时 Full GC (System.gc())问题的解决过程](https://www.jianshu.com/p/6a2f14067f50?tdsourcetag=s_pctim_aiomsg)
- [JVM菜鸟进阶高手之路六:JVM每隔一小时执行一次Full GC（详细篇）](https://alpha-ss.sohu.com/infonews/article/6346133922451030016?tdsourcetag=s_pctim_aiomsg&qq-pf-to=pcqq.group)
- []()
- []()