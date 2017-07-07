package com.yzd.jutils.springBoot.schedule4;

import com.yzd.jutils.springBoot.schedule4.base.AbstractTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/7/7.
 */
public class Job2Task extends AbstractTask {
    //队列消息指令
    String cmd;
    public Job2Task(ArrayBlockingQueue<Integer> tokenBucket,String cmd) {
        super(tokenBucket);
        this.cmd=cmd;
    }
    @Override
    protected void doWork(){
        //具体的业务逻辑代码
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("schedule4-Job2Task-cmd="+cmd);
    }
}
