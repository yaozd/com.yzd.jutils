package com.yzd.jutils.algorithmExt;

public class _MainTest {

    public static void main(String[] ww) {
        /**
         * MIN：最小值，MAX:最大值，TOTAL:总金额,N:人数
         */
        int min = 6, max = 12, total = 42, n = 5;

        //----------------------------
        // 简单随机红包生成java实现
        // https://blog.csdn.net/gfdgdshhg/article/details/78428952
        int[] numArray = HongbaoUtil.hb(n, total, min, max);
        for (Integer i : numArray) {
            System.out.println(i);
        }
    }
}
