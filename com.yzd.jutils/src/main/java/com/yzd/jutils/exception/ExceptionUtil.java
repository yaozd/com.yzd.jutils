package com.yzd.jutils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by zd.yao on 2017/6/22.
 */
public class ExceptionUtil {
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }

    }

    public static void main(String[] args) {
        try {
            String[] arr = {"111", "222"};
            arr[2] = "fff";
            //java.lang.ArithmeticException: / by zero
            int i=1-1;
            int t= 10/i;
            //
        } catch (Exception e) {
            String info = getErrorInfoFromException(e);
            System.out.println(info);
        }
    }
}
