
> [jquery/js实现一个网页同时调用多个倒计时(最新的)](https://blog.csdn.net/websites/article/details/50037611)
- ready
    ```
    $(function(){
    });
    ```
- [解决 jQuery 动态新增节点无法触发 onclick 点击事件的问题](https://blog.csdn.net/zcf980/article/details/83060286)
    ```
    使用 on() 方法：推荐使用该方法
    自 jQuery 版本 1.7 起，on() 方法是 bind()、live() 和 delegate() 方法的新的替代品。该方法给 API 带来很多便利
    --------------
    $("#parent").on('click', 'child', function(){});
    --------------
    $('body').on('click', '.but', function() {});
    ```
- [jquery，字符串转json对象，json对象转字符串](https://www.cnblogs.com/alsf/p/7528104.html)
    ```
    var obj4Str=JSON.stringify(jsonobj); //可以将json对象转换成字符串
    var obj = JSON.parse(str); //由JSON字符串转换为JSON对象
    ```