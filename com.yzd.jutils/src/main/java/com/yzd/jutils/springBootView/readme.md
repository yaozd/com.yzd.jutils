#web视图渲染
### 性能：velocity应该是最好的。其次是jsp。普通的页面freemarker性能最差
```
velocity应该是最好的-byArvin推荐（2018-08-24）
```
### 进级问题-减小html页面的大小
```
[转载]开发 Spring 自定义视图和视图解析器
https://www.cnblogs.com/ywjy/p/5038655.html
springMVC(7) ViewResolver实现之VelocityViewResolver
https://www.jianshu.com/p/631111312834
SpringBoot与velocity的结合的示例代码
https://www.jb51.net/article/137108.htm
springboot 1.4.0 +VelocityLayout+VelocityTools2.0 配置 
http://blog.sina.com.cn/s/blog_4b0e11bc0102x7y3.html
```
### 减小html页面的大小的思路
```
1.前后端分离
2.剔除视图模板中的无效空格与回车（减少网络数据传输）
3.通过velocity把model与view进行渲染
3.前台通过VUE与json数据渲染出来
```

### freemarker
```
springboot+freemarker 增加自定义变量和自定义
https://blog.csdn.net/shi0299/article/details/74332593
```

### Thymeleaf
```
Thymeleaf添加全局静态变量
https://blog.csdn.net/u011144425/article/details/79404124
thymeleaf全局常量定义（非国际化）
https://www.jianshu.com/p/9214d792c5e3
我踩的thymeleaf渲染框架的坑
https://blog.csdn.net/quuqu/article/details/52637909
IDEA项目搭建十三——Web站点Controller基类及布局页静态资源设计
https://www.cnblogs.com/taiyonghai/p/9415062.html
```
### jsp,velocity,freemark页面引擎的比較-(主要关注性能)
```
jsp,velocity,freemark页面引擎的比較-(主要关注性能)
http://www.cnblogs.com/yxysuanfa/p/7126381.html
//
1、性能。velocity应该是最好的。其次是jsp。普通的页面freemarker性能最差（尽管仅仅是几毫秒到十几毫秒的差距）。

可是在复杂页面上（包括大量推断、日期金额格式化）的页面上，freemarker的性能比使用tag和el的jsp好。
2、宏定义比jsp tag方便
3、内置大量经常使用功能。

比方html过滤。日期金额格式化等等，使用很方便
```
### 性能：velocity应该是最好的。其次是jsp。普通的页面freemarker性能最差

