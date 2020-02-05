##　重定向和转发
- [SpringBoot中处理的转发与重定向](https://blog.csdn.net/yubin1285570923/article/details/83796003) 推荐参考byArvin
- [Springboot下，实现接口重定向到其他页面或方法](https://blog.csdn.net/yy226953/article/details/88656508)
```
使用response的重定向功能

public void testRed(HttpServletResponse response) throws Exception{
	response.sendRedirect("https://www.baidu.com");
}
方便跳转外网，并可以携带参数
当方法没有返回值即返回void的时候，重定向和转发操作都是使用的servlet的api，就是：
转发：
request.getRequestDispatcher("/jsp/result.jsp").forward(request, response);
重定向：
response.sendRedirect(request.getContextPath()+"/jsp/result.jsp");
```