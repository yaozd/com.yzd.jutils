##　重定向和转发
- [spring boot 的重定向和转发](https://blog.csdn.net/a15123837995/article/details/83620129)
- [Springboot下，实现接口重定向到其他页面或方法](https://blog.csdn.net/yy226953/article/details/88656508)
```
使用response的重定向功能

public void testRed(HttpServletResponse response) throws Exception{
	response.sendRedirect("https://www.baidu.com");
}
方便跳转外网，并可以携带参数

```