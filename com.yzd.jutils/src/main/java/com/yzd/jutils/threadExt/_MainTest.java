package com.yzd.jutils.threadExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/9/14.
 */
public class _MainTest {
    public static void main(String[] args) {
        //java中thread的start()和run()的区别
        //1.start（）方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的
        //2.run（）方法当作普通方法的方式调用，程序还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintUtil.outLn(1);
            }
        }).start();
        int pause = 0;
    }
    //通过newFixedThreadPool实现任务执行超时后终止
    @Test
    public void ExecutorService_Test() throws InterruptedException {
        PrintUtil.outLn("Begin currentTimeMillis=" + new Date());
        PrintUtil.outLn("Begin currentTimeMillis=" + System.currentTimeMillis());
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                PrintUtil.outLn(1);
                try {
                    //TimeUnit.SECONDS.sleep(500);
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //虽然shutdown方法是等所有任务跑完后才真正停掉线程池，但该方法不会造成堵塞，也就是这代码运行后，下一行代码会立刻运行
        //shutdownNow暴力的直接终止线程池
        //awaitTermination方法是堵塞式的，只有等真的把线程池停掉才会让程序继续往下执行
        //===
        //region 通过shutdown+awaitTermination实现任务执行超时后终止
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        //endregion
        PrintUtil.outLn("End currentTimeMillis=" + System.currentTimeMillis());
        PrintUtil.outLn("End currentTimeMillis=" + new Date());
    }
}
