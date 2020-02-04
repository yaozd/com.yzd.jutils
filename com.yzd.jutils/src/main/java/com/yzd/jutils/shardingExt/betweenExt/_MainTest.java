package com.yzd.jutils.shardingExt.betweenExt;

import com.google.common.collect.Range;
import org.junit.Test;

import java.util.List;

/**
 * (算法)判断两个区间是否重叠
 * http://www.cnblogs.com/AndyJee/p/4537251.html
 * 区间重叠判断算法
 * http://blog.csdn.net/romandion/article/details/8910458
 * Created by zd.yao on 2017/11/2.
 */
public class _MainTest {
    //目前只有between操作会触发doBetweenSharding方法
    //大于（>）与小于（<）操作是不会触发doBetweenSharding方法
    @Test
    public void between() {
        //shardingjdbc返回实际数据类型
        //shardingValue.getValueRange()
        Range<Integer> range1 = Range.closed(0, 9);
        System.out.println("Lower Bound: " + range1.lowerEndpoint());
        System.out.println("Upper Bound: " + range1.upperEndpoint());
        //
        StoreRegion first = new StoreRegion(0L, 10L);
        StoreRegion second = new StoreRegion(2L, 10L);
        boolean isInclude = isInRegion(first, second);
        System.out.println(isInclude);
        //
        StoreRegion t2 = new StoreRegion(201601L, 201701L);
        List<String> nameList = StoreRegionEnum.getCollection(t2);
        for (String name : nameList) {
            System.out.println(name);
        }
    }

    /***
     * 在分库分表的IN ,=操作就相当于StoreRegion t2=new StoreRegion(1L,1L);
     * order_id=1,等价于 order_id>=1 and order_id<=1;
     */
    @Test
    public void between_sameValue() {
        StoreRegion t2 = new StoreRegion(201807L, 201807L);
        List<String> nameList = StoreRegionEnum.getCollection(t2);
        for (String name : nameList) {
            System.out.println("" + name);
        }
    }

    //region 判断两个区间是否重叠
    private boolean isInRegion(StoreRegion first, StoreRegion second) {
        return isInRegion(first.begin, first.end, second.begin, second.end);
    }

    private boolean isInRegion(long firstBegin, long firstEnd, long secondBegin, long secondEnd) {
        long begin = Math.max(firstBegin, secondBegin);
        long end = Math.min(firstEnd, secondEnd);
        long len = end - begin;
        return len >= 0;
    }
    //endregion
}
