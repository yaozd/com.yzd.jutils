## [SpringBoot通过RequestContextHolder获取HttpRequest和HttpResponse](https://blog.csdn.net/suddev/article/details/79464574)

```
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
//
ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
HttpServletRequest request = requestAttributes.getRequest();
HttpServletResponse response = requestAttributes.getResponse();
//
如果当前请求的数据：上传文件，这此方法会抛异常
```
