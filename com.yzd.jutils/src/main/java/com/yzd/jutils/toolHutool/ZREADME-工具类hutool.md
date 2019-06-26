## [https://github.com/looly/hutool](https://github.com/looly/hutool)
###　中文文档
- [https://www.hutool.cn/docs/#/](https://www.hutool.cn/docs/#/)
###　hutool工具类推荐-byArvin-2019-06-11-1004
- 工具类比较多，也比较实用
- 验证码的功能不错，比之前的好
```
一个Java基础工具类，对文件、流、加密解密、转码、正则、线程、XML等JDK方法进行封装，组成各种Util工具类，同时提供以下组件：

hutool-aop JDK动态代理封装，提供非IOC下的切面支持
hutool-bloomFilter 布隆过滤，提供一些Hash算法的布隆过滤
hutool-cache 缓存
hutool-core 核心，包括Bean操作、日期、各种Util等
hutool-cron 定时任务模块，提供类Crontab表达式的定时任务
hutool-crypto 加密解密模块
hutool-db JDBC封装后的数据操作，基于ActiveRecord思想
hutool-dfa 基于DFA模型的多关键字查找
hutool-extra 扩展模块，对第三方封装（模板引擎、邮件等）
hutool-http 基于HttpUrlConnection的Http客户端封装
hutool-log 自动识别日志实现的日志门面
hutool-script 脚本执行封装，例如Javascript
hutool-setting 功能更强大的Setting配置文件和Properties封装
hutool-system 系统参数调用封装（JVM信息等）
hutool-json JSON实现
hutool-captcha 图片验证码实现
hutool-poi 针对POI中Excel的封装
设计哲学
1. 方法优先于对象
在工具类中，往往以静态方法为主。
方法集中在一个类中，配合IDE查找使用起来是十分便利的。
于是Hutool将JDK中许多的类总结抽象为一个方法，这一原则使用最多的就是流的相关方法，这些方法很好的隐藏了XXXInputStream、XXXReader等的复杂性。
```

### Maven
```
hutool-all
hutool-aop
hutool-bloomFilter
hutool-cache
hutool-captcha
hutool-core
hutool-cron
hutool-crypto
hutool-db
hutool-dfa
hutool-extra
hutool-http
hutool-json
hutool-log
hutool-parent
hutool-poi
hutool-script
hutool-setting
hutool-socket
hutool-system

-----------------------------
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-core</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-aop</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-bloomFilter</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-cache</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-captcha</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-core</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-cron</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-crypto</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-db</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-dfa</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-extra</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-http</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-json</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-log</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-parent</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-poi</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-script</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-setting</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-socket</artifactId>
    <version>4.5.15</version>
</dependency>
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-system</artifactId>
    <version>4.5.15</version>
</dependency>

```
		