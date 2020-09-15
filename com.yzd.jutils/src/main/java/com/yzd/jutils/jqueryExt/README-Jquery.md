
> [jquery/js实现一个网页同时调用多个倒计时(最新的)](https://blog.csdn.net/websites/article/details/50037611)
- ready
    ```
    $(function(){
    });
    ```
- [解决 jQuery 动态新增节点无法触发 onclick 点击事件的问题](https://blog.csdn.net/zcf980/article/details/83060286)
- [jquery点击事件失效原因和解决办法](https://www.cnblogs.com/leiting/p/9323539.html)
    ```
    使用 on() 方法：推荐使用该方法
    自 jQuery 版本 1.7 起，on() 方法是 bind()、live() 和 delegate() 方法的新的替代品。该方法给 API 带来很多便利
    --------------
    $("#parent").on('click', 'child', function(){});
    --------------
    $('body').on('click', '.but', function() {});
    --------------
    通用方法：
    $(document).on('click', 'li',function(){
        alert($(this).html());
    });
    ```
- [JavaScript或jQuery模拟点击超链接和按钮](https://www.cnblogs.com/freeweb/p/4797872.html)
```
document.getElementById("roleListLi").click();
```
- [jquery，字符串转json对象，json对象转字符串](https://www.cnblogs.com/alsf/p/7528104.html)
    ```
    var obj4Str=JSON.stringify(jsonobj); //可以将json对象转换成字符串
    var obj = JSON.parse(str); //由JSON字符串转换为JSON对象
    ```
### >1.jquery开源类库
- [Linqa.js 这是一个帮助我们像 LINQ 一样操作数组的扩展库](https://www.oschina.net/p/linqa-js/related?lang=0&p=69&sort=time)
- [javascript开源大全](https://www.cnblogs.com/yiliweichinasoft/p/3819699.html)
- [javaScript字符串工具类StringUtils详解](https://www.jb51.net/article/130051.htm)
- [javascript常用工具类和公共方法合集](http://www.htmleaf.com/ziliaoku/qianduanjiaocheng/201505311944.html)
- [30个值得收藏的CSS代码片段](http://www.htmleaf.com/ziliaoku/qianduanjiaocheng/201506011951.html)
- []()
- []()