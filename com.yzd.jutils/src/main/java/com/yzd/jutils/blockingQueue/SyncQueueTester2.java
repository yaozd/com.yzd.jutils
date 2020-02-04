package com.yzd.jutils.blockingQueue;

import java.util.concurrent.*;

/**
 * 同步队列
 * 似懂非懂的SynchronousQueue和长度为1的BlockingQueue
 * http://blog.csdn.net/aitangyong/article/details/38684831
 * Created by zd.yao on 2017/7/6.
 */
public class SyncQueueTester2 {
    //同步阻塞队列线程池
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new ThreadPoolExecutor.AbortPolicy());
    //通过ArrayBlockingQueue阻塞队列来控制线程的数量，相当于令牌桶算法，但这里的线程的数量就是阻塞队列的大小，但并不是严格的
    //在一些极端的情况下会比阻塞队列的大小多出5到10的线程。大家可以试情况选择
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20099; i++) {
            queue.put(1);
            kickOffEntry(i);
            if (i == 100) {
                int t = 0;
            }
            //Thread.sleep(200);
        }
        System.out.println(executor.getActiveCount());
        executor.shutdown();
    }

    private static void kickOffEntry(final int index) {
        executor.
                submit(
                        new Callable<Void>() {
                            public Void call() throws InterruptedException {

                                //System.out.println("start " + index);
                                //Thread.sleep(1000); // pretend to do work
                                System.out.println("stop " + index);
                                queue.take();
                                return null;
                            }
                        }
                );
    }
}

