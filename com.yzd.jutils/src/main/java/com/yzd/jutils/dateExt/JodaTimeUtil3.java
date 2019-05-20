package com.yzd.jutils.dateExt;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class JodaTimeUtil3 {
    public static Integer getBetweenDay(Date stDate, Date endDate) {
        DateTime stTime = new DateTime(stDate);
        DateTime endTime = new DateTime(endDate);
        Integer days = Days.daysBetween(stTime, endTime).getDays();
        return days;
    }

    public static String getYestdayStr() {
        DateTime dt = new DateTime(new Date());
        return dt.plusDays(-1).toString("yyyy-MM-dd");
    }

    /**
     * 时间转为字符串格式
     *
     * @param dt
     * @param dateTimeFormat
     * @return
     */
    public static String getDateStr(Date dt, String dateTimeFormat) {
        DateTime dateTime = new DateTime(dt);
        return dateTime.toString(dateTimeFormat);
    }

    public static String getDateStr(String dateStr, String dateTimeFormat) {
        DateTime dt = createDateTime(dateStr);
        return dt.toString(dateTimeFormat);
    }

    public static Date getDate(String dateStr) {
        DateTime dt = createDateTime(dateStr);
        return dt.toDate();
    }

    public static DateTime createDateTime(String dateStr) {
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        //时间解析
        return DateTime.parse(dateStr, format);
    }

    public static Date plusMinutes(int val) {
        DateTime dt = new DateTime(new Date());
        dt = dt.plusMinutes(val);
        return dt.toDate();
    }

    public static Date plusHours(int val) {
        DateTime dt = new DateTime(new Date());
        dt = dt.plusHours(val);
        System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss"));
        return dt.toDate();
    }
}
