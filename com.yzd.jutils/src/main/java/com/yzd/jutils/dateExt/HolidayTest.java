package com.yzd.jutils.dateExt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/***
 * JAVA_判断日期是否为工作日（排除节假日和调整周末上班）
 * https://blog.csdn.net/icecoola_/article/details/80945055
 * 判断当前日期是否为工作日，包括判断是否周末，是否节假日，是否周末调整上班。
 *
 * 判断是否周末，我们可以通过java方法就可以实现，但是判断是否节假日和，调整的周末上班，这个就需要我们自己去维护了。
 * 搞2个集合，分别来存节假日和调整上班的日期。
 * Created by yzd on 2018/9/7 16:39.
 */


public class HolidayTest {
    //节假日信息可以通过百度搜索“2018年放假安排时间表”，同理“2019年放假安排时间表”来进行维护

    //节假日列表
    private static List<Calendar> holidayList = new ArrayList<Calendar>();
    //周末为工作日
    private static List<Calendar> weekendList = new ArrayList<Calendar>();

    /**
     * @param args return void    返回类型
     *             throws
     */
    public static void main(String[] args) {
        try {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar ca = Calendar.getInstance();
            Date d = df.parse("2016-09-15");
            ca.setTime(d);//设置当前时间

            HolidayTest ct = new HolidayTest();
            ct.initHolidayList("2016-09-15");//初始节假日
            ct.initHolidayList("2016-09-16");//初始节假日
            ct.initHolidayList("2016-09-17");//初始节假日

            ct.initWeekendList("2016-09-18");//初始周末为工作日

            boolean k = checkHoliday(ca);
            System.out.println(k);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getClass());
            e.printStackTrace();
        }

    }


    /**
     * 验证日期是否是节假日
     *
     * @param calendar 传入需要验证的日期
     * @return return boolean    返回类型  返回true是节假日，返回false不是节假日
     * throws
     */
    public static boolean checkHoliday(Calendar calendar) throws Exception {

        //判断日期是否是周六周日
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {

            //判断日期是否是节假日
            for (Calendar ca : weekendList) {
                if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
                        ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                    return false;
                }
            }

            return true;
        }
        //判断日期是否是节假日
        for (Calendar ca : holidayList) {
            if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                    ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
                    ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 把所有节假日放入list
     *
     * @param date 从数据库查 查出来的格式2016-05-09
     *             return void    返回类型
     *             throws
     */
    public void initHolidayList(String date) {

        String[] da = date.split("-");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(da[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(da[1]) - 1);//月份比正常小1,0代表一月
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(da[2]));
        holidayList.add(calendar);
    }

    /**
     * 初始化周末被调整为工作日的数据
     */
    public void initWeekendList(String date) {
        String[] da = date.split("-");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(da[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(da[1]) - 1);//月份比正常小1,0代表一月
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(da[2]));
        weekendList.add(calendar);
    }
}