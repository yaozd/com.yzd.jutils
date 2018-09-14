package com.yzd.jutils.timestampExt;

import com.yzd.jutils.dateExt.DateUtil2;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zd.yao on 2018/9/12.
 */
public class TimestampTest {
    //Java获取当前时间戳/时间戳转换
    //https://www.cnblogs.com/EasonJim/p/7896082.html
    public static void main(String[] args) {
        DateTime dt7 = new DateTime(System.currentTimeMillis());
        System.out.println(dt7.toDate().getTime());
        System.out.println(dt7.plusMinutes(5).toDate().getTime());
        System.out.println(dt7.plusMinutes(5).toDate().getTime()-dt7.toDate().getTime());
        System.out.println("==========================");
        // 精确到毫秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis());
        System.out.println(Calendar.getInstance().getTimeInMillis());
        System.out.println(new Date().getTime());

        // 精确到秒
        // 获取当前时间戳
        System.out.println(System.currentTimeMillis() / 1000);
        System.out.println(Calendar.getInstance().getTimeInMillis() / 1000);
        System.out.println(new Date().getTime() / 1000);

        // 精确到毫秒
        // 获取指定格式的时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        // 输出字符串
        System.out.println(df.format(new Date()));
        // 获取指定时间Date对象，参数是时间戳，只能精确到秒
        System.out.println(new Date(1536896407917L));
        //精确到毫秒才可以-byArvin 2018-0-12
        System.out.println("System.currentTimeMillis()");
        System.out.println(new Date(System.currentTimeMillis()));
        df.getCalendar();
        // 获取指定时间的时间戳
        try {
            System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").parse("2017/11/11 11:11:11:111").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
