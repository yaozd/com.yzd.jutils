package com.yzd.jutils.threadExt;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阿里伯乐评测题目：
 * 线程A输出1，3，5，7，9；线程B输出2，4，6，8 ；要求输出：1，2，3，4，5，6，7，8，9.
 * 思路：
 * 通过AtomicInteger计数器，然后对线程数取余，通过取余的方式，可以控制无限数量的线程的执行顺序
 * 参考：
 * 多线程按顺序依次打印ABCD---java多线程的一道经典面试题
 * https://blog.csdn.net/JeamKing/article/details/73040430
 */
public class Thread4PrintDemo {
    int threadCount = 2;
    AtomicInteger executeCount = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);
    Lock lock = new ReentrantLock();
    Thread oneThread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 1; i < 10; i += 2) {
                //0
                sequencePrint(0, "A=" + i);
            }
        }
    });
    Thread twoThread = new Thread(new Runnable() {
        @Override
        public void run() {
            for (int i = 2; i < 10; i += 2) {
                sequencePrint(1, "B=" + i);
            }
        }
    });

    /**
     * 顺序打印
     *
     * @param n   执行顺序
     * @param str 打印信息
     */
    private void sequencePrint(int n, String str) {
        while (true) {
            executeCount.incrementAndGet();
            System.out.println(1);
            synchronized (count) {
                if (count.get() % threadCount == n) {
                    print(str);
                    count.incrementAndGet();
                    count.notifyAll();
                    break;
                }
                try {
                    count.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread4PrintDemo instance = new Thread4PrintDemo();
        instance.oneThread.start();
        instance.twoThread.start();
        //
        Thread.sleep(100);
        System.out.printf("总执行的次数：" + instance.executeCount.get());
    }
}
