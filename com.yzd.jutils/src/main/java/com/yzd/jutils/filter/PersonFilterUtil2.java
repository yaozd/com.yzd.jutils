package com.yzd.jutils.filter;

import com.google.common.collect.Maps;
import com.yzd.jutils.print.PrintUtil;

import java.util.Map;

/**
 * todo 写法比较简单但是静态变量占用内存
 * Created by zd.yao on 2017/4/28.
 */
public class PersonFilterUtil2 {
    private static Map<Integer, PersonFilter> filterMap;

    public static PersonFilter getFilter(Integer index) {
        return filterMap.get(index);
    }
    static {
        PrintUtil.outLn("PersonFilterUtil2 init");
        filterMap = Maps.newHashMap();
        filterMap.put(1, PersonFilter.ageFilter);
        filterMap.put(2, PersonFilter.nameFilter);
    }
}
