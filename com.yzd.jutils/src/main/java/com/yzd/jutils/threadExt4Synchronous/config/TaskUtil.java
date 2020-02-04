package com.yzd.jutils.threadExt4Synchronous.config;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskUtil {
    private static boolean shutdownState = false;

    /**
     * 关闭任务
     */
    public static void shutdown() {
        shutdownState = true;
    }

    /**
     * 是否为关闭状态
     *
     * @return
     */
    public static boolean isShutdown() {
        return shutdownState;
    }

    /**
     * 任务是否已经全部完成
     *
     * @return
     */
    public static boolean isCompleteTaskAll() {
        for (TaskEnum e : TaskEnum.values()) {
            if (e.getTokenBucketSize() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 当前任务数量
     *
     * @return
     */
    public static Integer getTaskSize() {
        Integer size = 0;
        for (TaskEnum e : TaskEnum.values()) {
            size = size + e.getTokenBucketSize();
        }
        return size;
    }

    /**
     * 通过任务名称创建一个线程池
     *
     * @param name
     * @return
     */
    public static ThreadPoolExecutor newThreadPoolExecutor(String name) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new DefaultThreadFactory(name));
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory(String name) {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
            namePrefix = "TaskPool-" + name + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
