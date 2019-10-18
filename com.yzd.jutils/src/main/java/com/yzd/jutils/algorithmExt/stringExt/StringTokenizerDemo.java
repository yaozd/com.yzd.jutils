package com.yzd.jutils.algorithmExt.stringExt;

import java.util.StringTokenizer;

/**
 * @author: yaozhendong
 * @create: 2019-09-26 09:14
 **/

public class StringTokenizerDemo {

    public static void main(String[] args) {
        String str = null;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 1000; i++) {
            sb.append(i).append(";");
        }
        str = sb.toString();

        long begin = System.currentTimeMillis();

        StringTokenizer st = new StringTokenizer(str, ";");
        for (int i = 0; i < 1000000; i ++) {
            while (st.hasMoreTokens()) {
                st.nextToken();
            }
            st = new StringTokenizer(str, ";");
        }

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}