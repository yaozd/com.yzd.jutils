### [HTML页面的加载顺序](https://blog.csdn.net/m0_37550086/article/details/77513676)
```
从上到下运行，先解析head标签中的代码，
（1）head标签中会包含一些引用外部文件的代码，从开始运行就会下载这些被引用的外部文件
         当遇到script标签的时候
         浏览器暂停解析（不是暂停下载），将控制权交给JavaScript引擎（解释器）
         如果<script>标签引用了外部脚本，就下载该脚本，否则就直接执行，执行完毕后将控制权交给浏览器渲染引擎
（2）当head中代码解析完毕，会开始解析body中的代码
         如果此时head中引用的外部文件没有下载完，将会继续下载
         浏览器解析body代码中的元素，会按照head中声明一部分样式去解析
         如果此时遇到body标签中的<script>，同样会将控制权交给JavaScript引擎来解析JavaScript
         解析完毕后将控制权交还给浏览器渲染引擎。
         当body中的代码全部执行完毕、并且整个页面的css样式加载完毕后，css会重新渲染整个页面的html元素。
（3）按照之前的描述，<script>写到body标签内靠后比较好，
         因为JavaScript 会操作html元素， 如果在body加载完之前写JavaScript 会造成JavaScript 找不到页面元素
         但是我们经常将<script>写到head中，body中不会有大量的js代码，body中的html代码结构会比较清晰
         window.onload： 等待页面中的所有内容加载完毕之后才会执行
         $(document).ready()： 页面中所有DOM结构绘制完毕之后就能够执行
         可以这样理解：window.onload 和 $(document).ready()/$(function(){}); 相当于  写在body 内  最靠后的<script> 代码段
```