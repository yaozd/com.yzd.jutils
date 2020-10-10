package com.yzd.jutils.guava.cache_Caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest_Caffeine {
    public static void main(String[] args) throws InterruptedException {
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
            String val=String.valueOf(i);
            cache.put(val,val);

        }
        System.out.println(cache.getIfPresent("key1"));
        System.out.println(cache.getIfPresent("key2"));
        //Thread.sleep(3*1000);
        System.out.println(cache.getIfPresent("key3"));
        System.out.println(cache.getIfPresent("key4"));
        System.out.println(cache.getIfPresent("key5"));
    }
}
