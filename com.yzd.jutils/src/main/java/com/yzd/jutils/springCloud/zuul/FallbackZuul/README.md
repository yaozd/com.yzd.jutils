#### 熔断处理
- [熔断处理-fallback](http://www.cnblogs.com/yjmyzz/p/spring-cloud-zuul-demo.html)

```
三、熔断处理

如果网关后面的微服务挂了，zuul还允许定义一个fallback类，用于熔断处理，参考下面的代码：

package com.cnblogs.yjmyzz.spring.cloud.study.gateway;
 
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
 
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
 
/**
 * Created by yangjunming on 2017/7/14.
 */
@Component
public class ServiceConsumerFallbackProvider implements ZuulFallbackProvider {
 
    @Override
    public String getRoute() {
        return "service-consumer";
    }
 
    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }
 
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }
 
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }
 
            @Override
            public void close() {
 
            }
 
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("Service-Consumer不可用".getBytes());
            }
 
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
开发人员只要在getRoute这个方法里指定要处理的微服务实例，然后重写fallbackResponse即可。
```