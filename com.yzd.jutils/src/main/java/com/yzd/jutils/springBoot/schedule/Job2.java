package com.yzd.jutils.springBoot.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by zd.yao on 2017/7/5.
 */
@Component
public class Job2 {
    final static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1);
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_writeQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[task_read]每隔5秒钟执行一次 " + dateFormat.format(new Date()));
        for(int i=0;i<100;i++){
            queue.put(String.valueOf(i));
            System.out.println(i);
        }
    }
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_readQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[task_write]每隔5秒钟执行一次 " + dateFormat.format(new Date()));
        final int threadSize=13;
        ExecutorService executor = Executors.newFixedThreadPool(threadSize);
        for(int i=0;i<threadSize;i++){
            Job2Task task1=new Job2Task(queue);
            executor.execute(task1);
        }
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
    }
}
