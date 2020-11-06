package com.yzd.jutils.guava.cache_Caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest_Caffeine {
    public static void main(String[] args) throws InterruptedException {
        demoForPreLoadValue();
        demoForExpireAfterWrite();
    }

    private static void demoForExpireAfterWrite() throws InterruptedException {
        Cache<String, String> cache = Caffeine.newBuilder()
                //.initialCapacity(0)//初始大小
                .maximumSize(100_000)//最大数量 ,PS:最大容量，不是一个精确数据，如果是小幅度超过最大容量时，数据不一定会被删除
                .expireAfterWrite(30, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        cache.put("key4", "value4");
        cache.put("key5", "value5");
        cache.put("key6", "value5");
        cache.put("key7", "value5");
        cache.put("key8", "value5");
        for (int i = 0; i < 100_000; i++) {
            String val = String.valueOf(i);
            cache.put(val, val);

        }
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        //Thread.sleep(3*1000);
        System.out.println(cache.getIfPresent("key3"));
        System.out.println(cache.getIfPresent("key4"));
        System.out.println(cache.getIfPresent("key5"));
    }

    public static final Object PLACE_OBJ = new Object();

    /**
     * 使用场景：
     * 防止同一时间并发请求，产生过多压力
     * 例如：TOKEN查询
     *
     * @throws InterruptedException
     */
    private static void demoForPreLoadValue() throws InterruptedException {
        LoadingCache<String, Object> loadingCache = Caffeine.newBuilder()
                .maximumSize(10_000)//控制容量
                .expireAfterWrite(3, TimeUnit.SECONDS)//控制并发时间间隔
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String k) {
                        System.out.println(k);
                        return PLACE_OBJ;
                    }
                });
        for (int i = 0; i < 10; i++) {
            loadingCache.get("i=" + i);
        }
        System.out.println("========01=======");
        for (int i = 0; i < 10; i++) {
            loadingCache.get("i=" + i);
        }
        System.out.println("========02=======");
        Thread.sleep(3 * 1000);
        for (int i = 0; i < 10; i++) {
            loadingCache.get("i=" + i);
        }
        System.out.println("========03=======");
    }

}
