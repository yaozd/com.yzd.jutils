package com.yzd.jutils.nullExt;

import java.util.List;

/**
 * 如何为空则为NULL
 * 使用场景：表单查询与mybatis查询中IN
 */
public class OptionalUtil2 {
    public static String emptyToNull(String string) {
        return string == null || string.isEmpty() ? null : string;
    }
    public static List emptyToNull(List list) {
        return list == null || list.isEmpty() ? null : list;
    }
}
