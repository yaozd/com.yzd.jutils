package com.yzd.jutils.timeExt;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/***
 *
 *
 * Created by yzd on 2018/8/31 17:49.
 */

public class TimeUtilForInfluxdbGrafana {
    //参考：
    //Java处理GMT时间和UTC时间
    //https://blog.csdn.net/top_code/article/details/50462922
    //java UTC时间和local时间相互转换
    //https://blog.csdn.net/w592376568/article/details/80742695
    //===========================================
    //本代码主要用于influxdb的SQL语句中的
    // SELECT "water_level","location" FROM "h2o_feet" WHERE time >= '2015-08-18T00:00:00Z' AND time <= '2015-08-18T00:30:00Z'
    //主要用于grafana在使用influxdb时的时间格式转换
    //java UTC时间和local时间相互转换
    @Test
    public void t1(){
        String localDate="2018-08-31 17:54:53";
        Date utcDate=localToUTC(localDate);
        //T代表后面跟着时间，Z代表UTC统一时间
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(utcDate);
        System.out.println(time);
    }
    @Test
    public void t2() throws ParseException {
        //T代表后面跟着时间，Z代表UTC统一时间
        //格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = sdf.format(new Date());
        System.out.println(time);

        //解析时间 2016-01-05T15:09:54Z
        Date date = sdf.parse(time);
        System.out.println(date);
    }
    /**
     * local时间转换成UTC时间
     * @param localTime
     * @return
     */
    public static Date localToUTC(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());
        return utcDate;
    }
    @Test
    public void t3(){
        String utcDate="2018-08-31 17:54:53";
        Date localDate=utcToLocal(utcDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(localDate);
        System.out.println(time);
    }
    /**
     * utc时间转成local时间
     * @param utcTime
     * @return
     */
    public static Date utcToLocal(String utcTime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date utcDate = null;
        try {
            utcDate = sdf.parse(utcTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.setTimeZone(TimeZone.getDefault());
        Date locatlDate = null;
        String localTime = sdf.format(utcDate.getTime());
        try {
            locatlDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return locatlDate;
    }
}
