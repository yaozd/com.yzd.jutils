/*
 * Copyright (c)  2019. houbinbin Inc.
 * heaven All rights reserved.
 */

package com.yzd.jutils.toolHeaven.util.lang.reflect;

import com.yzd.jutils.toolHeaven.util.guava.Guavas;
import com.yzd.jutils.toolHeaven.util.lang.ObjectUtil;
import com.yzd.jutils.toolHeaven.util.util.ArrayUtil;
import com.yzd.jutils.toolHeaven.util.util.CollectionUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class 泛型工具类
 *
 * @author binbin.hou
 * date 2019/2/24
 * @since 0.0.3
 */
public final class ClassGenericUtil {

    private ClassGenericUtil() {
    }

    /**
     * 获取当前类的泛型接口信息
     *
     * @param clazz 类
     * @return 泛型接口信息
     */
    private static List<Type> getGenericInterfaces(final Class clazz) {
        Set<Type> typeSet = new HashSet<>();

        // 添加当前类的泛型接口信息
        Type[] types = clazz.getGenericInterfaces();
        if (ArrayUtil.isNotEmpty(types)) {
            typeSet.addAll(Guavas.newArrayList(types));
        }

        // 当前类的泛型父类信息
        Type superType = clazz.getGenericSuperclass();
        if (ObjectUtil.isNotNull(superType)
                && superType.getClass().isInterface()) {
            typeSet.add(superType);
        }

        return Guavas.newArrayList(typeSet);
    }


    /**
     * 获取泛型类型
     *
     * @param clazz          数据类型
     * @param interfaceClass 接口对应的 class 信息
     * @param index          泛型的下标志位置
     * @return 对应的泛型类型
     */
    public static Class getGenericType(final Class clazz,
                                       final Class interfaceClass,
                                       final int index) {
        List<Type> typeList = ClassGenericUtil.getGenericInterfaces(clazz);
        for (Type type : typeList) {
            if (type instanceof ParameterizedType
                    && interfaceClass.equals(((ParameterizedType) type).getRawType())
            ) {
                ParameterizedType p = (ParameterizedType) type;
                return (Class) p.getActualTypeArguments()[index];
            }
        }

        return Object.class;
    }

    /**
     * 获取元素的泛型
     *
     * @param list 列表
     * @return 泛型
     * @since 0.1.26
     */
    public static Class getGenericClass(final Collection<?> list) {
        if (CollectionUtil.isEmpty(list)) {
            return null;
        }

        for (Object object : list) {
            if (ObjectUtil.isNotNull(object)) {
                return object.getClass();
            }
        }
        return null;
    }


}