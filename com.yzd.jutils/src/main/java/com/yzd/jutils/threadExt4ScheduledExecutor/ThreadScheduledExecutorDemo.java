package com.yzd.jutils.threadExt4ScheduledExecutor;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Executors.newSingleThreadScheduledExecutor()
 * https://www.jianshu.com/p/0cfcea8db493
 */
public class ThreadScheduledExecutorDemo {
    /**
     * 线程
     * 如果是在@Test测试状态下，下面程序是可以正常退出，
     * 但是在main的方法就不会正常退出。
     *
     * @throws InterruptedException
     */
    @Test
    public void t1() throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 1秒打印一次 当前线程名
        service.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 1, 1, TimeUnit.SECONDS);
        // 主线程等待10秒
        TimeUnit.SECONDS.sleep(10);
        System.out.println("主线程退出了");
    }

    /**
     * 设置线程为守护线程，主线程退出，子线程也随之退出
     *
     * @throws InterruptedException
     */
    @Test
    public void t2() throws InterruptedException {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor(r -> {
            Thread t = new Thread(r, "SubThread");
            // 设置线程为守护线程，主线程退出，子线程也随之退出
            t.setDaemon(true);
            return t;
        });
        // 1秒打印一次 当前线程名
        service.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 1, 1, TimeUnit.SECONDS);
        // 主线程等待10秒
        TimeUnit.SECONDS.sleep(10);
        System.out.println("主线程退出了");
    }
}
