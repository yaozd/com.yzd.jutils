package com.yzd.jutils.objectLock;

import java.util.concurrent.ExecutionException;

/**
 * Created by zd.yao on 2017/5/2.
 */
public class Outputter1 {
    public void output(String name, String key) throws ExecutionException {
        // TODO 线程输出方法
        Object monitor = ObjectLock.getInstance().monitorCache.get(key);
        synchronized (monitor) {// 得到锁
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
        }
    }
}
