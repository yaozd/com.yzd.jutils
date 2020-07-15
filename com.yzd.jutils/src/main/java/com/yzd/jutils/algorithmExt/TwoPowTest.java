package com.yzd.jutils.algorithmExt;

/**
 * 判断一个数是不是2的N次方
 * Java——实现 pow(x, n)（即：计算 x 的 n 次幂函数）
 * https://blog.csdn.net/weixin_44040023/article/details/103339201
 *
 * @Author: yaozh
 * @Description:
 */
public class TwoPowTest {
    public static void main(String[] args) {
        int num = 2;
        int pow = 5;
        System.out.println(power2(num, pow));
        System.out.println("N次方:" + powerN(2147418112, 2));
    }

    /**
     * Java——实现 pow(x, n)（即：计算 x 的 n 次幂函数）
     * https://blog.csdn.net/weixin_44040023/article/details/103339201
     * 题目来源：力扣（LeetCode）
     * 链    接：https://leetcode-cn.com/problems/powx-n
     * https://leetcode-cn.com/problems/powx-n/solution/powx-n-by-leetcode-solution/
     *
     * @param x
     * @param n
     * @return
     */
    public static double power2(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        double half = power2(x, n / 2);
        double rest = power2(x, n % 2);
        return half * half * rest;
    }

    /**
     * 判断一个数是不是N的多少次方
     *
     * @param n
     * @param a
     * @return
     */
    public static double powerN(int n, int a) {
        int b = 0;
        double val = 0;
        while (true) {
            val = power2(a, b);
            if (val >= n) {
                break;
            }
            b++;
        }
        double c = val - n;
        System.out.println("val-n=" + c);
        return c == 0 ? b : b - 1;
    }

    /**
     * 如何使用Java计算次方（次幂）-废弃：效率太低
     *
     * @param a
     * @param b
     * @return
     */
    public static int power(int a, int b) {
        int power = 1;
        for (int c = 0; c < b; c++) {
            power *= a;
        }
        return power;
    }
}
