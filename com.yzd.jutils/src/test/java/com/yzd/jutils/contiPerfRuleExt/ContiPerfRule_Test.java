package com.yzd.jutils.contiPerfRuleExt;

import com.yzd.jutils.guava.cacheExt4PublicCache.LocalCacheUtil;
import com.yzd.jutils.guava.cacheExt4PublicCache.ToolUtil2;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class ContiPerfRule_Test {

    /**
              <dependency>
                  <groupId>org.databene</groupId>
                  <artifactId>contiperf</artifactId>
                  <version>2.1.0</version>
                  <scope>test</scope>
              </dependency>
     */
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(threads = 100,duration=150000000)
    public void increment() throws InterruptedException {
        String key = "yzd";
        //TimeUnit.SECONDS.sleep(1);
    }
    @Test
    //@PerfTest(threads = 100,duration=150000000)
    @PerfTest(threads = 100,invocations=10000)
    public void increment4MutilKeyName() throws InterruptedException {
        //通过随机数模拟不同的KEY-NAME
        String key = "yzd"+ToolUtil2.getNonce();
        //TimeUnit.SECONDS.sleep(1);
    }
}
