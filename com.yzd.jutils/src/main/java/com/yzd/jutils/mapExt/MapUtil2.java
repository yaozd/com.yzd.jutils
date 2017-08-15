package com.yzd.jutils.mapExt;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by zd.yao on 2017/8/15.
 */
public class MapUtil2 {
    /**
     * ASCII 码从小到大排序（字典序）
     * @param paramMap
     * @return
     */
    public static Map<String,Object> sortByASCII(Map<String,Object> paramMap){
        SortedMap<String,Object> sort=new TreeMap<String,Object>(paramMap);
        return sort;
    }
}
