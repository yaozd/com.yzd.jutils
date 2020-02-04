## scope
- [spring mvc 设置@Scope("prototype")](https://blog.csdn.net/zhang_dianliang/article/details/76850906)
```
spring中bean的scope属性，有如下5种类型：

singleton 表示在spring容器中的单例，通过spring容器获得该bean时总是返回唯一的实例
prototype表示每次获得bean都会生成一个新的对象
request表示在一次http请求内有效（只适用于web应用）
session表示在一个用户会话内有效（只适用于web应用）
globalSession表示在全局会话内有效（只适用于web应用）
在多数情况，我们只会使用singleton和prototype两种scope，如果在spring配置文件内未指定scope属性，默认为singleton。

单例的原因有二：
1、为了性能。

2、不需要多例。

1、单例不用每次都new，当然快了。
2、不需要实例会让很多人迷惑，因为spring mvc官方也没明确说不可以多例。
————————————————
版权声明：本文为CSDN博主「zhang_dianliang」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/zhang_dianliang/article/details/76850906
```
