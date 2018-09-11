package com.yzd.jutils.springBoot.mockBeanTest;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

/***
 *
 *
 * Created by yzd on 2018/9/11 12:52.
 */

public class ContiPerf_Test {
    //使用contiperf进行压测
    //https://blog.csdn.net/linsongbin1/article/details/51304349

    @Rule
    public ContiPerfRule i = new ContiPerfRule();

    //通过PerfTest制定并发线程数和执行时间，如threads=100，duration=15000。这里15000表示15秒。
    @Test
    @PerfTest(threads = 100,duration=15000)
    public void test1() throws Exception {
    }
    //通过PerfTest制定并发线程数和invocations指定调用的次数。
    @Test
    @PerfTest(threads = 100,invocations=10000)
    public void test2() throws Exception {
    }
}
