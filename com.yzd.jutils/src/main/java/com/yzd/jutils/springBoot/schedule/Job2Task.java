package com.yzd.jutils.springBoot.schedule;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/7/5.
 */
public class Job2Task implements Runnable {
    BlockingQueue<String> queue;

    public Job2Task(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("【Job2Task】--开始");
        while (true) {
            try {
                doWork();
            } catch (Exception e) {
                //这里应该使用log记录日志，而不应是e.printStackTrace();
                e.printStackTrace();
                try {
                    //正常情况下情况下程序是不会出现问题的，但由于网络中断等特殊情况下出现异常，造成CPU资源耗尽
                    //暂时为10秒
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

        }
        //System.out.println("【Job2Task】--结束");
    }

    private void doWork() {
        String val = take();
        System.out.println("JOB2TASK-DO-WORK=" + val);
    }

    private String take() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
