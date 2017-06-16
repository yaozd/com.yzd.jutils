package com.yzd.jutils.dateExt;

import com.yzd.jutils.print.PrintUtil;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class DateTest {
    private static final String[] WEEKDAYS = {"", "一", "二", "三", "四", "五", "六", "日"};
    private static final String[] MONTHS = {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
    private static final String[] AMPM = {"上午", "下午"};

    /**
     * Java解析GMT格式的时间- 承保止期=06-二月-2018
     * http://blog.csdn.net/id19870510/article/details/8622396
     *
     * @param args
     */
    public static void main(String[] args) {
        t1();
        t2();
    }

    private static void t1() {
        try {
            DateFormatSymbols sym = new DateFormatSymbols(Locale.CHINA);
            sym.setAmPmStrings(AMPM);
            sym.setShortWeekdays(WEEKDAYS);
            sym.setWeekdays(WEEKDAYS);
            sym.setMonths(MONTHS);
            SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy", sym);
            String s = "06-二月-2018";
            Date date = f.parse(s);
            PrintUtil.outLn(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void t2() {
        try {
            DateFormatSymbols sym = new DateFormatSymbols(Locale.CHINA);
            sym.setAmPmStrings(AMPM);
            sym.setShortWeekdays(WEEKDAYS);
            sym.setWeekdays(WEEKDAYS);
            sym.setMonths(MONTHS);
            SimpleDateFormat f = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", sym);
            String s = "六, 07 七月 2007 13:49:22 GMT";
            Date date = f.parse(s);
            PrintUtil.outLn(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
