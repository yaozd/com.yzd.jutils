package com.yzd.jutils.dateExt;

import com.yzd.jutils.print.PrintUtil;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 参考：
 * 吐槽java时间那点事儿 Joda Time-推荐
 * http://blog.csdn.net/54powerman/article/details/56292391
 * Joda-Time 用法
 http://blog.csdn.net/top_code/article/details/50374078
 joda-time的使用-推荐
 http://ylq365.iteye.com/blog/1769680
 */
public class JodaTimeUtil2 {

    /**
     *
     */
    @Test
    public void exmaple(){
        //初始化时间
        DateTime dateTime=new DateTime(2012, 12, 13, 18, 23,55);

        // 年,月,日,时,分,秒,毫秒
        DateTime dt3 = new DateTime(2011, 2, 13, 10, 30, 50, 333);// 2010年2月13日10点30分50秒333毫秒

        //下面就是按照一点的格式输出时间
        String str2 = dateTime.toString("MM/dd/yyyy hh:mm:ss.SSSa");
        String str3 = dateTime.toString("dd-MM-yyyy HH:mm:ss");
        String str4 = dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa");
        String str5 = dateTime.toString("MM/dd/yyyy HH:mm ZZZZ");
        String str6 = dateTime.toString("MM/dd/yyyy HH:mm Z");

        DateTimeFormatter format = DateTimeFormat .forPattern("yyyy-MM-dd HH:mm:ss");
        //时间解析
        DateTime dateTime2 = DateTime.parse("2012-12-21 23:22:45", format);

        //时间格式化，输出==> 2012/12/21 23:22:45 Fri
        String string_u = dateTime2.toString("yyyy/MM/dd HH:mm:ss EE");
        System.out.println(string_u);

        //格式化带Locale，输出==> 2012年12月21日 23:22:45 星期五
        String string_c = dateTime2.toString("yyyy年MM月dd日 HH:mm:ss EE", Locale.CHINESE);
        System.out.println(string_c);

        DateTime dt1 = new DateTime();// 取得当前时间

        // 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的
        DateTime dt2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2012-12-26 03:27:39");

        //计算两个日期间隔的天数
        LocalDate start=new LocalDate(2012, 12,14);
        LocalDate end=new LocalDate(2013, 01, 15);
        int days = Days.daysBetween(start, end).getDays();

        //计算两个日期间隔的小时数,分钟数,秒数

        //增加日期
        DateTime dateTime1 = DateTime.parse("2012-12-03");
        dateTime1 = dateTime1.plusDays(30);
        dateTime1 = dateTime1.plusHours(3);
        dateTime1 = dateTime1.plusMinutes(3);
        dateTime1 = dateTime1.plusMonths(2);
        dateTime1 = dateTime1.plusSeconds(4);
        dateTime1 = dateTime1.plusWeeks(5);
        dateTime1 = dateTime1.plusYears(3);

        // Joda-time 各种操作.....
        dateTime = dateTime.plusDays(1) // 增加天
                .plusYears(1)// 增加年
                .plusMonths(1)// 增加月
                .plusWeeks(1)// 增加星期
                .minusMillis(1)// 减分钟
                .minusHours(1)// 减小时
                .minusSeconds(1);// 减秒数

        //判断是否闰月
        DateTime dt4 = new DateTime();
        org.joda.time.DateTime.Property month = dt4.monthOfYear();
        System.out.println("是否闰月:" + month.isLeap());

        //取得 3秒前的时间
        DateTime dt5 = dateTime1.secondOfMinute().addToCopy(-3);
        dateTime1.getSecondOfMinute();// 得到整分钟后，过的秒钟数
        dateTime1.getSecondOfDay();// 得到整天后，过的秒钟数
        dateTime1.secondOfMinute();// 得到分钟对象,例如做闰年判断等使用

        // DateTime与java.util.Date对象,当前系统TimeMillis转换
        DateTime dt6 = new DateTime(new Date());
        Date date = dateTime1.toDate();
        DateTime dt7 = new DateTime(System.currentTimeMillis());
        dateTime1.getMillis();

        Calendar calendar = Calendar.getInstance();
        dateTime = new DateTime(calendar);
    }

    /**
     *计算间隔和区间
     */
    @Test
    public void timeInterval(){
        DateTime begin = new DateTime("2015-02-01");
        DateTime end = new DateTime("2016-05-01");
        //计算区间毫秒数
        Duration d = new Duration(begin, end);
        long millis = d.getMillis();
        //计算区间天数
        Period p = new Period(begin, end, PeriodType.days());
        int days = p.getDays();
        //计算特定日期是否在该区间内
        Interval interval = new Interval(begin, end);
        boolean contained = interval.contains(new DateTime("2015-03-01"));
    }
    /**
     * joda-time与date的转换
     */
    @Test
    public void date2DateTime(){
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1); // out: 2016-02-26T16:02:57.582+08:00
        DateTime dateTime2 = new DateTime(2016,2,14,0,0,0);
        System.out.println(dateTime2); // out: 2016-02-14T00:00:00.000+08:00
        DateTime dateTime3 = new DateTime(1456473917004L);
        System.out.println(dateTime3); // out: 2016-02-26T16:05:17.004+08:00
        DateTime dateTime4 = new DateTime(new Date());
        System.out.println(dateTime4); // out: 2016-02-26T16:07:59.970+08:00
        DateTime dateTime5 = new DateTime("2016-02-15T00:00:00.000+08:00");
        System.out.println(dateTime5); // out: 2016-02-15T00:00:00.000+08:00
        DateTime dateTime6 = new DateTime("2016-02-15");
        System.out.println(dateTime6);
        // 根据指定格式,将时间字符串转换成DateTime对象,这里的格式和上面的输出格式是一样的
        DateTime dateTime7 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime("2012-12-26 03:27:39");
        System.out.println(dateTime7);
        //
        Date d3=new Date();
        DateTime dateTime8=new DateTime(d3);
        PrintUtil.outLn(dateTime8.toString("yyyy-MM-dd HH:mm:ss"));
        //
        DateTime dt = new DateTime();
        //转换成java.util.Date对象
        Date d1 = new Date(dt.getMillis());
        Date d2 = dt.toDate();
        SimpleDateFormat sdf = DateFormatUtils.getFormat("yyyy-MM-dd HH:mm:ss");
        PrintUtil.outLn(sdf.format(d1));
    }

    /**
     *计算两个日期的相隔天数
     */
    @Test
    public void getBetweenDay(){
        DateTime nowTime = new DateTime();
        DateTime futureTime = new DateTime(2018, 10, 1, 0, 0, 0);
        Integer days = Days.daysBetween(nowTime, futureTime).getDays();
        PrintUtil.outLn(days);
    }
    /**
     *  获取今天的开始时间：比如：2014-06-19 00:00:00
     */
    @Test
    public void withTimeAtStartOfDay(){
        DateTime nowTime = new DateTime();
        DateTime startOfDay =  nowTime.withTimeAtStartOfDay();
        String val=startOfDay.toString("yyyy-MM-dd HH:mm:ss");
        PrintUtil.outLn(val);
    }

    /**
     * 获取今天的结束时间：比如：2014-06-19 23:59:59
     */
    @Test
    public void withMaximumValue(){
        DateTime nowTime = new DateTime();
        DateTime endOfDay =  nowTime.millisOfDay().withMaximumValue();
        String val=endOfDay.toString("yyyy-MM-dd HH:mm:ss.SSS");
        PrintUtil.outLn(val);
    }
}
