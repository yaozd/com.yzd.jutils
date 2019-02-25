### [fis介绍-来自Baidu](http://fis.baidu.com/fis3/docs/beginning/intro.html)
```
FIS3 是什么？
FIS3 是面向前端的工程构建工具。解决前端工程中性能优化、
资源加载（异步、同步、按需、预加载、依赖管理、合并、内嵌）、
模块化开发、自动化工具、开发规范、代码部署等问题。

```
> [安装 Node 和 NPM](http://fis.baidu.com/fis3/docs/beginning/install.html)-必须安装指定版本<br>
[Node-Previous Releases](https://nodejs.org/en/download/releases/)
```
目前使用Node 6.x版
Node 版本要求 0.8.x，0.10.x, 0.12.x，4.x，6.x，不在此列表中的版本不予支持。最新版本 node 支持会第一时间跟进，支持后更新支持列表。
```

> [FIS安装](http://fis.baidu.com/fis3/docs/beginning/install.html)

> [FIS-常用插件列表](http://fis.baidu.com/fis3/docs/common-plugin.html)
```
特别推荐插件：
1.fis3-deploy-replace（发版替换插件）
```
> FIS WEBPACK区别
- [我想问下fis3和webpack有什么区别](https://div.io/topic/1623)
- [Fis3构建迁移Webpack之路](https://www.cnblogs.com/cpselvis/p/7859665.html)
- [前端构建工具漫谈，fis3、webpack、rollup.js-推荐参考byArvin](https://zhuanlan.zhihu.com/p/20933749)
- [基于fis3的纯前端解决方案，拿来即用的fis3脚手架](https://github.com/yanhaijing/fis3-base)-fis-conf.js

> [FIS-项目中使用fis常见问题汇总](http://feg.netease.com/archives/fis-questions.html)
```
小组引入fis工具已经很久了，但是随着fis的推广以及新人的加入，一些常见的关于fis问题，经常被重复问到，所以本文整理汇总了fis使用过程中常见的坑。

1、node版本的问题
node版本更新有两条主线，一条是大版本更新，比如v5.6.0，官网默认推荐下载的版本是这条；另一条是小版本更新，比如v0.12.1。各版本详细下载地址

而目前fis2和fis3都是基于node小版本那条线，所以一开始安装node的时候就要注意下。否则即使安装成功，后期用着会发现某些fis插件出错的问题。

2、fis2新版本无法自动编译刷新问题
虽然目前推荐用fis3，但是工作中可能还会遇到fis2的旧项目，所以fis2也要安装以备不时之需。

如果是npm install -g fis直接安装的话，会默认安装fis2的最新版本，但是fis2的最新版本用了之后发现有个问题，就是无法自动编译刷新。

建议安装1.7.22版本，npm install -g fis@1.7.22

fis2版本更新目录

3、用淘宝域解决npm安装慢的问题

npm install -g xxx --registry=http://registry.npm.taobao.org
4、fis3打本地包（也叫local包）如何配置
打local包的初衷是希望，除了路径能用相对路径，其它最好都不变，比如可以合并雪碧图、可以不发布inline和temp_file这些多余的文件夹等等。
所以需要用fis3打local包的，可以参考以下配置：

//配置打本地包
fis.hook('relative');
fis.media('local')
    .match('::package', {
        spriter: fis.plugin('csssprites',{
            layout: 'matrix',
            margin: '5px'
        })
    })
    .match('*.{css,less}',{
        useSprite : true
    })
    .match('**', {
      relative: true,
      useDomain : false,
      charset : fis.get("charset"),
      deploy: fis.plugin('encoding')
    })
    .match("**.html",{
        postprocessor : fis.plugin('include',{
            nouse : true
        })
    })
    .match('**', {
      deploy: fis.plugin('local-supply', {
          to: './local',
          exclude : ['inline','temp_file']
      })
    })
5、通过我们组内发布工具发布的项目，一定不能上传带有dist和release名字的文件夹
之前有童鞋在本地打包生成dist或者release的包，不小心上传到git上面，然后再用发布系统发布，导致无法发布的报错。因为这两个文件夹是fis内部打包后需要使用的，所以项目源码不能用。切记！

6、fis重装时要注意先把server stop
组内的fis3已经把fis3和相关常用的插件集成了feg，当我们需要update所有插件（或者update某几个，但不想一个个update），你可以重装feg：npm install -g feg。那么问题来了，如果你update之前已经启动server，因为fis的update机制是先把已经存在的删除，然后再重新装的。如果server没有stop，就会导致删除不完整，重新装的时候会卡住安装不成功。当你发现卡在再想停掉server，那时候fis命令已经失效了。这种情况下只能找到那个server进程强制停了（具体哪个进程没研究）或者重启电脑。

7、usemin插件的使用要小心
组内有个关于文件合并很方便的打包插件 fis3-postpackager-usemin
使用方法很方便：

<!--usemin js(pkg/app.js)--> //js表示合并js类的文件，括号内为合并后的文件路径与名称
<script charset="gb2312" type="text/javascript" src="js/common/common.js"></script>
<script charset="gb2312" type="text/javascript" src="js/app/index.js"></script>
<!--end-->
这里存在的问题是，如果你有多个文件，合并后的文件路径和名称一定是要唯一的。这是一开始使用的时候，很多人忽略的一个问题，很有可能导致打包后，发现一个页面的资源请求不到。举个例子：
文件一：
<!--usemin js(pkg/index1.js)--> //js表示合并js类的文件，括号内为合并后的文件路径与名称
<script charset="gb2312" type="text/javascript" src="js/common/common.js"></script>
<script charset="gb2312" type="text/javascript" src="js/app/index1.js"></script>
<!--end-->
文件二：
-------
<!--usemin js(pkg/index2.js)--> //js表示合并js类的文件，括号内为合并后的文件路径与名称
<script charset="gb2312" type="text/javascript" src="js/common/common.js"></script>
<script charset="gb2312" type="text/javascript" src="js/app/index2.js"></script>
<!--end-->
8、fis编译自动补全html标签未闭合的坑
什么鬼？自动补全未闭合的标签也是坑（估计fis人员看到这个都喊冤）？！！比如：
编译前：
-------
<div class="name">
    <span>我是span标签
fis编译后：
-------
<div class="name">
    <span>我是span标签</span>
</div>
这个。。。补全本身是好事，可以修正程序员对于这种常识性的错误，但是在一些特殊场景下，可能会带来烦恼。
比如，我们的项目经常会使用include来引入一些公用的模块，那么如果刚好我的某个模块，标签闭合是分开到不同html文档的，理论上，两个文档合在一起（include服务器把一个html代码模块原封不动的合并进来的）标签是没问题。
但是，因为中间有fis编译这一步，把残缺的那个代码块文档的标签补全闭合了，那么到时候服务器include进来，实际是会出现标签多了而导致布局错乱了。
当然，正常的写法很少出现这种把同一个标签分到两个文档（这种写法很蛋疼，不利于后期人维护，非常不推荐），只是最近遇到一个13年的项目是这样做，而之前还没有用到fis编译，现在改成fis编译就出现这个问题了。所以记下来，提醒一下大家，fis编译html文档是有自动补全未闭合的标签的功能的。

9、template板里面的动态图片路径不会自己替换成绝对路径
首先何为动态图片，比如：

1
<img src="<%- picname %>"/>
这里图片是通过传入数据到模板引擎的，图片地址是变化的。理想的情况是，我传入一个相对路径，然后fis帮我替换成绝对路径，然而并不是。因为普通的模板引擎（比如baiduTemplate.js）不是html，是最终通过js渲染的，所以不会补全；而小组内用的tolist fis插件，是通过nodejs结合ejs模板引擎生成批量静态页面，这个过程fis先对模板的其它不变的静态资源补全路径（这时候img的src还是个变量），然后再生成html，所以也是无法补全的。
解决方案：
1、经过模板渲染的图片放在data下，不加md5后缀；
2、然后在数据传入模板之前，先用__CDNPATH来补全img的路径，再传入模板渲染即可

10、'git' 不是内部或外部命令，也不是可运行的程序
这个报错可能在一开始使用下面两个命令时出现：

1
2
feg get fis3 //获取基于fis3的通用模板
feg get fis3-cms //获取基于fis3的cms模板
这两个命令的作用是clone 存放在git上面的项目demo。
出现这个报错的原因是，在安装git的时候，由于某种原因导致安装成功后环境变量没加入git，所以在cmd命令敲关于git的命令都是有这个报错。
解决方案自然是在环境变量加入git就行了：
我的电脑——右键属性——高级系统设置——环境变量——系统变量——找到“path”
只需要在现有的path后面加入git的路径（需要找到自己机子git的安装路径），比如：

1
xxxxxx;C:\Program Files (x86)\Git\bin
11、（待续...）
手机阅读请扫描下方二维码：
```