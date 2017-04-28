package com.yzd.jutils.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.yzd.jutils.print.PrintUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * todo 单例- 静态内部类
 * Created by zd.yao on 2017/4/28.
 */
public class Singleton {
    private static class SingletonHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    public static final Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public LoadingCache<Long, AtomicLong> keyBase;
    private Singleton (){
        PrintUtil.outLn("singleton cache init");
        //缓存KEY的基数
        LoadingCache<Long, AtomicLong> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) {
                        return new AtomicLong(1);
                    }
                });
        keyBase=cache;
    }
}

