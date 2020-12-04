package com.yzd.jutils.javaEXt;

/**
 * - java不打印异常堆栈
 * - [java不打印异常堆栈](https://blog.csdn.net/tengdazhang770960436/article/details/91838820)
 * - [异常栈信息不见了之JVM参数OmitStackTraceInFastThrow](https://www.jianshu.com/p/cc1bd35466cb)
 *
 * @Author: yaozh
 * @Description:
 */
public class NoPrintStackTraceTest {
    public static void main(String[] args) {
        String test = null;
        int i = 0;
        while (true) {
            try {
                test.length();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
