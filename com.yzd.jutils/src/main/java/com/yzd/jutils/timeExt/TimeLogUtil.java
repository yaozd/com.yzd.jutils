package com.yzd.jutils.timeExt;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zd.yao on 2017/10/18.
 * 作用：消息队列类型的任务，用于减少日志的输出
 * 参考：
 * Java: getMinutes and getHours
 * https://stackoverflow.com/questions/907170/java-getminutes-and-gethours/907207
 * Date date = new Date();   // given date
 * Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
 * calendar.setTime(date);   // assigns calendar to given date
 * calendar.get(Calendar.HOUR_OF_DAY); // gets hour in 24h format
 * calendar.get(Calendar.HOUR);        // gets hour in 12h format
 * calendar.get(Calendar.MONTH);       // gets month number, NOTE this is zero based!
 * calendar.get(Calendar.MINUTE);
 */
public class TimeLogUtil {
    private static class SingletonHolder {
        private static final TimeLogUtil INSTANCE = new TimeLogUtil();
    }

    public static final TimeLogUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private TimeLogUtil() {
        timeMap = new ConcurrentHashMap<>();
    }

    //因为一个消息队列的任务会用多个方法，所以这里使用map的集合类
    private Map<String, Integer> timeMap;

    /**
     * 每5分钟打印一次日志
     * 主要是解决消息队列类型的任务调度程序频繁打印日志信息
     *
     * @param clazz  类名
     * @param method 方法名
     * @return
     */
    public synchronized boolean isNext5Minutes(Class<?> clazz, String method) {
        //KEY=类名+方法名,避免KEY出现重复
        String key = clazz.getName() + "|" + method;
        Integer oldVal = getValue(key);
        Calendar calendar = Calendar.getInstance();
        int minutes = calendar.get(Calendar.MINUTE);
        int newVal = minutes / 5;
        if (newVal != oldVal) {
            setValue(key, newVal);
            return true;
        }
        return false;
    }

    private Integer getValue(String key) {
        return timeMap.getOrDefault(key, 0);
    }

    private void setValue(String key, Integer val) {
        timeMap.put(key, val);
    }
}
