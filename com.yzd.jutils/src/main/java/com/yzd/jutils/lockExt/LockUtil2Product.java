package com.yzd.jutils.lockExt;

import java.util.concurrent.locks.Lock;

/**
 * Created by zd.yao on 2017/10/16.
 */
public class LockUtil2Product {
    // region LockUtil2Product singleton
    private static class SingletonHolder {
        private static final LockUtil2Product INSTANCE = new LockUtil2Product();
    }

    public static final LockUtil2Product getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private LockUtil2Product() {
    }
    // endregion

    public <T> T execute(LockEnum lockEnum, String productId, ILockExecutor<T> executor) {
        T result = null;
        String baseLockPath = String.format("/%s-locks-%s", lockEnum.name(), productId);
        Lock lockTmp = getLock(baseLockPath);
        lockTmp.lock();
        try {
            result = executor.execute();
            return result;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        } finally {
            lockTmp.unlock();
        }

    }

    private synchronized Lock getLock(String key) {

        //可以使用 zookeeper lock-menagerie的锁
        //Lock firstLock = new ReentrantZkLock(baseLockPath, zkSessionManager);
        return null;
    }
}
