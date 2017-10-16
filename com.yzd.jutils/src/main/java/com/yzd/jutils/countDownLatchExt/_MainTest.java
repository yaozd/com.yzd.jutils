package com.yzd.jutils.countDownLatchExt;

import com.yzd.jutils.print.PrintUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/10/16.
 */
public class _MainTest {
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch_1 = new CountDownLatch(1);
        boolean isOk_1 = latch_1.await(5, TimeUnit.SECONDS);
        // isOk_1=false
        PrintUtil.outLn("isOk=" + isOk_1);
        final CountDownLatch latch_2 = new CountDownLatch(1);
        latch_2.countDown();
        boolean isOk_2 = latch_2.await(5, TimeUnit.SECONDS);
        // isOk_2=true
        PrintUtil.outLn("isOk=" + isOk_2);
        //可以用于测试多线程锁资源的竞争
        //参考 zookeeper lock--menagerie分布式锁中单元测试的使用
        /*
        @Test(timeout = 1500l)
        public void testMultipleThreadsCannotAccessSameLock()throws Exception{
            final CountDownLatch latch = new CountDownLatch(1);
            final Lock firstLock = new ReentrantZkLock(baseLockPath, zkSessionManager);
            firstLock.lock();
            testService.submit(new Runnable() {
                @Override
                public void run() {
                    firstLock.lock();
                    try{
                        latch.countDown();
                    }finally{
                        firstLock.unlock();
                    }
                }
            });

            boolean acquired = latch.await(500, TimeUnit.MILLISECONDS);
            assertTrue("The Lock was acquired twice by two separate threads!",!acquired);

            firstLock.unlock();

            acquired = latch.await(500, TimeUnit.MILLISECONDS);
            assertTrue("The Lock was never acquired by another thread!",acquired);
        }*/
    }
}
