package com.yzd.jutils.algorithmExt.stringExt;

/**
 * @author: yaozhendong
 * @create: 2019-09-26 09:16
 **/

public class CustomerSplitDemo {

    public static void main(String[] args) {
        String str = null;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 1000; i++) {
            sb.append(i).append(";");
        }
        str = sb.toString();

        long begin = System.currentTimeMillis();

        String temp = str;
        for (int i = 0; i < 1000000; i++) {
            while (true) {
                String splitStr = null; // 保留截取的字符串
                int index = temp.indexOf(";");
                if (index < 0) {
                    break;
                }
                splitStr = temp.substring(0, index);
                temp = temp.substring(index + 1);
            }
            temp = str;
        }

        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }

}
