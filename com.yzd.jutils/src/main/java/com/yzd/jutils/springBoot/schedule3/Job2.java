package com.yzd.jutils.springBoot.schedule3;

import com.yzd.jutils.springBoot.schedule3.base.WorkThreadPool;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zd.yao on 2017/7/7.
 */
@Component
public class Job2 {
    private static WorkThreadPool task_readQueue_threadPool = new WorkThreadPool(10);

    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_readQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[task_write]每隔5秒钟执行一次 " + dateFormat.format(new Date()));
        for (int i = 0; i < 10000; i++) {
            task_readQueue_threadPool.getTokenBucket().put(1);
            Job2Task task1 = new Job2Task(task_readQueue_threadPool.getTokenBucket());
            task_readQueue_threadPool.getExecutor().execute(task1);
        }
    }
}
