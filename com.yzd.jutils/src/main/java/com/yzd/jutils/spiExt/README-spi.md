## spi-Service Provider Interfaces，服务提供接口
- [Java之ServiceLoader](https://www.cnblogs.com/lighten/archive/2004/01/13/6946683.html)
```
ServiceLoader的使用是要在根目录有一个文件夹META-INF/services/
//
public class ServiceLoaderTest {
     
    public static void main(String[] args) {
        ServiceLoader<TestService> loader = ServiceLoader.load(TestService.class);
        for(TestService service : loader) {
            System.out.println(service.sayHello());
        }
    }
 
}
```