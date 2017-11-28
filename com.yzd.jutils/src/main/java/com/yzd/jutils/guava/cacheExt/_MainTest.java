package com.yzd.jutils.guava.cacheExt;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zd.yao on 2017/11/28.
 */
public class _MainTest {
    @Test
    public void example() throws Exception {
        String key="P01.HelloWorld:b0baee9d279d34fa1dfd71aadb908c3f";
        String tempKey="";
        AtomicLong num=new AtomicLong(1);
        while (true){
            //时间区间：10秒的统计数据
            String time=String.valueOf(System.currentTimeMillis() / 10000);
            String keyFullName=key+"_"+time+num.incrementAndGet();
            //PrintUtil.outLn(keyFullName);
            LocalCacheKeyCount.getInstance().keyAccessCount.get(keyFullName).incrementAndGet();
           /* if(!tempKey.equals(keyFullName)){
                PrintUtil.outLn(keyFullName);
                tempKey=keyFullName;
                TimeUnit.SECONDS.sleep(2);
            }*/

        }

    }
}
