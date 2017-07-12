package com.yzd.jutils.springBoot.schedule4;

import com.yzd.jutils.springBoot.schedule4.base.WorkThreadPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/7/7.
 */
@Component
public class Job2 {
    private static WorkThreadPool task_readQueue_threadPool=new WorkThreadPool(10);
    @Scheduled(initialDelay = 3000, fixedDelay = 1000 * 5)
    public void task_readQueue() throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[task_write]每隔5秒钟执行一次 " + dateFormat.format(new Date()));
        for(int i=0;i<100;i++){
            task_readQueue_threadPool.getTokenBucket().put(1);
            Job2Task task1=new Job2Task(task_readQueue_threadPool.getTokenBucket(),String.valueOf(i));
            task_readQueue_threadPool.getExecutor().execute(task1);
        }
        //以redis作为消息队列的broker的情况下的伪代码
        while (true){
            //从redis中读取消息
            String cmd = getString();
            if (cmd == null) continue;
            task_readQueue_threadPool.getTokenBucket().put(1);
            Job2Task task1=new Job2Task(task_readQueue_threadPool.getTokenBucket(),cmd);
            task_readQueue_threadPool.getExecutor().execute(task1);
        }
        //注：以redis消息的MD5的值做为独占锁
    }

    //从redis 里面读取消息可放到一个单独的抽象类里AbstractJob中，可以使代码更加清楚
    //通过静态方法引用就可以
    private String getString() throws InterruptedException {
        //
        String cmd=null;
        //redis-网络抖动等特殊情况下的异常处理
        try{
            //阻塞指令-读取reids的消息队列
            cmd="redis.list.rlpod";
        }catch (Exception e){
            //log 记录日志
            e.printStackTrace();
            TimeUnit.SECONDS.sleep(10);
            return null;
        }
        //redis 阻塞超时的情况下处理
        if(StringUtils.isBlank(cmd)){
            return null;
        }
        return cmd;
    }
}
