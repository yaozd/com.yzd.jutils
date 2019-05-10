package com.yzd.jutils.enumExt.ext2;

import java.util.Set;

public class EnumUtil {
    /**
     * 检查CODE，从而避免不合法数据进来
     *
     * @param codes
     * @param val
     * @return
     */
    public static Integer checkCode(String field,Set<Integer> codes, Integer val) {
        if (codes.contains(val)) {
            return val;
        }
        //throw new CustomException("：当前值"+val+"没有找到对应的枚举。");
        throw new IllegalStateException(field+"：当前值[" + val + "],没有找到对应的枚举。");
    }
}
