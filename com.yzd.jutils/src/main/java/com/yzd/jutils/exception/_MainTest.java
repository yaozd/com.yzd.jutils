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
     * 1.ex.getMessage()--对性能的影响比较小
     * 2.ex.printStackTrace();获取异常堆栈信息对性能的影响比较大
     * 3.logger.error("fuck", ex);把异常堆栈信息输出到日志对性能的影响特别严重
     * 总结
     * 处理异常的几个步骤里，对性能的耗费从大到小依次为：输出到日志、获取异常堆栈、创建并 catch 异常。
     */
    /**
     * catch 中不做任何事情
     * ex.getMessage()--对性能的影响比较小
     */
    @Test
    public void t1() {
        doExTest_t1();
        doExTest_t1();
    }

    void doExTest_t1() {
        long start = System.nanoTime();
        for (int i = 0; i < 100000; ++i) {
            try {
                throw new RuntimeException("" + Math.random());
            } catch (Exception e) {
                e.getMessage();
            }
        }
        System.out.println("time: " + (System.nanoTime() - start));
    }

    /**
     * catch 中输出异常到日志
     * logger.error("fuck", ex);把异常堆栈信息输出到日志对性能的影响特别严重
     */
    @Test
    public void t2() {
        doExTest_t2();
        doExTest_t2();
    }

    private static final Logger logger = LoggerFactory.getLogger(_MainTest.class);

    void doExTest_t2() {
        long start = System.nanoTime();
        for (int i = 0; i < 100000; ++i) {
            try {
                throw new RuntimeException("" + Math.random());
            } catch (Exception e) {
                logger.error("exception:", e);
            }
        }
        System.out.println("time: " + (System.nanoTime() - start));
    }

    /**
     * catch 中获取异常栈
     * ex.printStackTrace();获取异常堆栈信息对性能的影响比较大
     */
    @Test
    public void t3() {
        doExTest_t3();
        doExTest_t3();
    }

    void doExTest_t3() {
        long start = System.nanoTime();

        for (int i = 0; i < 100000; ++i) {
            try {
                throw new RuntimeException("" + Math.random());
            } catch (Exception e) {
                StackTraceElement[] stackTrace = e.getStackTrace();
            }
        }

        System.out.println("time: " + (System.nanoTime() - start));
    }
}
