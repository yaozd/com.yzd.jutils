package com.yzd.jutils.shardingExt.betweenExt;

import java.util.ArrayList;
import java.util.List;

/**
 * 目前的数据库的配置信息
 * 假定每个存储块的大小为：1年半的容量
 * Created by zd.yao on 2017/11/2.
 */
public enum StoreRegionEnum {
    db0(201600L,201807L,"0"),
    db1(201807L,202000L,"1"),
    db2(202000L,202107L,"2");
    StoreRegionEnum (Long begin,Long end,String name){
        this.region=new StoreRegion(begin,end);
        this.name=name;
    }
    StoreRegion region;
    String name;
    public static List<String> getCollection(StoreRegion first){
        List<String> list=new ArrayList<>();
        for (StoreRegionEnum e : StoreRegionEnum.values()){
            boolean isInclude= isInRegion(first,e.region);
            if(isInclude){
                list.add(e.name);
            }
        }
        return list;
    }
    //region 判断两个区间是否重叠
    private static boolean isInRegion(StoreRegion first,StoreRegion second){
        return isInRegion(first.begin,first.end,second.begin,second.end);
    }
    private static boolean isInRegion(long firstBegin,long firstEnd,long secondBegin,long secondEnd){
        long begin =Math.max(firstBegin,secondBegin);
        long end =Math.min(firstEnd, secondEnd);
        long len = end - begin;
        return len>=0;
    }
    //endregion
}

