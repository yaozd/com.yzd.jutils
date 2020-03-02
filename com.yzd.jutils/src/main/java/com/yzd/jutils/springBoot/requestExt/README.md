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
## uri uri_path uri_param
```
System.out.println(httpRequest.uri());
String uri = httpRequest.uri();
int i = uri.indexOf('?');
if(i>0){
    String uriParam = uri.substring(i);
    String uriPath = uri.substring(0, i);
    httpRequest.setUri("/s?"+uriParam);
}else {
    httpRequest.setUri("/s?");
}
```