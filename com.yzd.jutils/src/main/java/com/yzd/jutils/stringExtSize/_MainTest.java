package com.yzd.jutils.stringExtSize;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

/**
 * Created by zd.yao on 2017/12/19.
 */
public class _MainTest {
    /**
     * java中如何计算一个字符串的byte长度
     * /计算字符串的大小（字节）
     */
    @Test
    public void string_size_test()
    {
        //计算字符串的大小（字节）
        PrintUtil.outLn(getLength("P01.S:SaveAllKeySet:2pe1x1in0mbk"));
        PrintUtil.outLn(getLength("测试"));
    }
    public static int getLength(String s) {
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = Character.codePointAt(s, i);
            if (ascii >= 0 && ascii <= 255) {
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }
}
