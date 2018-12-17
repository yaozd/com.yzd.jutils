package com.yzd.jutils.exception;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Java 进阶：异常影响性能吗？-byArvin推荐-2018-12-17-1758
 * https://blog.csdn.net/hustspy1990/article/details/78075394
 */
public class _MainTest {
    /**
     * catch 中不做任何事情
     */
    @Test
    public void t1(){
        doExTest_t1();
        doExTest_t1();
    }
    void doExTest_t1() {
        long start = System.nanoTime();
        for (int i=0; i<100000; ++i) {
            try {
                throw new RuntimeException("" + Math.random());
            } catch (Exception e) {
            }
        }
        System.out.println("time: " + (System.nanoTime() - start));
    }
    /**
     * catch 中输出异常到日志
     */
    @Test
    public void t2(){
        doExTest_t2();
        doExTest_t2();
    }
    private static final Logger logger = LoggerFactory.getLogger(_MainTest.class);
    void doExTest_t2() {
        long start = System.nanoTime();
        for (int i=0; i<100000; ++i) {
            try {
                throw new RuntimeException("" + Math.random());
            } catch (Exception e) {
                logger.error("fuck", e);
            }
        }
        System.out.println("time: " + (System.nanoTime() - start));
    }
    
}
