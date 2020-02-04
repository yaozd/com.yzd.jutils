package com.yzd.jutils.guava.cacheExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 公共缓存水平扩展--访问数据统计
 * Created by zd.yao on 2017/11/28.
 */
public class _MainTest {

    //目前KEY的结构：P01.HelloWorld:b0baee9d279d34fa1dfd71aadb908c3f#151209921
    String key = "P01.HelloWorld:b0baee9d279d34fa1dfd71aadb908c3f";

    @Test
    public void example_final() throws Exception {
        String tempKey = "";
        while (true) {
            String keyFullName = LocalCacheKeyUtil.getKeyFullNameOfKeyCount(key);
            //PrintUtil.outLn(keyFullName);
            LocalCacheKeyCount.getInstance().keyAccessCount.get(keyFullName).incrementAndGet();
            if (!tempKey.equals(keyFullName)) {
                PrintUtil.outLn(keyFullName);
                tempKey = keyFullName;
                TimeUnit.SECONDS.sleep(1);
            }

        }
    }

    @Test
    public void example() throws Exception {
        String tempKey = "";
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //取余
                    Long sizeOfKeyCount = LocalCacheKeyUtil.getValueOfKeyAccessCount(key);
                    Long sizeOfKeyNum = LocalCacheKeyUtil.getValueOfKeyNumber(key);
                    PrintUtil.outLn(sizeOfKeyCount + "=_MainTest=TEST=" + sizeOfKeyNum);
                    if (sizeOfKeyNum == 0) {
                        sizeOfKeyNum = 1L;
                    }
                    Long num = sizeOfKeyCount % sizeOfKeyNum;
                    //
                    String keyFullNameOfKeyNum = LocalCacheKeyUtil.getKeyFullNameOfKeyNum(key);
                    PrintUtil.outLn(keyFullNameOfKeyNum + "=_MainTest=" + num);
                    try {
                        TimeUnit.SECONDS.sleep(11);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LocalCacheKeyCount.getInstance().keyAccessCount.invalidateAll();
                }
            }
        });
        t.start();
        while (true) {
            String keyFullName = LocalCacheKeyUtil.getKeyFullNameOfKeyCount(key);
            //PrintUtil.outLn(keyFullName);
            LocalCacheKeyCount.getInstance().keyAccessCount.get(keyFullName).incrementAndGet();
            if (!tempKey.equals(keyFullName)) {
                PrintUtil.outLn(keyFullName);
                tempKey = keyFullName;
                TimeUnit.SECONDS.sleep(2);
            }

        }

    }

    @Test
    public void example_maximumSize() throws Exception {

        String tempKey = "";
        AtomicLong num = new AtomicLong(1);
        while (true) {
            //时间区间：10秒的统计数据
            String time = String.valueOf(System.currentTimeMillis() / 10000);
            String keyFullName = key + "_" + time + num.incrementAndGet();
            //PrintUtil.outLn(keyFullName);
            LocalCacheKeyCount.getInstance().keyAccessCount.get(keyFullName).incrementAndGet();
           /* if(!tempKey.equals(keyFullName)){
                PrintUtil.outLn(keyFullName);
                tempKey=keyFullName;
                TimeUnit.SECONDS.sleep(2);
            }*/

        }

    }

    @Test
    public void example_getKeyNum() throws Exception {
        //取余
        String keyFullName = LocalCacheKeyUtil.getKeyFullNameOfKeyNum(key);
        Long n = System.currentTimeMillis() % LocalCacheKeyNum.getInstance().keyNumber.get(keyFullName);
        PrintUtil.outLn(n);

    }
}
