package com.yzd.jutils.guava.cacheExt;

import com.google.common.cache.*;
import com.yzd.jutils.print.PrintUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 缓存KEY的访问计数器
 * Created by zd.yao on 2017/11/28.
 */
public class LocalCacheKeyCount {
    private static class SingletonHolder {
        private static final LocalCacheKeyCount INSTANCE = new LocalCacheKeyCount();
    }
    public static final LocalCacheKeyCount getInstance() {
        return SingletonHolder.INSTANCE;
    }
    public LoadingCache<String, AtomicLong> keyAccessCount;
    private LocalCacheKeyCount (){
        PrintUtil.outLn("LocalCacheKeyCount=step 01");
        LoadingCache<String, AtomicLong> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, AtomicLong>()  {
                    @Override
                    public void onRemoval(RemovalNotification<String, AtomicLong> removal) {
                        String key=removal.getKey();
                        Long count=removal.getValue().get();
                        //redis get key count
                        //5秒内每1000增加1个缓存： count+redis count /1000
                        //LocalCacheKeyNum.getInstance().keyNumber.put(key,2);
                        LocalCacheKeyNum.getInstance().keyNumber.put(key, (int) (count/1000));
                        try {
                            PrintUtil.outLn(LocalCacheKeyNum.getInstance().keyNumber.get(key));
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .build(new CacheLoader<String, AtomicLong>() {
                    @Override
                    public AtomicLong load(String key) {
                        return new AtomicLong(1);
                    }
                });
        keyAccessCount=cache;
    }
}

