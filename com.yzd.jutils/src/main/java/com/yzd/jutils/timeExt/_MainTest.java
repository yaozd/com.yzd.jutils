package com.yzd.jutils.timeExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/10/18.
 */
public class _MainTest {

    //消息队列类型的任务，用于减少日志的输出-每5分钟打印一次日志
    @Test
    public void TimeLogUtil() throws InterruptedException {
        for (int i=0;i<60;i++){
            boolean isOk= TimeLogUtil.getInstance().isNext5Minutes(this.getClass(),"TimeLogUtil");
            Date dt=new Date();
            PrintUtil.outLn(dt+"="+isOk);
            TimeUnit.MINUTES.sleep(2);
        }

    }
}
