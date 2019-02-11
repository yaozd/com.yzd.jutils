package com.yzd.jutils.threadExt4ScheduledExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class _MainTest {
    /**
     * 如果是在@Test测试状态下，下面程序是可以正常退出，
     * 但是在main的方法就不会正常退出。
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        // 1秒打印一次 当前线程名
        service.scheduleAtFixedRate(() -> System.out.println(Thread.currentThread().getName()), 1, 1, TimeUnit.SECONDS);
        // 主线程等待10秒
        TimeUnit.SECONDS.sleep(10);
        System.out.println("主线程退出了");
    }
}
