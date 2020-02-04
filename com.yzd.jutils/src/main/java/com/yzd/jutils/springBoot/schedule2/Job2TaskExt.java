package com.yzd.jutils.springBoot.schedule2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zd.yao on 2017/7/7.
 */
public class Job2TaskExt extends Job2Task {
    public Job2TaskExt(ArrayBlockingQueue<Integer> queue) {
        super(queue);
    }

    @Override
    protected void doWork() {
        //具体的业务逻辑代码
        System.out.println("Job2TaskExt");
    }
}
