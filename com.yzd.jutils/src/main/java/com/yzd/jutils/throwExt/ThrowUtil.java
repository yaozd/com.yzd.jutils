package com.yzd.jutils.throwExt;

public class ThrowUtil {
    private ThrowUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 模拟异常
     */
    public static void mockException() {
        throw new IllegalStateException("模拟异常");
    }

    /**
     * 模拟异常
     *eg:
     *if(System.currentTimeMillis()>100000000L) throw new IllegalStateException("模拟异常");
     */
    public static void mockExceptionByTime() {
        if (System.currentTimeMillis() > 100000000L) {
            throw new IllegalStateException("模拟异常");
        }
    }
}
