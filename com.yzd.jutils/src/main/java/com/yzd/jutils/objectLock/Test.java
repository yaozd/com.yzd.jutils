package com.yzd.jutils.objectLock;

import java.util.concurrent.ExecutionException;

/**
 * todo 使用Guava为并发应用实现基于对象的微锁
 * http://www.jdon.com/concurrent/object-based-micro-locking-for-concurrent-applications-by-using-guava.html
 * 理解Java中的弱引用（Weak Reference）
 * http://www.cnblogs.com/absfree/p/5555687.html
 * Created by zd.yao on 2017/5/2.
 */
public class Test {
    public static void main(String[] args) throws ExecutionException {
        final String key1 = "key1";
        final String key2 = "key2";
        final Outputter1 output = new Outputter1();
        for (int i = 0; i < 10; i++)
            ThreadFun(key1, key2, output);
    }

    private static void ThreadFun(final String key1, final String key2, final Outputter1 output) {
        new Thread() {
            public void run() {
                try {
                    output.output("asdfghj一", key1);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                try {
                    output.output("123456789二-2", key2);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                try {
                    output.output("!@#$%^三", key1);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                try {
                    output.output("[：：：：]四-2", key2);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
        new Thread() {
            public void run() {
                try {
                    output.output("[----]五", key1);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            ;
        }.start();
    }
}
