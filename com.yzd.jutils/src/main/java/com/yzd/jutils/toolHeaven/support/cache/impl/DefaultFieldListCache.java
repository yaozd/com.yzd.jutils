package com.yzd.jutils.toolHeaven.support.cache.impl;

import com.yzd.jutils.toolHeaven.annotation.NotThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.api.IField;
import com.yzd.jutils.toolHeaven.reflect.util.Classes;

import java.util.List;

/**
 * 多个字段的缓存
 *
 * @author binbin.hou
 * @since 0.1.5
 */
@NotThreadSafe
public class DefaultFieldListCache extends AbstractCache<Class, List<IField>> {

    @Override
    protected List<IField> buildValue(Class key) {
        return Classes.getFields(key);
    }

}
