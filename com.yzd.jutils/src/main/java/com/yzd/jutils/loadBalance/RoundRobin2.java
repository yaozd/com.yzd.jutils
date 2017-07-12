package com.yzd.jutils.loadBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 轮询（Round Robin）法--改进版
 * Created by zd.yao on 2017/7/4.
 */
public class RoundRobin2 {

    static AtomicLong count=new AtomicLong(0);
    public static String getServer() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap =
                new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);
        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        ArrayList<String> keyList = new ArrayList<String>();
        keyList.addAll(keySet);
        String server = null;
        int num=(int)count.incrementAndGet();
        if(num>10000){
            num=0;
            count=new AtomicLong(0);
        }
        server = keyList.get(num%keyList.size());
        return server;
    }
}
