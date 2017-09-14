package com.yzd.jutils.threadExt;

import com.yzd.jutils.print.PrintUtil;

/**
 * Created by zd.yao on 2017/9/14.
 */
public class _MainTest {
    public static void main(String[] args){
        //java中thread的start()和run()的区别
        //1.start（）方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的
        //2.run（）方法当作普通方法的方式调用，程序还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintUtil.outLn(1);
            }
        }).start();
        int pause=0;
    }
}
