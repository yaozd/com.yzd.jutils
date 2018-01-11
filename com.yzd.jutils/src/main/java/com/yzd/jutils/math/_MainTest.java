package com.yzd.jutils.math;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

public class _MainTest {
    @Test
    public void T1_MoneyUtil() {
        String v1 = MoneyUtil.amountToChinese(22222222);
        PrintUtil.outLn(v1);
        String v2= MoneyUtil.CNValueOf("1111111111111111");
        PrintUtil.outLn(v2);
        String v3 = MoneyUtil.formatRMB(new Double("2222222.4"));
        PrintUtil.outLn(v3);
    }
}
