package com.yzd.jutils.atomicExt;

import org.junit.Test;

public class _MainTest {
    //最佳实践：AtomicInteger实现边界值控制
    /**
     * 解决：
     * 最佳实践：AtomicInteger实现边界值控制
     * 使用synchronized 保证顺序，对性能基本没有影响
     */
    @Test
    //@PerfTest(threads = 20, invocations = 100)
    public void batchInsert_getPkg(){
        for (int j = 0; j <1000000 ; j++) {
            DataRepository.PRODUCT.incrementAndGet();
        }
        //DataRepository.PRODUCT.printTest();
    }

}
