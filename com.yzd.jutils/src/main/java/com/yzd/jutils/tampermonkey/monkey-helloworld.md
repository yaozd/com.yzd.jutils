## helloworld
1. [jQuery �̳�](https://www.runoob.com/jquery/jquery-tutorial.html)
2. [jQuery��������-�ɿ��ٲ��Խű�](https://www.runoob.com/try/try.php?filename=tryjquery_hide)
3. [�ͺ�ű�-��������](https://www.cnblogs.com/dachie/archive/2012/09/28/2706508.html)

```
һ���ܺ���Ķ����ͺ�ű�ӳ������!

˲������Լ����JS��ͦ�á����Ű�ѧϰ��̬�ȣ������ϵ�ʱ�����˸�С��DEMO��http://www.dachie.com/userscript/

// ==UserScript==
// @name          Hello World
// @namespace     http://diveintogreasemonkey.org/download/
// @description   example script to alert "Hello world!" on every page
// @include       *
// @exclude       http://diveintogreasemonkey.org/*
// @exclude       http://www.diveintogreasemonkey.org/*
// ==/UserScript==

����ṹ��������������

������// @require http://code.jquery.com/jquery-1.8.2.js һЩ����Ҫ����⵽�ͺ�ű���ʹ��
```
## ��д���Խű�
1. [����jQuery���]

```
// @require http://code.jquery.com/jquery-1.8.2.js һЩ����Ҫ����⵽�ͺ�ű���ʹ��
```
2. [��д��һ���ͺ�(Greasemonkey)���](http://www.360doc.com/content/11/1216/22/1162553_172819386.shtml)
3. Demo-01

```
// ==UserScript==
// @name baiduͼƬ����
// @version 20160402.1113
// @author qhq
// @homepageURL    https://greasyfork.org/zh-CN/scripts/14710
// @supportURL		https://greasyfork.org/zh-CN/scripts/14710/feedback
// @icon         https://assetcdn.500px.org/assets/favicon-1e8257b93fb787f8ceb66b5522ee853c.ico
// @description  baiduͼƬ�����������ͼƬ��ť
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
// @name baiduͼƬ����
// @version 20160402.1113
// @author qhq
// @homepageURL    https://greasyfork.org/zh-CN/scripts/14710
// @supportURL		https://greasyfork.org/zh-CN/scripts/14710/feedback
// @icon         https://assetcdn.500px.org/assets/favicon-1e8257b93fb787f8ceb66b5522ee853c.ico
// @description  baiduͼƬ�����������ͼƬ��ť
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
## jQuery�������ݾ���ȡ
1.��֤��test
```
<script type="text/javascript">
  $(function(){
   $(":input[name='email']").blur(function(){
    var email = $(this).val();
    var reg = /\w+[@]{1}\w+[.]\w+/;
    if(reg.test(email)){
     $(":input[name='check']").val("email�Ϸ�");
    }else{
     $(":input[name='check']").val("��������ȷ��email��ַ");
    }
   });
  });
 </script>
```
2.ʾ����jsʹ��������ʽgroup����ȡ�ַ����е�����-match
```
https://blog.csdn.net/diandian82/article/details/9023707
//
var linkStr = "/black-mountain/35-cotton-creek-cir-black-mountain-nc-421_537763.html";
// ���ű�ʾ�顣���ʿ�����group[index]������ÿ�����Ϣ
var linkRegx = /\/([^\/]+)\/.+-(\d+)_(\d+).html/;
var group = linkStr.match(linkRegx);
console.log(group);
/*
������Ϊ��
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
3.�滻��replace
```
https://www.jb51.net/article/43286.htm
<script language="JavaScript"> 
var myReg = /(w+)s(w+)/; 
var str  = "John Smith"; 
var newstr = str.replace(myReg, "$2, $1"); 
document.write(newstr); 
</script>
�����"Smith, John" 
```
4.js������match��exec��test��search��replace��splitʹ�ý��ܼ���
```
1.split
function SplitDemo(){ 
var s, ss; 
var s = "The rain in Spain falls mainly in the plain."; 
// ������ʽ���ò��ִ�д��s���зָ��� 
ss = s.split(/s/i); 
return(ss); 
} 
document.write(SplitDemo()); 
2.
js������ʽ֮exec()������match()�����Լ�search()���� 
 
�ȿ����룺

var sToMatch = "test, Tes, tst, tset, Test, Tesyt, sTes";
var reEs = /es/gi;
alert(reEs.exec(sToMatch));
alert(sToMatch.match(reEs));
alert(sToMatch.search(reEs));


```