### 参考代码：
- [https://github.com/yaozd/com.yzd.shiro.root](https://github.com/yaozd/com.yzd.shiro.root)
### 统一处理返回值/响应体
- 统一处理的返回值可以保证swagger更友好的显示处理。
- [Springboot使用了ResponseBodyAdvice处理返回值异常？](http://www.th7.cn/Program/java/201709/1256116.shtml)
```
public class BaseGlobalResponseBodyAdvice implements ResponseBodyAdvice {
//这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
@Override
public boolean supports(MethodParameter methodParameter, Class aClass) {
return true;
}
//对于返回的对象如果不是最终对象ResponseResult，则选包装一下
@Override
public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType,
Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
if(!(o instanceof ResponseResult)) {
ResponseResult responseResult = new ResponseResult(CodeConstant.SUCCESS, o);
//因为handler处理类的返回类型是String，为了保证一致性，这里需要将ResponseResult转回去
if(o instanceof String) {
return JSON.toJSONString(responseResult);
}
return responseResult;
}
return o;
}
} 
最终解决了这个问题。
```
- [spring boot @ControllerAdvice，ResponseBodyAdvice 统一处理返回值/响应体](http://blog.sina.com.cn/s/blog_c16346d00102xa5x.html)
```
1、对返回的json进行封装，@ControllerAdvice，ResponseBodyAdvice

2、封装的实体：
public class WsResponse {
private String code;
private String message;
private T result;

public static WsResponse fail(String message){
WsResponse ws =  new WsResponse();
ws.setCode("0002");
ws.setMessage(message);
return ws;
}
public static WsResponse success(String message){
WsResponse ws =  new WsResponse();
ws.setCode("0001");
ws.setMessage(message);
return ws;
}
public static WsResponse success(){
WsResponse ws =  new WsResponse();
ws.setCode("0001");
ws.setMessage("sccess");
return ws;
}
public static WsResponse success(T result){
WsResponse ws =  new WsResponse();
ws.setCode("0001");
ws.setResult(result);
return ws;
}

3、对返回json进行封装
@ControllerAdvice
public class WsResponseBodyAdvice implements ResponseBodyAdvice {
//obj是原始的json数据
public Object beforeBodyWrite(Object obj, MethodParameter arg1, MediaType mediaType, Class arg3, ServerHttpRequest arg4,
ServerHttpResponse arg5) {
if(obj==null && mediaType.equals(MediaType.APPLICATION_JSON)){
return WsResponse.success();
}else if(obj instanceof WsResponse){
return obj;
}else{
return WsResponse.success(obj);
}
}

public boolean supports(MethodParameter arg0, Class arg1) {
return true;
}

}

4、封装后返回结果
http://localhost:8080/api-service/services/user/findById/1

{"code":"0001","message":null,"result":{"id":1,"userName":"test1","age":20,"address":"武汉街道口","phone":null,"sex":"BOY","creatDate":1510738157326,"updateDate":null}}
```