


//参考：
/*
一篇关于apache commons类库的详解
http://blog.csdn.net/wiker_yong/article/details/23551209
Java工具类之Apache的Commons Lang和BeanUtils
http://rensanning.iteye.com/blog/1547845
编写更少量的代码：使用apache commons工具类库
http://www.cnblogs.com/ITtangtang/p/3966955.html
(转)Apache Commons工具集简介
http://www.cnblogs.com/jackyrong/archive/2006/10/15/529599.html
Commons Lang 2.5 API
http://tool.oschina.net/apidocs/apidoc?api=commons-lang
Welcome to Apache Commons
http://commons.apache.org/

=======================================================
Apache Commons是一个非常有用的工具包，解决各种实际的通用问题，下面是一个简述表，详细信息访问http://jakarta.apache.org/commons/index.html
BeanUtils
Commons-BeanUtils 提供对 Java 反射和自省API的包装
Betwixt
Betwixt提供将 JavaBean 映射至 XML 文档，以及相反映射的服务.
Chain
Chain 提供实现组织复杂的处理流程的“责任链模式”.
CLI
CLI 提供针对命令行参数，选项，选项组，强制选项等的简单API.
Codec
Codec 包含一些通用的编码解码算法。包括一些语音编码器， Hex, Base64, 以及URL encoder.
Collections
Commons-Collections 提供一个类包来扩展和增加标准的 Java Collection框架
Configuration
Commons-Configuration 工具对各种各式的配置和参考文件提供读取帮助.
Daemon
一种 unix-daemon-like java 代码的替代机制
DBCP
Commons-DBCP 提供数据库连接池服务
DbUtils
DbUtils 是一个 JDBC helper 类库，完成数据库任务的简单的资源清除代码.
Digester
Commons-Digester 是一个 XML-Java对象的映射工具，用于解析 XML配置文件.
Discovery
Commons-Discovery 提供工具来定位资源 (包括类) ，通过使用各种模式来映射服务/引用名称和资源名称。.
EL
Commons-EL 提供在JSP2.0规范中定义的EL表达式的解释器.
FileUpload
FileUpload 使得在你可以在应用和Servlet中容易的加入强大和高性能的文件上传能力
HttpClient
Commons-HttpClient 提供了可以工作于HTTP协议客户端的一个框架.
IO
IO 是一个 I/O 工具集
Jelly
Jelly是一个基于 XML 的脚本和处理引擎。 Jelly 借鉴了 JSP 定指标签，Velocity, Cocoon和Xdoclet中的脚本引擎的许多优点。Jelly 可以用在命令行， Ant 或者 Servlet之中。
Jexl
Jexl是一个表达式语言，通过借鉴来自于Velocity的经验扩展了JSTL定义的表达式语言。.
JXPath
Commons-JXPath 提供了使用Xpath语法操纵符合Java类命名规范的 JavaBeans的工具。也支持 maps, DOM 和其他对象模型。.
Lang
Commons-Lang 提供了许多许多通用的工具类集，提供了一些java.lang中类的扩展功能
Latka
Commons-Latka 是一个HTTP 功能测试包，用于自动化的QA,验收和衰减测试.
Launcher
Launcher 组件是一个交叉平台的Java 应用载入器。Commons-launcher 消除了需要批处理或者Shell脚本来载入Java 类。.原始的 Java 类来自于Jakarta Tomcat 4.0 项目
Logging
Commons-Logging 是一个各种 logging API实现的包裹类.
Math
Math 是一个轻量的，自包含的数学和统计组件，解决了许多非常通用但没有及时出现在Java标准语言中的实践问题.
Modeler
Commons-Modeler 提供了建模兼容JMX规范的Mbean的机制.
Net
Net 是一个网络工具集，基于 NetComponents 代码，包括 FTP 客户端等等。
Pool
Commons-Pool 提供了通用对象池接口，一个用于创建模块化对象池的工具包，以及通常的对象池实现.
Primitives
Commons-Primitives提供了一个更小，更快和更易使用的对Java基本类型的支持。当前主要是针对基本类型的 collection。.
Validator
The commons-validator提供了一个简单的，可扩展的框架来在一个XML文件中定义校验器 (校验方法)和校验规则。支持校验规则的和错误消息的国际化。
 ====

我们首先来看org.apache.commons.lang包，这个包提供了一些有用的包含static方法的Util类。除了6个Exception类和2个已经deprecated的数字类之外，commons.lang包共包含了17个实用的类：

ArrayUtils – 用于对数组的操作，如添加、查找、删除、子数组、倒序、元素类型转换等；
BitField – 用于操作位元，提供了一些方便而安全的方法；
BooleanUtils – 用于操作和转换boolean或者Boolean及相应的数组；
CharEncoding – 包含了Java环境支持的字符编码，提供是否支持某种编码的判断；
CharRange – 用于设定字符范围并做相应检查；
CharSet – 用于设定一组字符作为范围并做相应检查；
CharSetUtils – 用于操作CharSet；
CharUtils – 用于操作char值和Character对象；
ClassUtils – 用于对Java类的操作，不使用反射；
ObjectUtils – 用于操作Java对象，提供null安全的访问和其他一些功能；
RandomStringUtils – 用于生成随机的字符串；
SerializationUtils – 用于处理对象序列化，提供比一般Java序列化更高级的处理能力；
StringEscapeUtils – 用于正确处理转义字符，产生正确的Java、JavaScript、HTML、XML和SQL代码；
StringUtils – 处理String的核心类，提供了相当多的功能；
SystemUtils – 在java.lang.System基础上提供更方便的访问，如用户路径、Java版本、时区、操作系统等判断；
Validate – 提供验证的操作，有点类似assert断言；
WordUtils – 用于处理单词大小写、换行等。

 */
