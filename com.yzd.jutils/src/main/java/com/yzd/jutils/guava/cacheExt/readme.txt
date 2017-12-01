
1.负载均衡=可以通过LocalCacheKeyCount（访问计数）做为参数获取。
2.缓存KEY的编号数量的过期时间为15秒
3.缓存KEY的访问计数器的过期时间为11秒的独立线程清空缓存
注：不要使用keyAccessCount.invalidateAll清除所有，keyAccessCount.invalidateAll在运行20分钟后会自动停止