package com.yzd.jutils.dateExt;

import com.yzd.jutils.print.PrintUtil;

import java.util.Date;

/**
 * Created by zd.yao on 2017/9/11.
 */
public class _MainTest {
    public static void main(String[] args){
       //计算年龄=32
        Date birthday=DateUtil3.parseDate("1985-9-13");
        Date currentDay=DateUtil3.parseDate("2017-9-13");
        int d=DateUtil3.getYearDiff(birthday,currentDay);
        PrintUtil.outLn("年龄="+d);

    }
}
