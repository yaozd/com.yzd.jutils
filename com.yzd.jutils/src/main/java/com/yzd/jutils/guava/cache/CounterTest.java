package com.yzd.jutils.guava.cache;

import com.google.common.cache.*;
import com.yzd.jutils.print.PrintUtil;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * todo 解决：数据倾斜-例如：缓存热点问题
 * Created by zd.yao on 2017/4/27.
 */
public class CounterTest {
    public static void main(String[] args) throws ExecutionException {
        //缓存KEY的基数
        final LoadingCache<Long, AtomicLong> keBase = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(new CacheLoader<Long, AtomicLong>() {
                    @Override
                    public AtomicLong load(Long seconds) {
                        return new AtomicLong(1);
                    }
                });

        //缓存KEY的计数器
        RemovalListener<Long, AtomicLong> removalListener = new RemovalListener<Long, AtomicLong>() {
            public void onRemoval(RemovalNotification<Long, AtomicLong> removal) {
                Long key=removal.getKey();
                AtomicLong value=removal.getValue();
                PrintUtil.outLn("key=%s;value=%s;",removal.getKey(),removal.getValue());
                if(removal.getValue().get()>100L){
                    try {
                        //todo 将新的KEY的名字还原为原始KEY的名字
                        Long num=Singleton.getInstance().keyBase.get(key/10).get();
                        //todo 做一个限制-避免缓存被无限放大
                        if(num<3){
                            Singleton.getInstance().keyBase.get(key/10).incrementAndGet();
                        }
                        //todo 获取每一个KEY的缓存对象然后再放大4位
                        //todo key=xxx-0、xxx-1、xxx-2、xxx-3、、xxx-4
                        //todo FOR(I=0;I<NUM;I++)
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        LoadingCache<Long, AtomicLong> keyCounter = CacheBuilder.newBuilder()
                        .expireAfterWrite(2, TimeUnit.SECONDS)
                        .removalListener(removalListener)
                        .build(new CacheLoader<Long, AtomicLong>() {
                            @Override
                            public AtomicLong load(Long seconds) {
                                return new AtomicLong(0);
                            }
                        });
        long limit = 1000;
        while(true) {
            //得到当前秒--作为KEY（原始Key值）
            //long currentSeconds = System.currentTimeMillis() / 1000;
            long currentSeconds = 1234567;
            //todo 原始KEY的名字
            Long num= Singleton.getInstance().keyBase.get(currentSeconds).get();
            Long keyNum= System.currentTimeMillis()%num;
            //todo-产生新的KEY的名字
            Long newKey=currentSeconds*10+keyNum;
            Long count= keyCounter.get(newKey).incrementAndGet();
            if(count > limit) {
                System.out.println("限流了:" + currentSeconds);
                continue;
            }
            //业务处理
        }
    }
}
