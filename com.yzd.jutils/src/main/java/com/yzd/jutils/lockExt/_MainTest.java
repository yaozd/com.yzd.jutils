package com.yzd.jutils.lockExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Java中Lock，tryLock，lockInterruptibly有什么区别？
 * https://www.zhihu.com/question/36771163
 *
 * Created by zd.yao on 2017/10/16.
 */
public class _MainTest {
    @Test
    public void LockUtil2(){
        LockUtil2 lockUtil2= LockUtil2.getInstance();
        String result= lockUtil2.execute(new ILockExecutor<String>() {
            @Override
            public String execute() {
                return "LockUtil2";
            }
        });
        PrintUtil.outLn("result="+result);
    }
    @Test
    public void lock()
    {
        // lock ： 在锁上等待，直到获取锁；
        final Lock lock = new ReentrantLock();
        lock.lock();
        try{

        }
        finally {
            lock.unlock();
        }
    }
    @Test
    public void tryLock() throws InterruptedException {

        //tryLock：立即返回，获得锁返回true,没获得锁返回false；
        final Lock lock = new ReentrantLock();
        lock.tryLock(5, TimeUnit.SECONDS);
        try{

        }
        finally {
            lock.unlock();
        }
    }
    @Test
    public void lockInterruptibly() throws InterruptedException {

        //tryInterruptibly：在锁上等待，直到获取锁，但是会响应中断，这个方法优先考虑响应中断，而不是响应锁的普通获取或重入获取
        final Lock lock = new ReentrantLock();
        lock.lockInterruptibly();
        try{

        }
        finally {
            lock.unlock();
        }
    }
}
