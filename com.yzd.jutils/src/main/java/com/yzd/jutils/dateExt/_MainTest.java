package com.yzd.jutils.dateExt;

import com.yzd.jutils.print.PrintUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by zd.yao on 2017/9/11.
 */
public class _MainTest {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("现在时间是：" + new Date());

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, +1);
        Date now = c.getTime();
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(now));

        //计算年龄=32
        Date birthday = DateUtil3.parseDate("1985-9-13");
        Date currentDay = DateUtil3.parseDate("2017-9-13");
        int d = DateUtil3.getYearDiff(birthday, currentDay);
        PrintUtil.outLn("年龄=" + d);

    }
}
