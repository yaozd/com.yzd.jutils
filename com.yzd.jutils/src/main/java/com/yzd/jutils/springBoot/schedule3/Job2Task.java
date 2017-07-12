package com.yzd.jutils.springBoot.schedule3;

import com.yzd.jutils.springBoot.schedule3.base.AbstractTask;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/7/7.
 */
public class Job2Task extends AbstractTask {
    public Job2Task(ArrayBlockingQueue<Integer> tokenBucket) {
        super(tokenBucket);
    }
    @Override
    protected void doWork(){
        //具体的业务逻辑代码
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Job2Task");
    }
}
