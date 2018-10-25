## web界面的快照功

### [如何实现web界面的快照功能-html2canvas](https://blog.csdn.net/junzhen_chen/article/details/72581366)
```
利用 html2canvas插件快速实现快照功能
用法： 下载并引入html2cancas.js文件，并直接调用html2canvas函数，传入参数
参数说明： 
1.需要快照的区域元素

2. 配置对象

        html2canvas($("#main-container"), {
                        allowTaint: true,
                        taintTest: false,
                        onrendered: function(canvas) {
                                    //通过canvas得到快照图片的base64编码
                        var dataUrl = canvas.toDataURL("image/jpeg",0.7);
                        }
                    });
html2cancas官网路径：http://html2canvas.hertzen.com

```
### [使用phantomjs无头浏览器生成pdf和网页快照说明文档](http://www.itbaby.me/blog/5978a508f0b1d613bdb04f42)

### html2canvas与phantomjs对比
```
html2canvas生成的图片比phantomjs小。只有100多KB
```