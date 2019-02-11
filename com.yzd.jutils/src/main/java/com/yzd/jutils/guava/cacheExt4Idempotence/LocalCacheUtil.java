package com.yzd.jutils.guava.cacheExt4Idempotence;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 一个简单的幂等工具类实现
 * https://segmentfault.com/a/1190000015497226
 */
public class LocalCacheUtil {
    public static final String NOT_HIT_TAG = "UNHIT_TAG";
    public static final String NULL_TAG = "NULL_TAG";
    private static LoadingCache<String, Object> idempotentCache =
            CacheBuilder.newBuilder().expireAfterAccess(3, TimeUnit.MINUTES).build(new CacheLoader<String, Object>() {
                @Override
                public Object load(String key) throws Exception {
                    return NOT_HIT_TAG;
                }
            });

    public static Object getObject(String uuid) {
        Object obj = idempotentCache.getUnchecked(uuid);
        if (obj instanceof String) {
            if (NULL_TAG.equals(obj)) {
                return null;
            }
        }
        return obj;
    }
    public static void putObject(String uuid, Object val) {
        //针对返回结果为null的场景，也好解决，就是利用一个符号来代替null，简单的变形如下
        if (val == null) {
            val = NULL_TAG;
        }
        idempotentCache.put(uuid, val);
    }

    public static void remove(String uuid) {
        idempotentCache.invalidate(uuid);
    }

    /**
     *
     * 内存问题
     * 虽然上面设置了失效时间为3min,但在jdk8的场景下，很容易发现内存疯狂上涨，不见到有回收？ why？
     * 这块可能与gauva的内存回收机制有关系，因为jdk8取消了永久代，使用了元空间，当没有设最大值时，会一直上涨，使用系统的内存
     * 简单的解决方案就是主动回收掉无效的数据
     */
    public static void registerScheduleClearTask() {
        ScheduledExecutorService task = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "CacheClearTaskSubThread");
            // 设置线程为守护线程，主线程退出，子线程也随之退出
            t.setDaemon(true);
            return t;
        });
        //每一分钟执行一次
        //主动回收掉无效的数据,释放内存,减少内存溢出
        task.scheduleAtFixedRate(() -> idempotentCache.cleanUp(), 1, 1, TimeUnit.MINUTES);
        //测试执行情况
        //task.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 1, 1, TimeUnit.SECONDS);
    }
}
