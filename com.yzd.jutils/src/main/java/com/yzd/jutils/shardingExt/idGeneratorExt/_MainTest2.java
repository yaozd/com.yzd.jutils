package com.yzd.jutils.shardingExt.idGeneratorExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class _MainTest2 {
    @Test
    public void IdGenerator() {
        Long t1 = 168009112355618816L;
        String ttt = Long.toString(t1, 36);
        PrintUtil.outLn(ttt);
        long t2 = t1 >> 22;
        //1477929600000L=2016-11-1-0:0:0
        long t3 = 1477929600000L + t2;
        System.out.println(t3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(t3);
        String t4 = simpleDateFormat.format(date);
        System.out.println(t4);

        long t5 = t1 >> 41;
        System.out.println(t5);
        String t6 = Long.toString(14481376401L, 36);
        System.out.println(t6);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmss");
        String t42 = simpleDateFormat2.format(date);
        String tt2 = "1" + t42 + t5;
        System.out.println(tt2);
        String t61 = Long.toString(Long.parseLong(tt2), 36);
        System.out.println(t61);
    }

    @Test
    public void t2() {
        Long t1 = 168034144389955584L;
        String ttt = Long.toString(t1, 36);
        PrintUtil.outLn(ttt);
        long t2 = t1 >> 22;
        //1477929600000L=2016-11-1-0:0:0
        long t3 = 1477929600000L + t2;
        System.out.println(t3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(t3);
        String t4 = simpleDateFormat.format(date);
        System.out.println(t4);

        long t5 = t1 >> 41;
        System.out.println(t5);
        String t6 = Long.toString(14481376401L, 36);
        System.out.println(t6);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmss");
        String t42 = simpleDateFormat2.format(date);
        String tt2 = "1" + t42 + t5;
        System.out.println(tt2);
        String t61 = Long.toString(Long.parseLong(tt2), 36);
        System.out.println(t61);
        String t1Str = t1.toString();
        String a1 = t1Str.substring(t1Str.length() - 5);
        PrintUtil.outLn(a1);
    }

    @Test
    public void t3() {
        IdGenerator t = new IdGenerator();
        for (int i = 1; i < 10; i++) {
            long t1 = t.generateId().longValue();
            System.out.println(t1);
        }

    }

    @Test
    public void t4() {
        Long t1 = 168039545856589824L;
        String ttt = Long.toString(t1, 32);
        PrintUtil.outLn(ttt);
        long t2 = t1 >> 22;
        //1477929600000L=2016-11-1-0:0:0
        long t3 = 1477929600000L + t2;
        System.out.println(t3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date(t3);
        String t4 = simpleDateFormat.format(date);
        System.out.println(t4);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmss");
        String t42 = simpleDateFormat2.format(date);
        String t1Str = t1.toString();
        String t5 = t1Str.substring(t1Str.length() - 5);
        PrintUtil.outLn(t5);
        String tt2 = "1" + t42 + t5;
        System.out.println(tt2);
        String t61 = Long.toString(Long.parseLong(tt2), 36);
        System.out.println(t61);
    }

    @Test
    public void t5() {
        Long t1 = 168039545856589824L;
        //十进制转为36进制
        String ttt = Long.toString(t1, 36);
        //36进制转为十进制
        Long ttt2 = Long.parseLong(ttt, 36);
        PrintUtil.outLn(ttt);
        PrintUtil.outLn(ttt2);
        long t2 = t1 >> 22;
        //1477929600000L=2016-11-1-0:0:0
        long t3 = 1477929600000L + t2;
        System.out.println(t3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(t3);
        String t4 = simpleDateFormat.format(date);
        System.out.println(t4);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmss");
        String t42 = simpleDateFormat2.format(date);
        String t1Str = t1.toString();
        String t5 = t1Str.substring(t1Str.length() - 5);
        PrintUtil.outLn(t5);
        String tt2 = "1" + t42 + t5;
        System.out.println(tt2);
        String t61 = Long.toString(Long.parseLong(tt2), 36);
        System.out.println(t61);
        String result = "5001" + t4 + t61;
        System.out.println(result);
    }

    @Test
    public void t6() {
        Long t1 = 168039545856589824L;
        String result = toHQRS_OrderNo(t1);
        System.out.println(result);
    }

    //转为第三方的订单号规则
    private String toHQRS_OrderNo(Long t1) {
        String ttt = Long.toString(t1, 36);
        //PrintUtil.outLn(ttt);
        long t2 = t1 >> 22;
        //1477929600000L=2016-11-1-0:0:0
        long t3 = 1477929600000L + t2;
        //System.out.println(t3);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(t3);
        String t4 = simpleDateFormat.format(date);
        //System.out.println(t4);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HHmmss");
        String t42 = simpleDateFormat2.format(date);
        String t1Str = t1.toString();
        String t5 = t1Str.substring(t1Str.length() - 5);
        //PrintUtil.outLn(t5);
        String tt2 = "1" + t42 + t5;
        //System.out.println(tt2);
        String t61 = Long.toString(Long.parseLong(tt2), 36);
        //System.out.println(t61);
        String result = "5001" + t4 + t61;
        return result;
    }
}
