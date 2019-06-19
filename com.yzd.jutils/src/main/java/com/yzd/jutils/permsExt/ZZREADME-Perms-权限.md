###　github
- [https://github.com/yaozd/com.yzd.cms-example.git](https://github.com/yaozd/com.yzd.cms-example.git)-html-site-demo

###　通过js做权限控制
- [navigation.html](http://localhost:1222/html/navigation.html)-权限导航测试
- [script-execute-sequence.html](http://localhost:1222/html/script-execute-sequence.html)-JS脚本执行顺序
- [script-execute-sequence-2.html](http://localhost:1222/html/script-execute-sequence-2.html)-JS脚本执行顺序
- [script-execute-sequence-3.html](http://localhost:1222/html/script-execute-sequence-3.html)-Js在html中的加载执行顺序-最先加载当前用户权限
- [script-execute-sequence-4.html](http://localhost:1222/html/script-execute-sequence-4.html)-Js在html中的加载执行顺序-用户没有登录的情况下，自动跳转到登录页面
- [script-execute-sequence-5.html](http://localhost:1222/html/script-execute-sequence-5.html)-Js在html中的加载执行顺序-使用ajax同步请求的方式，保证脚本执行的顺序
- PS:特别注意:"\</script>"起到一个缓冲的作用，必须带上
    ```
    正确引用方式：
        <script type="text/javascript" src="/script/perms-load"></script>
        <script type="text/javascript" src="/script/perms"></script>
    错误引用方式：
        <script type="text/javascript" src="/script/perms-load"/>
        <script type="text/javascript" src="/script/perms"/>
    ```
 ### 通过js做权限控制-参考：
 - [js在html中的加载执行顺序](https://www.cnblogs.com/lindaWei/archive/2012/04/05/2433454.html)
 - [jQuery Ajax 设置请求头](https://blog.csdn.net/WRian_Ban/article/details/70257261)