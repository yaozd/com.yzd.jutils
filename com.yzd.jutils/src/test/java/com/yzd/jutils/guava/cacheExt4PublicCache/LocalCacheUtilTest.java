package com.yzd.jutils.guava.cacheExt4PublicCache;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class LocalCacheUtilTest {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(threads = 100,duration=150000000)
    public void increment() throws InterruptedException {
        String key = "yzd";
        LocalCacheUtil.getInstance().increment(key);
        //TimeUnit.SECONDS.sleep(1);
    }
    @Test
    //@PerfTest(threads = 100,duration=150000000)
    @PerfTest(threads = 100,invocations=1000000000)
    public void increment4MutilKeyName() throws InterruptedException {
        //通过随机数模拟不同的KEY-NAME
        String key = "yzd"+ToolUtil2.getNonce();
        LocalCacheUtil.getInstance().increment(key);
        //TimeUnit.SECONDS.sleep(1);
    }
}