package com.yzd.jutils.math;

/**
 * 十进制和26进制转换算法
 * 参考：
 * https://www.cnblogs.com/lavezhang/archive/2012/05/14/2499000.html
 * 最近在封装Excel组件，需要提供两个接口，分别根据单元索引和单元名称访问单元格。例如，GetCell(1, 2)和GetCell(“A2”)，这两种方法返回的结果是相同的。这里遇到一个问题，如何在单元索引（[1,2]）和单元名称(A2)之间相互转换
 */
public class NumberUtil {
    //十进制和26进制转换算法
    /// <summary>
    /// 将指定的自然数转换为26进制表示。映射关系：[1-26] ->[A-Z]。
    /// </summary>
    /// <param name="n">自然数（如果无效，则返回空字符串）。</param>
    /// <returns>26进制表示。</returns>
    public static String ToNumberSystem26(int n){
        String s = "";
        while (n > 0){
            int m = n % 26;
            if (m == 0) {
                m = 26;
            }
            s = (char)(m + 64) + s;
            n = (n - m) / 26;
        }
        return s;
    }

    /// <summary>
    /// 将指定的26进制表示转换为自然数。映射关系：[A-Z] ->[1-26]。
    /// </summary>
    /// <param name="s">26进制表示（如果无效，则返回0）。</param>
    /// <returns>自然数。</returns>
    public static int FromNumberSystem26(String s){
        if (s==null||s.trim().length()==0){
            return 0;
        }
        int n = 0;
        for (int i = s.length() - 1, j = 1; i >= 0; i--, j *= 26){
            char c =s.charAt(i);
            if (c < 'A' || c > 'Z') {
                return 0;
            }
            n += ((int)c - 64) * j;
        }
        return n;
    }
}
