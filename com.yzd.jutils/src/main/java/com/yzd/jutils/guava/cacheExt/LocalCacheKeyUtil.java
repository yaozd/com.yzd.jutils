package com.yzd.jutils.guava.cacheExt;

import java.util.concurrent.ExecutionException;

/**
 * Created by zd.yao on 2017/11/30.
 */
public class LocalCacheKeyUtil {
    private static final String SeparateChar="#";
    //10秒
    private static final long IntervalTimeMillis=10000;
    //访问统计
    //时间区间：10秒的统计数据
    public static String getKeyFullNameOfKeyCount(String key){
        String time=String.valueOf(getIntervalTime());
        String keyFullName=key+SeparateChar+time;
        return keyFullName;
    }
    //当前KEY前10秒的缓存数量
    //时间区间：前10秒的缓存数量
    public static String getKeyFullNameOfKeyNum(String key){
        Long num= getIntervalTime();
        String time=String.valueOf(num-1);
        String keyFullName=key+SeparateChar+time;
        return keyFullName;
    }
    //间隔时间
    private static long getIntervalTime() {
        return System.currentTimeMillis() / IntervalTimeMillis;
    }
    public static String getIntervalTimeStr() {
        return SeparateChar+String.valueOf(getIntervalTime());
    }
    public static Long getValueOfKeyAccessCount(String key){
        String keyFullNameOfKeyCount=LocalCacheKeyUtil.getKeyFullNameOfKeyCount(key);
        try {
            return LocalCacheKeyCount.getInstance().keyAccessCount.get(keyFullNameOfKeyCount).get();
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }
    public static Long getValueOfKeyNumber(String key){
        String keyFullNameOfKeyNum=LocalCacheKeyUtil.getKeyFullNameOfKeyNum(key);
        try {
            return LocalCacheKeyNum.getInstance().keyNumber.get(keyFullNameOfKeyNum);
        } catch (ExecutionException e) {
            throw new IllegalStateException(e);
        }
    }
    //负载均衡=使用访问数对缓存编号取余
    public static Long getLoadBalanceOfKeyNum(String key){
        Long sizeOfKeyCount=LocalCacheKeyUtil.getValueOfKeyAccessCount(key);
        Long sizeOfKeyNum=LocalCacheKeyUtil.getValueOfKeyNumber(key);
        //PrintUtil.outLn(sizeOfKeyCount + "=_MainTest=TEST=" + sizeOfKeyNum);
        if(sizeOfKeyNum==0){
            sizeOfKeyNum=1L;
        }
        Long num=sizeOfKeyCount%sizeOfKeyNum;
        return num;
    }

    private static boolean isShutdown=false;
    public static boolean isIsShutdown() {
        return isShutdown;
    }

    public static void setIsShutdown(boolean isShutdown) {
        LocalCacheKeyUtil.isShutdown = isShutdown;
    }

}
