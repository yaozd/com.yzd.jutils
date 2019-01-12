
> [《Maven-编译错误解决》---请使用 -Xlint:unchecked 重新编译](https://blog.csdn.net/w695050167/article/details/76090898)
```
问题描述
注: 某些输入文件使用了未经检查或不安全的操作。
注: 有关详细信息, 请使用 -Xlint:unchecked 重新编译。
1
2
刚看到上面的问题的时候，有点懵。不知道将【-Xlint:unchecked】放到什么地方。

折腾了好久，终于找到。这不针对于maven，因为我是在maven上出的错。然后找maven相关的内容没有找的。所以就加了这么个标题。

解决方案
在pom.xml配置文件中，找到插件maven-compiler-plugin，在configuration节点内添加如下代码。

<compilerArgument>-Xlint:unchecked</compilerArgument>
--------------------- 
!-- maven编译报错 -source 1.5 中不支持 lambda 表达式-->
 <plugin>
     <groupId>org.apache.maven.plugins</groupId>
     <artifactId>maven-compiler-plugin</artifactId>
     <version>3.2</version>
     <configuration>
         <source>1.8</source>
         <target>1.8</target>
         <compilerArgument>-Xlint:unchecked</compilerArgument>
     </configuration>
 </plugin>
 --------------------- 
 警告: [unchecked] 未经检查的转换
 解决方案:
 在方法的前面添加
 @SuppressWarnings("unchecked") 
 或者
 调整为正常的类型转换
 private final SortedMap<Integer, String> stepMap;
 this.stepMap = new TreeMap<Integer, String>();
```