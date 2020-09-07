### Feign基础教程-byArvin推荐
- [Spring Cloud_13_Feign第三方注解与请求拦截器-zipkin](https://blog.csdn.net/zhaozao5757/article/details/79445196)
- [Feign真正正确的使用方法](https://www.jianshu.com/p/3d597e9d2d67)
- [Feign基础教程](https://blog.csdn.net/u010862794/article/details/73649616)
- [feign官网示例详解](http://p.codekk.com/detail/Android/OpenFeign/feign)
- [Feign不支持PATCH请求的解决办法](https://blog.csdn.net/menggudaoke/article/details/77884674)

### Feign使用问题集合：
- 问题：java.lang.IllegalStateException: Method has too many Body parameters

```
@RequestLine("POST /doLogin")
//前提：独产使用feign的情况
//问题：java.lang.IllegalStateException: Method has too many Body parameters
//@RequestParam("sign")调整为 @Param("sign")String sign
//String getBaseToken(GetBaseTokenForm form, @RequestParam("sign")String sign);
String getBaseToken(GetBaseTokenForm form, @Param("sign")String sign);
```
## Feign入门使用详解
- [Java探索之Feign入门使用详解](https://www.jb51.net/article/126976.htm)
```
// 通用API
interface BaseAPI {
 @RequestLine("GET /health")
 String health();
 @RequestLine("GET /all")
 List<Entity> all();
}
// 继承通用API
interface CustomAPI extends BaseAPI {
 @RequestLine("GET /custom")
 String custom();
}
// 各种类型有相同的表现形式，定义一个统一的API
@Headers("Accept: application/json")
interface BaseApi<V> {
 @RequestLine("GET /api/{key}")
 V get(@Param("key") String key);
 @RequestLine("GET /api")
 List<V> list();
 @Headers("Content-Type: application/json")
 @RequestLine("PUT /api/{key}")
 void put(@Param("key") String key, V value);
}
// 根据不同的类型来继承
interface FooApi extends BaseApi<Foo> { }
interface BarApi extends BaseApi<Bar> { }
//
interface GitHub {
 @RequestLine("GET /repos/{owner}/{repo}/contributors")
 List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);
 @RequestLine("GET /users/{username}/repos?sort={sort}")
 List<Repo> repos(@Param("username") String owner, @Param("sort") String sort);
 default List<Repo> repos(String owner) {
  return repos(owner, "full_name");
 }
 /**
  * Lists all contributors for all repos owned by a user.
  */
 default List<Contributor> contributors(String user) {
  MergingContributorList contributors = new MergingContributorList();
  for(Repo repo : this.repos(owner)) {
   contributors.addAll(this.contributors(user, repo.getName()));
  }
  return contributors.mergeResult();
 }
 static GitHub connect() {
  return Feign.builder()
        .decoder(new GsonDecoder())
        .target(GitHub.class, "https://api.github.com");
 }
}

```
### http请求神器之Feign
- [Spring Cloud_13_Feign第三方注解与请求拦截器-zipkin](https://blog.csdn.net/zhaozao5757/article/details/79445196)
- [Spring Cloud Feign 使用Apache的HTTP Client替换Feign原生httpclient](https://blog.csdn.net/yang920106/article/details/79103867/)
- [SpringCloud 声明式REST客户端Feign](https://blog.csdn.net/w_x_z_/article/details/53327183)
- [Spring Cloud中Feign配置详解](https://blog.csdn.net/u012702547/article/details/78327668)
- [Feign的自定义配置](https://blog.csdn.net/shunhua19881987/article/details/75491971)
- [Feign 自定义编码器、解码器和客户端](http://www.cnblogs.com/li3807/p/8890622.html)
- [Spring Cloud中Feign如何统一设置验证token]()
- [springboot使用feign访问api](https://blog.csdn.net/nickmengo/article/details/76732336)
- [springboot调用外部接口FeignClient](https://blog.csdn.net/gisam/article/details/72757620)
- [http请求神器之Feign](https://my.oschina.net/qixiaobo025/blog/1829465)