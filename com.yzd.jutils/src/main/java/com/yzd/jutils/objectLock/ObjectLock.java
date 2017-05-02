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
    public LoadingCache<String, Object>  monitorCache;
    private ObjectLock (){
        PrintUtil.outLn("ObjectLock monitor cache init");
        //todo 只是为测试把maximumSize设置为1
        LoadingCache<String, Object> monitorCache = CacheBuilder.newBuilder()
                .weakValues()
                .maximumSize(1)
                .build(new CacheLoader<String, Object>() {
                    @Override
                    public Object load(String s) {
                        return new Object();
                    }
                });
        this.monitorCache=monitorCache;
    }
}
