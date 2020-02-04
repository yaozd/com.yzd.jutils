/*
 * Copyright (c)  2019. houbinbin Inc.
 * heaven All rights reserved.
 */

package com.yzd.jutils.toolHeaven.util.lang.reflect;

/**
 * 反射数组具类
 * <p>
 * https://houbb.github.io/2018/07/01/reflection-07-array
 *
 * @author bbhou
 * @see java.lang.reflect.Array 反射数组类
 * @since 0.0.2
 */
public final class ReflectArrayUtil {

    private ReflectArrayUtil() {
    }

    /**
     * 获取数组的元素类型
     *
     * @param objects 数组
     * @return 元素类型
     */
    public static Class getComponentType(final Object[] objects) {
        Class arrayClass = objects.getClass();
        return arrayClass.getComponentType();
    }

}
