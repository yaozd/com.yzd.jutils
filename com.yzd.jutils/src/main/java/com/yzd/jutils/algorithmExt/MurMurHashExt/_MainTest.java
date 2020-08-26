package com.yzd.jutils.algorithmExt.MurMurHashExt;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    public static void main(String[] args) {
        long test = MurmurHash.hash64("test");
        System.out.println(test);
    }
}
