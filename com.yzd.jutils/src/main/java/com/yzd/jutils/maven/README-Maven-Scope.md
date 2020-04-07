## [<scope>provided</scope> 关于maven依赖中的scope的作用和用法](https://blog.csdn.net/qq_36874292/article/details/81072717)
- provided适合在编译和测试的环境，同样provide将不会被打包到lib目录下
```
compile
默认就是compile，什么都不配置也就是意味着compile。compile表示被依赖项目需要参与当前项目的编译，当然后续的测试，运行周期也参与其中，是一个比较强的依赖。打包的时候通常需要包含进去。默认的scope,在部署的时候将会打包到lib目录下，项目在编译，测试，运行阶段都需要

test
scope为test表示依赖项目仅仅参与测试相关的工作，在编译和运行环境下都不会被使用，更别说打包了。

runntime
runntime这个scope，仅仅适用于运行环境，在编译和测试环境下都不会被使用

provided
provided适合在编译和测试的环境，他和compile很接近，但是provide仅仅需要在编译和测试阶段，同样provide将不会被打包到lib目录下。

system
从参与度来说，也provided相同，不过被依赖项不会从maven仓库抓，而是从本地文件系统拿，一定需要配合systemPath属性使用。

scope的依赖传递
A–>B–>C。当前项目为A，A依赖于B，B依赖于C。知道B在A项目中的scope，那么怎么知道C在A中的scope呢？答案是： 
当C是test或者provided时，C直接被丢弃，A不依赖C； 
否则A依赖C，C的scope继承于B的scope。

为什么需要区分这些scope
可以用来限制dependency的范围可以在不同的环境下打包不同的jar包，比如junit测试类的jar包不需要在编译运行的时候，就可以设置scope为test。

最后还有一个<optional>true</optional>是什么意思，怎么用的呢？
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
</dependency>

例如上面的例子，在SpringBoot官网文件中你可以得到解释就是，<optional>true</optional>的话，其他项目依赖此项目也不会进行传递，只能本项目使用。

```