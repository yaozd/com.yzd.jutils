## 注解可以使用常量
- 通过常量可以知道哪些代码使用了当前的缓存
- 参考：redisCacheExt
```
 @RedisCache(key = CacheKeyConstant.UserService_getEmpty)
 CacheKeyConstant
```