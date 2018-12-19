
###　Java内存分析工具MAT(Memory Analyzer Tool)　
- -[Java内存分析工具MAT(Memory Analyzer Tool)安装使用实例-推荐byArvin](https://blog.csdn.net/jin_kwok/article/details/80326088)
- -[内存问题的分析与内存分析工具MAT(Memory Analyzer Tool)的使用](https://blog.csdn.net/m0_37450089/article/details/81368785)

### 常见问题分析思路

```
Dominator Tree:如果说需要定位内存泄露的代码点，我们可以通过Dominator Tree菜单选项来进行排查。Dominator Tree提供了一个列表。Dominator Tree：对象之间dominator关系树。如果从GC Root到达Y的的所有path都经过X，那么我们称X dominates Y，或者X是Y的Dominator 。Dominator Tree由系统中复杂的对象图计算而来。从MAT的dominator tree中可以看到占用内存最大的对象以及每个对象的dominator，如
参考：
Java内存分析工具MAT(Memory Analyzer Tool)安装使用实例
https://blog.csdn.net/jin_kwok/article/details/80326088 
-----------------------------------------------------------
内存溢出一般情况下，是在用户自己的代码造成，所以可以通过正则表达式的方式去过滤查找，如：“com.yzd”或者“.*com.yzd.*”，先定位到当前项目相关的类，再分析问题。

```