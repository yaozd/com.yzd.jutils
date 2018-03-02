package com.yzd.jutils.math;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

public class MathConvertUtil {
    //数值转换
    @Test
    public void T1_Convert() {
        Long t1=168039545856589824L;
        //十进制转为36进制
        String ttt= Long.toString(t1, 36);
        //36进制转为十进制
        Long ttt2=Long.parseLong(ttt,36);
        PrintUtil.outLn(ttt);
        PrintUtil.outLn(ttt2);
    }
}
