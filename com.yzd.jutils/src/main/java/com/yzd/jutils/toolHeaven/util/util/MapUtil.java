/*
 * Copyright (c)  2019. houbinbin Inc.
 * heaven All rights reserved.
 */

package com.yzd.jutils.toolHeaven.util.util;

import com.yzd.jutils.toolHeaven.support.handler.IHandler;
import com.yzd.jutils.toolHeaven.util.lang.ObjectUtil;
import com.yzd.jutils.toolHeaven.util.lang.StringUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 工具类
 *
 * @author bbhou
 * @since 0.0.1
 */
public final class MapUtil {

    private MapUtil() {
    }

    /**
     * 判断map为空
     *
     * @param map map
     * @return {@code true} 为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || 0 == map.size();
    }

    /**
     * 判断map为非空
     *
     * @param map map
     * @return {@code true} 非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 可遍历的结合转换为 map
     *
     * @param values      可遍历的元素 Iterator
     * @param keyFunction 转化方式
     * @param <K>         key 泛型
     * @param <V>         value 泛型
     * @return map 结果
     * @since 0.1.7
     */
    public static <K, V> Map<K, V> toMap(Collection<V> values, IHandler<? super V, K> keyFunction) {
        if (ObjectUtil.isNull(values)) {
            return Collections.emptyMap();
        }

        Map<K, V> map = new HashMap<>(values.size());

        for (V value : values) {
            final K key = keyFunction.handle(value);
            map.put(key, value);
        }
        return map;
    }

    /**
     * key 是元素的索引
     *
     * @param values 值
     * @param <V>    元素泛型
     * @return 结果 map
     */
    public static <V> Map<Integer, V> toIndexMap(Collection<V> values) {
        if (ObjectUtil.isNull(values)) {
            return Collections.emptyMap();
        }

        Map<Integer, V> map = new HashMap<>(values.size());

        for (V v : values) {
            map.put(map.size(), v);
        }
        return map;
    }

    /**
     * 获取对应的映射信息
     * （1）如果对应的值不存在，则返回 key 本身
     * （2）如果 map 为空，则返回
     *
     * @param map map
     * @param key key
     * @return value
     * @since 0.1.17
     */
    public static String getMapValue(final Map<String, String> map,
                                     final String key) {
        if (MapUtil.isEmpty(map)) {
            return key;
        }
        final String value = map.get(key);
        if (StringUtil.isEmpty(value)) {
            return key;
        }
        return value;
    }

}