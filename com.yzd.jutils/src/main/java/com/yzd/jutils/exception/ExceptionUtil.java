package com.yzd.jutils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 针对应用程序内部的异常-打印堆栈信息-方法来自dubbo
 * Created by zd.yao on 2017/6/22.
 */
public class ExceptionUtil {
    /***
     * 此方法来自网络
     * @param e
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        } finally {
            pw.close();
        }

    }

    //方法取自：dubbo的StringUtils-目前暂时推荐使用此方法
    public static String exceptionToString(Throwable e) {
        UnsafeStringWriter w = new UnsafeStringWriter();
        PrintWriter p = new PrintWriter(w);
        p.print(e.getClass().getName());
        if (e.getMessage() != null) {
            p.print(": " + e.getMessage());
        }
        p.println();
        String var3;
        try {
            e.printStackTrace(p);
            var3 = w.toString();
        } finally {
            p.close();
        }
        return var3;
    }

    //方法取自：dubbo的StringUtils-目前暂时推荐使用此方法
    public static String exceptionToString(String msg, Throwable e) {
        UnsafeStringWriter w = new UnsafeStringWriter();
        w.write(msg + "\n");
        PrintWriter p = new PrintWriter(w);

        String var4;
        try {
            e.printStackTrace(p);
            var4 = w.toString();
        } finally {
            p.close();
        }

        return var4;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            t();
        }

    }

    private static void t() {
        try {
            String[] arr = {"111", "222"};
            arr[2] = "fff";
            //java.lang.ArithmeticException: / by zero
            int i = 1 - 1;
            int t = 10 / i;
            //
        } catch (Exception e) {
            String v2 = exceptionToString(e);
            System.out.println(v2);
            String v3 = exceptionToString("XX操作异常", e);
            System.out.println(v3);
            String info = getErrorInfoFromException(e);
            System.out.println(info);
        }
    }
}
