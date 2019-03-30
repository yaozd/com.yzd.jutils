package com.yzd.jutils.atomicExt;

import org.apache.commons.lang3.ThreadUtils;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtomicRepositoryTest {
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Test
    @PerfTest(threads = 5,invocations=5)
    public void incrementAndGet() throws InterruptedException {
        for (int j = 0; j <100 ; j++) {
            AtomicRepository.PRODUCT.incrementAndGet();
        }
        //Thread.sleep(5);
        AtomicRepository.PRODUCT.printTest();

    }
}