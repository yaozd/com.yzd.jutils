## [Fastjson](https://www.w3cschool.cn/fastjson/fastjson-quickstart.html)-教程
```
1. 怎么获得fastjson?
你可以通过如下地方下载fastjson:
maven中央仓库: http://central.maven.org/maven2/com/alibaba/fastjson/
Sourceforge.net : https://sourceforge.net/projects/fastjson/files/
在maven中如何配置fastjson依赖 fastjson最新版本都会发布到maven中央仓库，你可以直接依赖。
```
## [fastjson的性能如何？](https://www.w3cschool.cn/fastjson/fastjson-howto.html)
- 关闭循环引用检测的功能时，性能最优
    - JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect)
    - VO vo = JSON.parseObject("...", VO.class, Feature.DisableCircularReferenceDetect)
```
fastjson是目前java语言中最快的json库，比自称最快的jackson速度要快，
第三方独立测试结果看这里：fastjson性能对比 。
自行做性能测试时，关闭循环引用检测的功能。
5. fastjson性能比gson怎样？
fastjson比gson快大约6倍，测试结果上这里：性能测试对比。gson的g可能是“龟”拼音的缩写，龟速的json库。
```
##　[Fastjson 循环引用](https://www.w3cschool.cn/fastjson/fastjson-zwic1vwn.html)
```
关闭引用支持
当序列化后的JSON传输到浏览器或者其他语言中，这些json解析器不支持循环引用，从而导致数据丢失。你可以关闭fastjson的循环引用支持。关闭引用检测，还能够提升序列化时的性能。

全局配置关闭

  JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
非全局关闭

  JSON.toJSONString(obj, SerializerFeature.DisableCircularReferenceDetect);
```

## [fastjson数据格式转换 SerializerFeature属性详解](https://www.cnblogs.com/xiang--liu/p/9710194.html)

##　java 对json 格式做参数格式校验
- [java 对json 格式做参数格式校验](https://blog.csdn.net/Dream_bin/article/details/92656876)
- [Java后台实现json字符串格式验证工具类](http://www.zuidaima.com/code/file/3605601447003136.htm?dir=/3605601447003136.java)
- [判断一个字符串是否是合法的JSON字符串](https://blog.csdn.net/aa1215018028/article/details/87916228)