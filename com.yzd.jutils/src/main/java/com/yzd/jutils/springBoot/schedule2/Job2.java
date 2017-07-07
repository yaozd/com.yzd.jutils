package com.yzd.jutils.springBoot.schedule2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by zd.yao on 2017/7/7.
 */
@Component
public class Job2 {
    //通过ArrayBlockingQueue阻塞队列来控制线程的数量，相当于令牌桶算法，但这里的线程的数量就是阻塞队列的大小，但并不是严格的
    //在一些极端的情况下会比阻塞队列的大小多出5到10的线程。大家可以试情况选择
    private static ArrayBlockingQueue<Integer> task_readQueue_queue = new ArrayBlockingQueue<Integer>(10);
    //同步阻塞队列线程池
    private static ThreadPoolExecutor task_readQueue_executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_readQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[task_write]每隔5秒钟执行一次 " + dateFormat.format(new Date()));
        for(int i=0;i<10000;i++){
            task_readQueue_queue.put(1);
            Job2Task task1=new Job2TaskExt(task_readQueue_queue);
            task_readQueue_executor.execute(task1);
        }
    }
}
