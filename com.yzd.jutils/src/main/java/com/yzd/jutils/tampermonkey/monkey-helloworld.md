## helloworld
1. [jQuery 教程](https://www.runoob.com/jquery/jquery-tutorial.html)
2. [jQuery在线运行-可快速测试脚本](https://www.runoob.com/try/try.php?filename=tryjquery_hide)
3. [油猴脚本-开发入门](https://www.cnblogs.com/dachie/archive/2012/09/28/2706508.html)

```
一个很好玩的东西油猴脚本映入眼帘!

瞬间觉得自己会点JS，挺好。本着爱学习的态度，用晚上的时间做了个小的DEMO：http://www.dachie.com/userscript/

// ==UserScript==
// @name          Hello World
// @namespace     http://diveintogreasemonkey.org/download/
// @description   example script to alert "Hello world!" on every page
// @include       *
// @exclude       http://diveintogreasemonkey.org/*
// @exclude       http://www.diveintogreasemonkey.org/*
// ==/UserScript==

整体结构基本上是这样。

还可以// @require http://code.jquery.com/jquery-1.8.2.js 一些你需要的类库到油猴脚本中使用
```
## 编写测试脚本
1. [引用jQuery类库]

```
// @require http://code.jquery.com/jquery-1.8.2.js 一些你需要的类库到油猴脚本中使用
```
2. [书写第一个油猴(Greasemonkey)插件](http://www.360doc.com/content/11/1216/22/1162553_172819386.shtml)
3. Demo-01

```
// ==UserScript==
// @name baidu图片下载
// @version 20160402.1113
// @author qhq
// @homepageURL    https://greasyfork.org/zh-CN/scripts/14710
// @supportURL		https://greasyfork.org/zh-CN/scripts/14710/feedback
// @icon         https://assetcdn.500px.org/assets/favicon-1e8257b93fb787f8ceb66b5522ee853c.ico
// @description  baidu图片下载添加下载图片按钮
// @include http*://*.baidu.com/*
// @grant none
// @namespace https://greasyfork.org/users/9065
// ==/UserScript==

(function() {
    'use strict';
    alert(1);
    // Your code here...
})();
```
4. Demo-02

```
// ==UserScript==
// @name baidu图片下载
// @version 20160402.1113
// @author qhq
// @homepageURL    https://greasyfork.org/zh-CN/scripts/14710
// @supportURL		https://greasyfork.org/zh-CN/scripts/14710/feedback
// @icon         https://assetcdn.500px.org/assets/favicon-1e8257b93fb787f8ceb66b5522ee853c.ico
// @description  baidu图片下载添加下载图片按钮
// @include http*://www.baidu.com/*
// @grant none
// @namespace https://greasyfork.org/users/9065
// ==/UserScript==

(function() {
    'use strict';
    var getBtnDownloadHtml = function (src) {
        return '<a href="' + src + '" target="_blank" download class="button medium submit">Download-byArvin</a>';
    }
    var addBtnDownload = function (src) {
        return '<a href="' + src + '" target="_blank" download class="button medium submit">Download-byArvin</a>';
    }
    var downloadHtml=getBtnDownloadHtml('http://bbs.17173.com/thread-10497400-1-2.html');
    //alert(downloadHtml);
    // Your code here...
})();
```
## jQuery正则数据据提取
1.验证：test
```
<script type="text/javascript">
  $(function(){
   $(":input[name='email']").blur(function(){
    var email = $(this).val();
    var reg = /\w+[@]{1}\w+[.]\w+/;
    if(reg.test(email)){
     $(":input[name='check']").val("email合法");
    }else{
     $(":input[name='check']").val("请输入正确的email地址");
    }
   });
  });
 </script>
```
2.示例：js使用正则表达式group来提取字符串中的数据-match
```
https://blog.csdn.net/diandian82/article/details/9023707
//
var linkStr = "/black-mountain/35-cotton-creek-cir-black-mountain-nc-421_537763.html";
// 括号表示组。访问可以用group[index]来访问每组的信息
var linkRegx = /\/([^\/]+)\/.+-(\d+)_(\d+).html/;
var group = linkStr.match(linkRegx);
console.log(group);
/*
输出结果为：
[ '/black-mountain/35-cotton-creek-cir-black-mountain-nc-421_537763.html',
  'black-mountain',
  '421',
  '537763',
  index: 0,
  input: '/black-mountain/35-cotton-creek-cir-black-mountain-nc-421_537763.html' ]
*/
```
```
 //============================================
  var getNumOfVideoSrcFun=function(imgSrcVal){
	 var reg = /thumb\/[\d]+_([\d]+).jpg/;
	 var group = imgSrcVal.match(reg);
	 var numVal=group[1];
	 //console.log(numVal);
	 return numVal;
  }
  $(".moduleFeaturedThumb").each(function(){
	var imgSrcVal=$(this)[0].src;
    console.log(imgSrcVal);
	var numOfVideoSrc=getNumOfVideoSrcFun(imgSrcVal);
	console.log(numOfVideoSrc);
  });
  //============================================
```
3.替换：replace
```
https://www.jb51.net/article/43286.htm
<script language="JavaScript"> 
var myReg = /(w+)s(w+)/; 
var str  = "John Smith"; 
var newstr = str.replace(myReg, "$2, $1"); 
document.write(newstr); 
</script>
将输出"Smith, John" 
```
4.js正则函数match、exec、test、search、replace、split使用介绍集合
```
1.split
function SplitDemo(){ 
var s, ss; 
var s = "The rain in Spain falls mainly in the plain."; 
// 正则表达式，用不分大不写的s进行分隔。 
ss = s.split(/s/i); 
return(ss); 
} 
document.write(SplitDemo()); 
2.
js正则表达式之exec()方法、match()方法以及search()方法 
 
先看代码：

var sToMatch = "test, Tes, tst, tset, Test, Tesyt, sTes";
var reEs = /es/gi;
alert(reEs.exec(sToMatch));
alert(sToMatch.match(reEs));
alert(sToMatch.search(reEs));


```