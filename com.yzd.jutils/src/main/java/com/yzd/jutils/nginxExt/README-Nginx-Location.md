### Nginx之Location配置详解(Location匹配顺序)
- [Nginx之Location配置详解(Location匹配顺序)](https://blog.csdn.net/RobertoHuang/article/details/70249007)
```
Lcoation命中过程：
精确匹配=》普通匹配=》正则匹配
```
###　nginx location匹配规则
- [Nginx Location指令URI匹配规则详解小结](https://www.jb51.net/article/159631.htm)
- [nginx location匹配规则](https://www.nginx.cn/115.html)
- []()
```
5、精确匹配与模糊匹配差别
location =/ { … } 与 location / { … } 的差别： 
* 前一个是精确匹配，只响应/请求，所有/xxx或/xxx/xxxx类的请求都不会以前缀的形式匹配到它 
* 后一个是只要以 / 为前缀的请求都会被匹配到。如：/abc ， /test/abc， /test/abc/aaaa
6、正则与非正则匹配
1> location ~ /test/.+.jsp$ { … } ：正则匹配，支持标准的正则表达式语法。 
2> location ^~ / { … } ： ^~意思是关闭正则匹配，当搜索到这个普通匹配模式后，将不再继续搜索正则匹配模式。
==============================
location匹配命令
location [=|~|~*|^~|@] /uri/ { … } 或 location @name { … }
~      #波浪线表示执行一个正则匹配，区分大小写
~*    #表示执行一个正则匹配，不区分大小写
^~    #^~表示普通字符匹配，如果该选项匹配，只匹配该选项，不匹配别的选项，一般用来匹配目录
=      #进行普通字符精确匹配
@     #"@" 定义一个命名的 location，使用在内部定向时，例如 error_page, try_files
```