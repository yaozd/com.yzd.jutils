package com.yzd.jutils.math;

import java.math.BigDecimal;

/**
 * Created by zd.yao on 2017/6/13.
 */
public class BigDecimalUtil {
    /**
     *最近数字舍入(5进)。这是我们最经典的四舍五入。
     */
    private static int DEFAULT_ROUND = BigDecimal.ROUND_HALF_UP;

    public static String add(String d1, String d2)
    {        // 进行加法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).toString();
    }
    public static String sub(String d1, String d2)
    {        // 进行减法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.subtract(b2).toString();
    }
    public static String mul(String d1, String d2)
    {
        // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.multiply(b2).toString();
    }
    public static String div(String d1, String d2,int len) {
        // 进行除法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.divide(b2,len,DEFAULT_ROUND).toString();
    }
    //最近数字舍入(5进)。这是我们最经典的四舍五入。
    public static String round(String d, int len) {
        // 进行四舍五入操作
        BigDecimal b1 = new BigDecimal(d);
        BigDecimal b2 = new BigDecimal(1);
        // 任何一个数字除以1都是原数字
        // ROUND_HALF_UP是BigDecimal的一个常量，
        //表示进行四舍五入的操作
        return b1.divide(b2, len, DEFAULT_ROUND).toString();
    }
    public static void main(String[] args) {
        System.out.println(4.015*100);
        String v2= BigDecimalUtil.mul("4.015", "100");
        String v1= BigDecimalUtil.div("200000", "0.002",2);
        System.out.println(v2);
        //删除小数点后面的数据
        Object obj= new Double(v2).intValue();
        System.out.println(obj.toString());
        //
        System.out.println(v1);
    }
}
