
### [jquery 选择器(name,属性,元素)大全](https://www.cnblogs.com/hanqishihu/p/6096977.html)

```
jQuery 选择器大体上可分为：基本选择器、层次选择器、过滤选择器、表单选择器。 其中过滤选择器可以分为：简单过滤选择器、内容过滤选择器、可见性过滤选择器、属性过滤选择器、子元素过滤选择器、表单对象属性过滤选择器。选择器是jQuery最基础的东西，下面向大家介绍jquery+%D1%A1%D4%F1%C6%F7/" target="_blank">jquery 选择器的用法

选择器是jQuery的核心组成部分，因为使用jQuery操作DOM时所做的每件事都和选择器密切相关——总得先选取元素才可进行下一步。jQuery使用常见的CSS选择器和XPATH选择器，它们为绝大多数Web设计师和开发者所熟悉。除此之外，还有一些jQuery自定义的选择器。 正是这些选择器使得jQuery分外灵活，易于学习。理解选择器如何工作，才能为充分利用jQuery的强大功能打好坚实基础。 在那些CSS选择器力不从心的场合，过滤器可以让你更灵活地根据DOM特性选取元素。人们常常结合使用过滤器和选择器，以便在基于某一标准选择特定元素时进行深度控制，比如需要根据元素在一组元素中的位置，或元素的可见性，或表单元素的某些属性（如选中/未选中或是否被禁用）选取元素时。jQuery提供了一系列为Dom元素添加、删除CSS类或直接设定样式的方法。 

基本选择器

1. id选择器（指定id元素）
将id="one"的元素背景色设置为黑色。（id选择器返单个元素）

$(document).ready(function () {
        $('#one').css('background', '#000');
    });2. class选择器（遍历css类元素）
将class="cube"的元素背景色设为黑色

$(document).ready(function () {
        $('.cube').css('background', '#000');
    });3. element选择器（遍历html元素）
将p元素的文字大小设置为12px

$(document).ready(function () {
        $('p').css('font-size', '12px');
    });4. * 选择器（遍历所有元素）
$(document).ready(function () {
        // 遍历form下的所有元素，将字体颜色设置为红色
        $('form *').css('color', '#FF0000');
    });5. 并列选择器
$(document).ready(function () {
        // 将p元素和div元素的margin设为0
        $('p, div').css('margin', '0');
    });

层叠选择器： 
 
$("form input") 选择所有的form元素中的input元素 
$("#main > *") 选择id值为main的所有的子元素 
$("label + input") 选择所有的label元素的下一个input元素节点，经测试选择器返回的是label标签后面直接跟一个input标签的所有input标签元素 
$("#prev ~ div") 同胞选择器，该选择器返回的为id为prev的标签元素的所有的属于同一个父元素的div标签 
 
 
 
过滤选择器
 
 
基本过滤选择器： 
 
$("tr:first") 选择所有tr元素的第一个 
$("tr:last") 选择所有tr元素的最后一个 
$("input:not(:checked) + span") 过滤掉：checked的选择器的所有的input元素 
 
$("tr:even") 选择所有的tr元素的第0，2，4... ...个元素（注意：因为所选择的多个元素时为数组，所以序号是从0开始） 
 
$("tr:odd") 选择所有的tr元素的第1，3，5... ...个元素 
$("td:eq(2)") 选择所有的td元素中序号为2的那个td元素 
$("td:gt(4)") 选择td元素中序号大于4的所有td元素 
$("td:ll(4)") 选择td元素中序号小于4的所有的td元素 
 
 
内容过滤选择器： 
 
$("div:contains('John')") 选择所有div中含有John文本的元素 
$("td:empty") 选择所有的为空（也不包括文本节点）的td元素的数组 
$("div:has(p)") 选择所有含有p标签的div元素 
$("td:parent") 选择所有的以td为父节点的元素数组 
 
 
可视化过滤选择器： 
 
$("div:hidden") 选择所有的被hidden的div元素 
$("div:visible") 选择所有的可视化的div元素 
 
 
属性过滤选择器： 
 
$("div[id]") 选择所有含有id属性的div元素 
$("input[name='newsletter']") 选择所有的name属性等于'newsletter'的input元素 
 
$("input[name!='newsletter']") 选择所有的name属性不等于'newsletter'的input元素 
 
$("input[name^='news']") 选择所有的name属性以'news'开头的input元素 
$("input[name$='news']") 选择所有的name属性以'news'结尾的input元素 
$("input[name*='man']") 选择所有的name属性包含'news'的input元素 
 
$("input[id][name$='man']") 可以使用多个属性进行联合选择，该选择器是得到所有的含有id属性并且那么属性以man结尾的元素 
 
 
子元素过滤选择器： 
 
$("div span:first-child") 返回所有的div元素的第一个子节点的数组 
$("div span:last-child") 返回所有的div元素的最后一个节点的数组 
$("div button:only-child") 返回所有的div中只有唯一一个子节点的所有子节点的数组 
 
 
表单元素选择器： 
 
$(":input") 选择所有的表单输入元素，包括input, textarea, select 和 button 
$(":text") 选择所有的text input元素 
$(":password") 选择所有的password input元素 
$(":radio") 选择所有的radio input元素 
$(":checkbox") 选择所有的checkbox input元素 
$(":submit") 选择所有的submit input元素 
$(":image") 选择所有的image input元素 
$(":reset") 选择所有的reset input元素 
$(":button") 选择所有的button input元素 
$(":file") 选择所有的file input元素 
$(":hidden") 选择所有类型为hidden的input元素或表单的隐藏域 
 
 
表单元素过滤选择器： 
 
$(":enabled") 选择所有的可操作的表单元素 
$(":disabled") 选择所有的不可操作的表单元素 
$(":checked") 选择所有的被checked的表单元素 
$("select option:selected") 选择所有的select 的子元素中被selected的元素

下面拿张表给大家参考

 

 

选择器	描述	返回	示例
基本选择器
#id	根据给定的id匹配一个元素	单个元素	$("#test")选取id为test的元素
.class	根据给定的类名匹配元素	集合元素	$(".test")选取class为test的元素
element	根据给定的元素名匹配元素	集合元素	$("p")选取所有的p元素
*	匹配所有元素	集合元素	$("*")选取所有元素
selector1,selector2,.....,selectorN	将每一个选择器匹配到的元素合并后一起返回	集合元素	$("div,span,p.myClass")选取所有div,span和拥有class为myClass的p标签的一组元素
层次选择器
$("ancestor descendant")	选取ancestor元素里的所有descendant(后代)元素	集合元素	$("div span")选取所有div里的所有的span元素
$("parent > child")	选取parent元素下的child(子)元素，与$("ancestor descendant")有区别，$("ancestor descendant")选择的是后代元素	集合元素	$("div>span")选取div元素下的名是span的子元素
$("prev + next")	选取紧接在prev元素后的next元素	集合元素	$(".one+div")选取class为one的下一个div元素
$("prev~siblings")	选取prev元素之后的所有siblings元素	集合元素	$("#two~div")选取id为two的元素后面的所有div兄弟元素
基本过滤选择器
:first	选取第一个元素	单个元素	$("div:first")选取所有div元素中的第一个div元素
:last	选取最后一个元素	单个元素	$("div:last")选取所有div元素中的最后一个div元素
:not(selector)	去除所有与给定选择器匹配的元素	集合元素	$("input:not(.myclass)")选取class不是myclass的元素
:even	选取索引是偶数的所有元素，索引是从0开始	集合元素	$("input:event")选取索引是偶数的input元素
:odd	选取索引是奇数的所有元素，索引是从0开始	集合元素	$("input:odd")选取索引是奇数的input元素
:eq(index)	选取索引等于index的元素(index从0开始)	集合元素	$("input:eq(1)")选取索引等于1的input元素
:gt(index)	选取索引大于index的元素(index从0开始)	集合元素	$("input:gt(1)")选取索引大于1的input元素(注：大于1，而不包括1)
:lt(index)	选取索引小于index的元素(index从0开始)	集合元素	$("input:lt(1)")选取索引大于1的input元素(注：小于1，而不包括1)
:header	选取所有的标题元素，例如h1,h2,h3等	集合元素	$(":header")选取网页中所有的h1,h2,h3...
:animated	选取当前正在执行动画的所有元素	集合元素	$("div:animted")选取正在执行动画的div元素
内容过滤选择器
:contains(text)	选取文本内容为"text"的元素	集合元素	$("div:contains('我')")选取含有文本"我"的div元素
:empty	选取不包含子元素或者文本的空元素	集合元素	$(div:empty)选取不包含资源(包括文本元素)的div空元素
:has(selector)	选取含有选择器所匹配的元素的元素	集合元素	$("div:has(p)")选取含有p元素的div元素
:parent	选取含有子元素或者文本的元素	集合元素	$("div:parent")选取拥有子元素(包括文本元素)的div元素
可见性过滤选择器
:hidden	选取所有不可见的元素	集合元素	$(":hidden")选取所有不可见的元素。包括《input type="hidden"/》,《div style="display:none"》和《div style="visibility:hidden"》等元素。如果只想选取《input》元素，可以使用$("input:hidden")
:visible	选取所有可见的元素	集合元素	$("div:visible")选取所有可见的div元素
属性过滤选择器
[attribute]	选取拥有此属性的元素	集合元素	$("div[id]")选取拥有属性id的元素
[attribute=value]	选取属性的值为value的元素	集合元素	$("div[title=test]")选取属性title为test的div元素
[attribute!=value]	选取属性的值不等于value的元素	集合元素	$("div[title!=test]")选取属性title不等于"test"的divy元素(注意：没有属性title的div元素也会被选取)
[attribute^=value]	选取属性的值以value开始的元素	集合元素	$("div[title^=test]")选取属性title以"test"开始的div元素
[attribute$=value]	选取属性的值以value结束的元素	集合元素	$("div[title$=test]")选取属性title以"test"结束的div元素
[attribute*=value]	选取属性的值含有value的元素	集合元素	$("div[titel*=test]")选取属性title含有'test'的div元素
[selector1][selector2][selectorN]	用属性选择器合并成一个复合属性选择器，满足多个条件。每选择一次，缩小一次范围。	集合元素	$("div[id][title$='test']")选取拥有属性id,并且属性title以"test"结束的div元素
子元素过滤选择器
:nth-child(index/event/odd/equation)	选取每个父元素下的第index个子元素或者奇偶元素.(index从1算起)	集合元素	:eq(index)只匹配一个元素，而:nth-child将为每一个父元素匹配元素，并且:nth-child(index)的index是从1开始的，而:eq(index)是从0算起的
:frist-child	选取每个父元素的第一个子元素	集合元素	:first只返回单个元素，而:first-child选择符将为每个父元素匹配第一个子元素。例如$("ul li:first-child");选取每个ul中的第一个li元素
:last-child	选取每个父元素的最后一个子元素	集合元素	同样，:last只返回单个元素，而:last-child选择符将为每个父元素匹配最后一个子元素。例如$("ul li:last-child")；选择每个ul中的最后一个li元素
:only-child	如果某个元素是它父元素中唯一的子元素，那么将会被匹配。如果父元素中含有其他元素，则不会被匹配	集合元素	$(ul li:only-child)在ul中选取是唯一子元素的li元素
表单对象属性过滤选择器
:enabled	选取所有可用元素	集合元素	$("#form1 :enabled")选取id为"form1"的表单内的所有可用元素
:disabled	选取所有不可用元素	集合元素	$("#form2:disabled")选取id为"form2"的表单内的所有不可用元素
:checked	选取所有被选中的元素(单选框，复选框)	集合元素	$("input :checked")选取所有被选中的input元素
:selected	选取所有被选中的选项元素(下拉列表)	集合元素	$("select:selected");选取所有被选中的选项元素
表单对象属性过滤选择器
集合元素	集合元素	集合元素	集合元素a
插入节点的方法
方法	描述	示例
append()	向每个匹配的元素内部追加内容	HTML代码:
《p》我想说《/p》
jQuery代码：
$("p").append("《b》你好《/b》")；
结果：
《p》我想说：《b》你好《/b》《/p》
appendTo()	将所有匹配的元素追到到指定的元素中。实际上，使用该方法是颠倒了常规$(A).append(B)的操作，既不是将B追到到A中，而是将A追加到B中	HTML代码:
《p》我想说《/p》
jQuery代码：
$(《b》你好《/b》).appendTo("p")；
结果：
《p》我想说：《b》你好《/b》《/p》
prepend()	像每个匹配的元素内部前置内容	HTML代码:
《p》我想说《/p》
jQuery代码：
$("p").prepend("《b》你好《/b》")；
结果：
《p》《b》你好《/b》我想说：《/p》
prependTo()	将所有匹配的元素前置到指定的元素中。实际上，使用该方法是颠倒了常规的$(A).prepend(B)的操作，既不是将B前置到A中，而是将A前置到B中	HTML代码:
《p》我想说《/p》
jQuery代码：
$("《b》你好《/b》").prependTo("P")；
结果：
《p》《b》你好《/b》我想说：《/p》
after	在每个匹配的元素之后插入内容	HTML代码:
《p》我想说《/p》
jQuery代码：
$("p").after("《b》你好《/b》")；
结果：
《p》我想说：《/p》《b》你好《/b》
insertAfter()	将所有匹配的元素插入到指定元素的后面。实际上使用该方法是颠倒了常规的$(A).after(B)的操作，既不是将B插入到A后面，而是将A插入到B后面	HTML代码:
《p》我想说《/p》
jQuery代码：
$("《b》你好《/b》").insertAfter("p")；
结果：
《p》我想说：《/p》《b》你好《/b》
before()	在每个匹配的元素之前插入内容	HTML代码:
《p》我想说《/p》
jQuery代码：
$("p").before("《b》你好《/b》")；
结果：
《b》你好《/b》《p》我想说：《/p》
insertBefore()	将所有匹配的元素插入到指定的元素的前面。实际上，使用该方法是颠倒了常规的$(A).before(B)的操作，既不是将B插入到A前面，而是将A插入到B前面	HTML代码:
《p》我想说《/p》
jQuery代码：
$("《b》你好《/b》").insertBefore("p")；
结果：
《b》你好《/b》《p》我想说：《/p》
load()方法参数解释
参数名称	类型	说明
url	String	请求HTML页面的URL地址
data(可选)	Object	发送至服务器的key/value数据
callback(可选)	Function	请求完成时的回调函数，无论请求成功或者失败
$.get()方法参数解释
 

参数名称	类型	说明
url	String	请求的HTML页的URL地址
data(可选)	Object	发送至服务器的key/value数据会作为QueryString附加到请求URL中
callback(可选)	Function	载入成功时回调函数(只有当Response的返回状态是success才调用该方法)自动将请求结果和状态传递给该方法
type(可选)	String	服务器返回内容的格式，包括xml.html.script.json.text和_default
```