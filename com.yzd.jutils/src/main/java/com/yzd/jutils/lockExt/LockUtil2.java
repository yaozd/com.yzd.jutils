package com.yzd.jutils.lockExt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zd.yao on 2017/10/16.
 */
public class LockUtil2 {
    // region lockUtil2 singleton
    private static class SingletonHolder {
        private static final LockUtil2 INSTANCE = new LockUtil2();
    }
    public static final LockUtil2 getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private LockUtil2 (){
    }
    // endregion
    final Lock lock = new ReentrantLock();

    public <T> T execute(ILockExecutor<T> executor) {
        T result = null;
        lock.lock();
        try {
            result = executor.execute();
            return result;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            lock.unlock();
        }

    }
}
