package com.yzd.jutils.exeExt;

/**
 * Created by zd.yao on 2017/6/13.
 */
public class ExeUtil {
    public static void openWindowsExe(final String command) {
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            //final String command = "notepad";// 记事本
            process = runtime.exec(String.format("\"%s\"", command));
        } catch (final Exception e) {
            System.out.println("Error win exec!");
        }
    }

    public static void openWindowsExeCMD(final String command) {
        final Runtime runtime = Runtime.getRuntime();
        Process process = null;
        try {
            //final String command = "notepad";// 记事本
            process = runtime.exec(String.format("cmd /c start %s", command));
        } catch (final Exception e) {
            System.out.println("Error win exec!");
        }
    }
}