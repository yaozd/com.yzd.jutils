package com.yzd.jutils.toolHeaven.support.cache.impl;

import com.yzd.jutils.toolHeaven.annotation.NotThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.api.IField;
import com.yzd.jutils.toolHeaven.support.cache.ICache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单个字段的缓存
 *
 * @author binbin.hou
 * @since 0.1.5
 */
@NotThreadSafe
public class DefaultFieldCache implements ICache<Class, IField> {

    /**
     * 存放信息的 map
     */
    private static final Map<Class, IField> MAP = new ConcurrentHashMap<>();

    @Override
    public IField get(Class key) {
        return MAP.get(key);
    }

    @Override
    public void set(Class key, IField value) {
        MAP.put(key, value);
    }

}
