package com.yzd.jutils.mapreduce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/6/22.
 */
public class MapreduceTask implements Callable<Integer> {
    List<Integer> tempList;
    CountDownLatch latch;

    public MapreduceTask(ArrayList arrayList, CountDownLatch latch) {
        super();
        this.tempList = arrayList;
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {
        try {
            //region 具体的实现-begin
            //PrintUtil.outLn(1);
            //PrintUtil.outLn(latch.getCount());
            if (latch.getCount() < 30) {
                //int a2 = 1 / 0;//为测试产生一个异常
            }
            TimeUnit.MICROSECONDS.sleep(100);//模拟任务执行时间
            return tempList.size();
            //endregion具体的实现-end
        } catch (Exception e) {
            //快速失败
            while (latch.getCount() > 0) {
                latch.countDown();
            }
            throw new IllegalStateException(e);
        } finally {
            latch.countDown();
        }
    }
}
