package com.yzd.jutils.springBoot.schedule4.base;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by zd.yao on 2017/7/7.
 */
public class AbstractTask  implements Runnable {
    ArrayBlockingQueue<Integer> tokenBucket;
    public AbstractTask(ArrayBlockingQueue<Integer> tokenBucket){
        this.tokenBucket=tokenBucket;
    }

    @Override
    public void run() {
        try{
            doWork();
        }finally {
            try {
                tokenBucket.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doWork() {
        //具体的业务逻辑代码
        System.out.println("AbstractTask");
        throw new IllegalStateException("没有具体的业务逻辑实现代码");

    }
}