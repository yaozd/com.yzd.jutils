package com.yzd.jutils.shardingExt.betweenExt;

/**
 * 目前假定每个存储块的大小为：1年半的容量
 * Created by zd.yao on 2017/11/2.
 */
public class StoreRegion {
    StoreRegion (Long begin,Long end){
        if(begin>end){
            throw new IllegalStateException("begin>end");
        }
        this.begin=begin;
        this.end=end;
    }
    Long begin;
    Long end;
}
