package com.yzd.jutils.dateExt;

import com.yzd.jutils.print.PrintUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 生日提醒
 * Created by zd.yao on 2017/6/14.
 */
public class BirthdayReminder {
    public static void main(String[] args) {
        Date birthday1 = DateUtil2.string2Date("1988-06-21");
        Date birthday2 = DateUtil2.string2Date("1988-03-20");
        //最近的生日是那一天
        Date lastBirthday1 = getLastBirthday(birthday1);
        Date lastBirthday2 = getLastBirthday(birthday2);
        PrintUtil.outLn(DateUtil2.date2String(lastBirthday1));
        PrintUtil.outLn(DateUtil2.date2String(lastBirthday2));
        //两个日期比较相差几天
        Date today = DateUtil2.getCurrentDate();
        int dayDiff1 = DateUtil3.getDayDiff(today, lastBirthday1);
        PrintUtil.outLn(dayDiff1);

    }

    //最近的生日是那一天
    private static Date getLastBirthday(Date birthday) {
        Date today = DateUtil2.getCurrentDate();
        String birthdayOfThisYearStr = String.format("%s-%s", DateUtil2.date2String(today, "yyyy"), DateUtil2.date2String(birthday, "MM-dd"));
        Date birthdayOfThisYear = DateUtil2.string2Date(birthdayOfThisYearStr);
        int dayDiff = DateUtil3.getDayDiff(birthdayOfThisYear, today);
        //dayDiff=-1:代表今年的生日还没有过，否则今年的生日已经过完
        Date lastBirthday = dayDiff == -1 ? birthdayOfThisYear : DateUtils.addYears(birthdayOfThisYear, 1);
        return lastBirthday;
    }
}
