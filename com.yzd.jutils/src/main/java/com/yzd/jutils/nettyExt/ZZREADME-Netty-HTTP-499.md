## nginx 499 问题分析
```
如果响应体不完整，则请求会被挂起
```
### 如何判定一个请求的响应是否完成
1. 只有header，没有body的响应
    ```
    1.HEADER类型请求，响应体可以没有body,只有header
    2.GET,POST等正常类型请求，响应码为1xx,204,304的时候，可以没有body
    PS:
    HTTP 1.1
    https://tools.ietf.org/html/rfc2616#section-4.3
    HTTP 1.0
    https://tools.ietf.org/html/rfc1945#section-7.2
    The entity body (if any) sent with an HTTP request or response is in
    a format and encoding defined by the Entity-Header fields.
    
        Entity-Body    = *OCTET
    
    An entity body is included with a request message only when the
    request method calls for one. The presence of an entity body in a
    request is signaled by the inclusion of a Content-Length header field
    in the request message headers. HTTP/1.0 requests containing an
    entity body must include a valid Content-Length header field.
    
    For response messages, whether or not an entity body is included with
    a message is dependent on both the request method and the response
    code. All responses to the HEAD request method must not include a
    body, even though the presence of entity header fields may lead one
    to believe they do. All 1xx (informational), 204 (no content), and
    304 (not modified) responses must not include a body. All other
    responses must include an entity body or a Content-Length header
    field defined with a value of zero (0).
   
    ```
2. 通过响应的header中标识 Transfer-Encoding与Content-Length
   ```
   GET,POST等正常类型请求,可以通过header中Transfer-Encoding与Content-Length来识别
   Transfer-Encoding:chunked
   或
   Content-Length:XXX 
   PS:
   Content-Length的长度必须与body的长度一致或小于才可以。（小于时则解析body不完整）
   
   ```
3. 数据发送完，关闭连接方式
   ```
   比较特殊的情况，响应码非1xx,204,304，同时响应的header中不存在Transfer-Encoding与Content-Length标识的时候
   则发送EMPTY_LAST_CONTENT完，响应后关闭连接
   ```
4. 特别说明：使用http2协议可不遵守上面的这些规范
   ```
   例如：
   https://www.cnblogs.com/nxlhero/ajax/sidecolumn.aspx
   ```
