package com.yzd.jutils.objectLock;

import com.google.common.cache.*;
import com.yzd.jutils.print.PrintUtil;

/**
 * Created by zd.yao on 2017/5/2.
 */
public class ObjectLock {
    private static class SingletonHolder {
        private static final ObjectLock INSTANCE = new ObjectLock();
    }

    public static final ObjectLock getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public LoadingCache<String, Object> monitorCache;

    private ObjectLock() {
        PrintUtil.outLn("ObjectLock monitor cache init");
        //todo 主要是测试方便查看-生产环境是可以把移除监听删除的
        RemovalListener<String, Object> removalListener = new RemovalListener<String, Object>() {
            @Override
            public void onRemoval(RemovalNotification<String, Object> removalNotification) {
                PrintUtil.outLn(removalNotification.getKey());
            }
        };
        //todo 只是为测试把maximumSize设置为1
        LoadingCache<String, Object> monitorCache = CacheBuilder.newBuilder()
                .weakValues()
                .maximumSize(1)
                .removalListener(removalListener)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String s) {
                        return new Object();
                    }
                });
        this.monitorCache = monitorCache;
    }
}
