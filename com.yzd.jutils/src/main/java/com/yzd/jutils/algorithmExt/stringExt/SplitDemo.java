package com.yzd.jutils.algorithmExt.stringExt;

/**
 * @author: yaozhendong
 * @create: 2019-09-26 09:11
 **/


public class SplitDemo {

    public static void main(String[] args) {
        String str = null;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 1000; i++) {
            sb.append(i).append(";");
        }
        str = sb.toString();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            str.split(";");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}