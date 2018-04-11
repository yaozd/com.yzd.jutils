package com.yzd.jutils.math;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.util.Date;

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
    //十进制与32进制转换
    @Test
    public void T2() {
        Long t1=16803L;
        String ttt= Long.toString(t1, 32);
        System.out.println(ttt);
        Long t2=Long.parseLong(ttt,32);
        System.out.println(t2);

        // TODO Auto-generated method stub
        System.out.println("十进制转换到其它进制：");
        int x = 123 ;
        String str1 = Integer.toHexString(x) ;      ///10进制转换成16进制的字符串
        System.out.println(str1);
        String str2 = Integer.toOctalString(x) ;     ///10进制转换成8进制的字符串
        System.out.println(str2);
        String str3 = Integer.toBinaryString(x) ;    ///10进制转换成2进制的字符串
        System.out.println(str3);
        String str4 = Integer.toString(123456,7) ;  ///10进制转换成7进制的字符串
        ///String str4 = Integer.toString(i,x) ;  ///10进制的数字i转换成x进制的字符串
        System.out.println("其它制转换到十进制：");
        int y1= Integer.valueOf("FFFF",16);     ///16进制转换成10进制
        System.out.println(y1);
        int y2=Integer.valueOf("776",8);        ///8进制转换成10进制
        System.out.println(y2);
        int y3=Integer.valueOf("0101",2);       //2进制转换成10进制
        System.out.println(y3);
        int y4=Integer.valueOf("101",7);        //7进制转换成10进制
        System.out.println(y4);
        ///Integer.valueOf("str",x); ///可以为任意进制的字符串str转换成x进制的10进制数
        System.out.println("其它的可能用到的函数：");
        //static int parseInt(String s, int radix) //使用第二个参数指定的基数，将字符串参数解析为有符号的整数。
        int n = Integer.parseInt("776", 8) ; ///8进制转换成10进制
        System.out.println(n);
        ///Integer.valueOf()返回一个“integer对象”和Integer.parseInt()返回一个“int值”的区别在于，返回值不同
        ///基本常识，其他的非10进制的数的保存，基本都是以字符串的形式
        ///例子：7进制到8进制的转换
        String q = "6523" ; ///7进制的字符串
        String b = Integer.toString(Integer.valueOf(q,7),8) ;///这样7进制就变成8进制了
    }
    /**
     * 十进制和26进制转换算法
     * 参考：
     * https://www.cnblogs.com/lavezhang/archive/2012/05/14/2499000.html
     * 最近在封装Excel组件，需要提供两个接口，分别根据单元索引和单元名称访问单元格。例如，GetCell(1, 2)和GetCell(“A2”)，这两种方法返回的结果是相同的。这里遇到一个问题，如何在单元索引（[1,2]）和单元名称(A2)之间相互转换
     */
    @Test
    public void t3(){
        String t1= NumberUtil.ToNumberSystem26(1);
        System.out.println(t1);
        int t2=NumberUtil.FromNumberSystem26(t1);
        System.out.println(t2);
        int[] numbers = { 1, 10, 26, 27, 256, 702, 703,52142362,1523447874 };
        for (int n : numbers){
            String s =Long.toString(n, 32);
            System.out.println(n + "\t" + s + "\t" + NumberUtil.FromNumberSystem26(s));
        }
    }
    @Test
    public void t4(){
        String t1= NumberUtil.ToNumberSystem26(1);
        System.out.println(t1);
        int t2=NumberUtil.FromNumberSystem26(t1);
        System.out.println(t2);
        int[] numbers = { 1, 10, 26, 27, 256, 702, 703,52142362,1523447874 };
        for (int n : numbers){
            String s = NumberUtil.ToNumberSystem26(n);
            System.out.println(n + "\t" + s + "\t" + NumberUtil.FromNumberSystem26(s));
        }
    }
    //渠道ID生成规则
    @Test
    public void t5(){
        int secondTimestamp=getSecondTimestampTwo(new Date());
        String secondTimestampTo26 = NumberUtil.ToNumberSystem26(secondTimestamp);
        int v26ToSecondTimestamp=NumberUtil.FromNumberSystem26(secondTimestampTo26);
        String secondTimestampToStr=String.valueOf(secondTimestamp);
        String last4StrOfSecondTimestamp=secondTimestampToStr.substring(secondTimestampToStr.length()-4);
        //时间戳的26进制+最后4位的组合
        //DXESWGC0685
        String channelId=secondTimestampTo26+last4StrOfSecondTimestamp;
        System.out.println(secondTimestampToStr);
        System.out.println(secondTimestampTo26);
        System.out.println(v26ToSecondTimestamp);
        System.out.println(last4StrOfSecondTimestamp);
        System.out.println(channelId);

    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static int getSecondTimestampTwo(Date date){
        if (null == date) {
            return 0;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Integer.valueOf(timestamp);
    }
}
