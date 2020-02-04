package com.yzd.jutils.guava.cacheExt4PublicCache;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 公共缓存数据-访问统计（计数）
 */
public class LocalCacheUtil {
    private static LocalCacheUtil ourInstance = new LocalCacheUtil();

    public static LocalCacheUtil getInstance() {
        return ourInstance;
    }

    private LocalCacheUtil() {

        //创建本地缓存
        keyCache = buildCache();
        //定时清理过期数据，防止内存溢出
        clearTask4ExpireData();
    }

    private LoadingCache<String, AtomicLong> keyCache;

    private LoadingCache<String, AtomicLong> buildCache() {
        //缓存KEY的计数器
        RemovalListener<String, AtomicLong> removalListener = new RemovalListener<String, AtomicLong>() {
            public void onRemoval(RemovalNotification<String, AtomicLong> removal) {
                String key = removal.getKey();
                Long count = removal.getValue().get();
                //PrintUtil.outLn("key=%s;count=%s;", key, count);
                //访问数过小可忽略不计，不需要上报到统计中心
                if (count < 100L) {
                    return;
                }
                //PrintUtil.outLn("========count=" + count);
            }
        };
        //缓存KEY的基数
        LoadingCache<String, AtomicLong> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .removalListener(removalListener)
                .build(new CacheLoader<String, AtomicLong>() {
                    @Override
                    public AtomicLong load(String key) {
                        //PrintUtil.outLn("#####################");
                        return new AtomicLong(1);
                    }
                });
        return cache;
    }

    public void increment(String key) {
        try {
            keyCache.get(key).getAndIncrement();
            //PrintUtil.outLn(keyCache.get(key).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内存问题
     * 虽然上面设置了失效时间为3min,但在jdk8的场景下，很容易发现内存疯狂上涨，不见到有回收？ why？
     * 这块可能与gauva的内存回收机制有关系，因为jdk8取消了永久代，使用了元空间，当没有设最大值时，会一直上涨，使用系统的内存
     * 简单的解决方案就是主动回收掉无效的数据
     */
    private void clearTask4ExpireData() {
        ScheduledExecutorService task = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "CacheClearTaskSubThread");
            // 设置线程为守护线程，主线程退出，子线程也随之退出
            t.setDaemon(true);
            return t;
        });
        //每一分钟执行一次
        //主动回收掉无效的数据,释放内存,减少内存溢出
        task.scheduleAtFixedRate(() -> keyCache.cleanUp(), 2, 2, TimeUnit.SECONDS);
        //测试执行情况
        //task.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 1, 1, TimeUnit.SECONDS);
    }
}
