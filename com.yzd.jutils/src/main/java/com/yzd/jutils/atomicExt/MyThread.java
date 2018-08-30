package com.yzd.jutils.atomicExt;

/***
 *
 *
 * Created by yzd on 2018/8/30 11:13.
 */

import java.util.concurrent.atomic.AtomicInteger;

class MyThread implements Runnable {
    //原子操作
    //    static  int i = 0;
    static AtomicInteger ai = new AtomicInteger(0);


    public void run() {
        for (int m = 0; m < 1; m++) {
            //获得以后再加1；
            Integer t = ai.getAndIncrement();
            System.out.println(t);
        }
    }
};