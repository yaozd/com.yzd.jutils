package com.yzd.jutils.guava.cacheExt;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yzd.jutils.print.PrintUtil;

import java.util.concurrent.TimeUnit;

/**
 * 缓存KEY的编号数量
 * Created by zd.yao on 2017/11/28.
 */
public class LocalCacheKeyNum {
    private static class SingletonHolder {
        private static final LocalCacheKeyNum INSTANCE = new LocalCacheKeyNum();
    }
    public static final LocalCacheKeyNum getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //缓存KEY的编号数量
    public LoadingCache<String, Long> keyNumber;
    private LocalCacheKeyNum (){
        PrintUtil.outLn("LocalCacheKeyNum=step 01");
        LoadingCache<String, Long> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                //缓存是时间大于keyAccessCount的时间为15秒
                .expireAfterWrite(15, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Long>() {
                    @Override
                    public Long load(String key) {
                        return 1L;
                    }
                });
        keyNumber=cache;
    }
}