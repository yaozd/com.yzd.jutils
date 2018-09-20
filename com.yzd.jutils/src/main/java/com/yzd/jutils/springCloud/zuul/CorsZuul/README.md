###　[SpringCloud 从整体上解决跨域问题_zuul网关配置实现跨域](https://blog.csdn.net/lidew521/article/details/82625296)

```
版权声明：本文为博主原创文章，未经博主credreamer 允许不得转载 违者追究法律责任。	https://blog.csdn.net/lidew521/article/details/82625296
  JAVA技术交流QQ群：170933152 

自己用的:

E:\IdeaWkSpace\SmartCommunity\sc-gateway\src\main\java\cn\gov\rongcheng\scgateway\config\GatewayConfig.java

package cn.gov.rongcheng.scgateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Component
@Configuration
public class GatewayConfig
{
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        //corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
-----------------------------------------------------------------------------

配置:

E:\IdeaWkSpace\SmartCommunity\sc-gateway\src\main\resources\application.properties

spring.application.name=sc-gateway
server.port=8040
zuul.host.socket-timeout-millis=60000
zuul.host.connect-timeout-millis=10000
#zuul.routes.api-a.path=/producer/**
#zuul.routes.api-a.url=spring-cloud-producer

#zuul.sensitive-headers="Cookie", "Set-Cookie", "Authorization")
zuul.sensitive-headers="Cookie","Set-Cookie"

eureka.client.serviceUrl.defaultZone=http://localhost:8000/eureka/

#暂时先不监控接口调用速度监测
#spring.zipkin.base-url=http://localhost:9000
#spring.sleuth.sampler.percentage=1.0
----------------------------------------------------------------------------------------------------------

下面这个:是从网络上找的...

 

云环境中每个服务自己有跨域解决方案，而网关需要做最外层的跨域解决方案.如果服务已有跨域配置网关也有，会出现*多次配置问题。
head中
multiple Access-Control-Allow-Origin
使用ZUUL配置忽略头部信息
zuul:
#需要忽略的头部信息，不在传播到其他服务
  sensitive-headers: Access-Control-Allow-Origin
  ignored-headers: Access-Control-Allow-Origin,H-APP-Id,Token,APPToken
跨域配置
@Component
@Configuration
public class GateWayCorsConfig
{
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
 
```