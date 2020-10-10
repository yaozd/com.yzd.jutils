# Caffeine
- [Caffeine](https://www.cnblogs.com/CrankZ/p/10889859.html)
```
Spring5（SpringBoot2）开始用Caffeine取代guava.详见官方信息SPR-13797

 pom.xml 中添加 caffeine 依赖：

版本问题参考https://mvnrepository.com/artifact/com.github.ben-manes.caffeine/caffeine

<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>2.7.0</version>
</dependency>
```

## 性能
```
这里只列出部分性能比较，
详细请看官方官方 https://github.com/ben-manes/caffeine/wiki/Benchmarks
由图可以看出，Caffeine不论读还是写的效率都远高于其他缓存。
```

## Demo
- 新建对象
```
// 1、最简单
Cache<String, Object> cache = Caffeine.newBuilder()
    .build();
// 2、真实使用过程中我们需要自己配置参数。这里只列举部分，具体请看下面列表
Cache<String, Object> cache = Caffeine.newBuilder()
    .initialCapacity(2)//初始大小
    .maximumSize(2)//最大数量
    .expireAfterWrite(3, TimeUnit.SECONDS)//过期时间
    .build();
```
- 参数含义
```
initialCapacity: 初始的缓存空间大小
maximumSize: 缓存的最大数量
maximumWeight: 缓存的最大权重
expireAfterAccess: 最后一次读或写操作后经过指定时间过期
expireAfterWrite: 最后一次写操作后经过指定时间过期
refreshAfterWrite: 创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存
weakKeys: 打开key的弱引用
weakValues：打开value的弱引用
softValues：打开value的软引用
recordStats：开发统计功能
注意：
expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准。
maximumSize和maximumWeight不可以同时使用
```