package com.yzd.jutils.blockingQueue;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/6/16.
 */
public class ExecutorBlockingQueueTest {
    /**
     * Executors.newFixedThreadPool和ArrayBlockingQueue一点使用心得
     * http://blog.csdn.net/s464036801/article/details/8253826
     * ThreadPoolExecutor使用介绍
     * http://blog.csdn.net/wangwenhui11/article/details/6760474
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.HOURS, queue, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            final int index = i;
            System.out.println("task: " + (index+1));
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread start--------" + (index+1));
                    try {
                        //Thread.sleep(Long.MAX_VALUE);
                        Thread.sleep(50000);//测试休眠50秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread end" + (index+1));
                }
            };
            executor.execute(run);
        }
    }
}
