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
    public LoadingCache<String, Integer> keyNumber;
    private LocalCacheKeyNum (){
        PrintUtil.outLn("LocalCacheKeyNum=step 01");
        LoadingCache<String, Integer> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                //缓存是时间大于keyAccessCount的时间
                .expireAfterWrite(15, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) {
                        return 1;
                    }
                });
        keyNumber=cache;
    }
}