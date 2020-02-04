package com.yzd.jutils.springBoot.schedule2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zd.yao on 2017/7/7.
 */
public class Job2Task implements Runnable {
    ArrayBlockingQueue<Integer> queueThread;

    public Job2Task(ArrayBlockingQueue<Integer> queue) {
        this.queueThread = queue;
    }


    public void run() {
        try {
            doWork();
        } finally {
            try {
                queueThread.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doWork() {
        //具体的业务逻辑代码
        System.out.println("Job2Task");
    }
}
