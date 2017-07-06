package com.yzd.jutils.blockingQueue;

import java.util.concurrent.*;

/**
 * 同步队列
 * 似懂非懂的SynchronousQueue和长度为1的BlockingQueue
 *  http://blog.csdn.net/aitangyong/article/details/38684831
 * Created by zd.yao on 2017/7/6.
 */
public class SyncQueueTester {
    private static ExecutorService executor = new ThreadPoolExecutor(1, 1,
            1000, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            kickOffEntry(i);

            Thread.sleep(200);
        }

        executor.shutdown();
    }

    private static void kickOffEntry(final int index) {
        executor.
                submit(
                        new Callable<Void>() {
                            public Void call() throws InterruptedException {
                                System.out.println("start " + index);
                                Thread.sleep(1000); // pretend to do work
                                System.out.println("stop " + index);
                                return null;
                            }
                        }
                );
    }
}
