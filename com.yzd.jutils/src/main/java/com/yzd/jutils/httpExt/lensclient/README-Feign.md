# feign
> lens http client ，实际使用中可以参考lens项目。

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


## 问题
```
1.
Exception in thread "main" java.lang.NoSuchMethodError: feign.Response.builder()Lfeign/Response$Builder;
Exception in thread "main" java.lang.NoSuchMethodError: feign.Response.builder()Lfeign/Response$Builder;
	at feign.okhttp.OkHttpClient.toFeignResponse(OkHttpClient.java:95)
//
原因：
fegin包引用冲突发生的
<dependency>
    <groupId>com.netflix.feign</groupId>
    <artifactId>feign-core</artifactId>
    <version>8.18.0</version>
</dependency>
与
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-core</artifactId>
    <version>9.3.1</version>
</dependency>
```

## lens maven配置
```
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-core</artifactId>
    <version>9.3.1</version>
</dependency>
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-okhttp</artifactId>
    <version>9.3.1</version>
</dependency>
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-gson</artifactId>
    <version>9.3.1</version>
</dependency>
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-slf4j</artifactId>
    <version>9.3.1</version>
</dependency>
```