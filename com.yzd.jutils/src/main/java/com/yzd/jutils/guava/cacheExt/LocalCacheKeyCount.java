package com.yzd.jutils.guava.cacheExt;

import com.google.common.cache.*;
import com.yzd.jutils.print.PrintUtil;

import java.util.Iterator;
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
    private Thread invalidateAllThread;
    private LocalCacheKeyCount (){
        PrintUtil.outLn("LocalCacheKeyCount=step 01");
        LoadingCache<String, AtomicLong> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                //时间区间：10秒的统计数据
                //.expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener(new RemovalListener<String, AtomicLong>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, AtomicLong> removal) {
                        String key = removal.getKey();
                        Long count = removal.getValue().get();
                        //如果数量<100则不进行合并统计
                        if (count < 100) {
                            return;
                        }
                        //redis get key count
                        //5秒内每1000增加1个缓存： count+redis count /1000
                        //LocalCacheKeyNum.getInstance().keyNumber.put(key,2);
                        Long num = count / 1000;
                        //同一KEY缓存最多5个实体最小1个实体
                        num = num < 1 ? 1 : num > 5 ? 5 : num;
                        LocalCacheKeyNum.getInstance().keyNumber.put(key, num);
                        try {
                            PrintUtil.outLn(key + "=LocalCacheKeyCount=" + LocalCacheKeyNum.getInstance().keyNumber.get(key));
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
        invalidateAllThread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(LocalCacheKeyUtil.isIsShutdown()){break;}
                    try{
                        TimeUnit.SECONDS.sleep(11);
                        //不要使用keyAccessCount.invalidateAll清除所有，keyAccessCount.invalidateAll在运行20分钟后会自动停止
                        Iterator<String> iterator = keyAccessCount.asMap().keySet().iterator();
                        while(iterator.hasNext()){
                            String key = iterator.next();
                            if (key.lastIndexOf(LocalCacheKeyUtil.getIntervalTimeStr()) > 0) {
                                continue;
                            }
                            keyAccessCount.invalidate(key);
                        }
                        PrintUtil.outLn("ExecutorService=step 01");
                    }catch (Exception e){}
                }
            }
        });
        invalidateAllThread.start();
        PrintUtil.outLn("LocalCacheKeyCount=step 02");
    }
}

