package com.yzd.jutils.springBoot.schedule3.base;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * Created by zd.yao on 2017/7/7.
 */
public class AbstractJob {

    /**
     * 从redis 里面读取消息可放到一个单独的抽象类里AbstractJob中，可以使代码更加清楚
     * @param listKey 消息队列的key
     * @param blockTimeout 阻塞的超时时间
     * @return
     * @throws InterruptedException
     */
    public static String getString(String listKey,int blockTimeout) throws InterruptedException {
        //
        String cmd=null;
        //redis-网络抖动等特殊情况下的异常处理
        try{
            //阻塞指令-读取reids的消息队列
            cmd="redis.list.rlpod";
        }catch (Exception e){
            //log 记录日志
            e.printStackTrace();
            TimeUnit.SECONDS.sleep(10);
            return null;
        }
        //redis 阻塞超时的情况下处理
        if(StringUtils.isBlank(cmd)){
            return null;
        }
        return cmd;
    }
}
