package com.yzd.jutils.atomicExt;

/***
 *
 *
 * Created by yzd on 2018/8/30 11:13.
 */

public class TestAtomicInteger {
    //
    //原子操作验证
    //https://www.cnblogs.com/sharkli/p/5623524.html
    public static void main(String[] args) throws InterruptedException {
        MyThread mt = new MyThread();

        Thread t1 = new Thread(mt);
        Thread t2 = new Thread(mt);
        t1.start();
        t2.start();
        Thread.sleep(500);
        System.out.println(MyThread.ai.get());
    }
}