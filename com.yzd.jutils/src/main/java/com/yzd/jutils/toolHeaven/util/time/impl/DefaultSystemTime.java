package com.yzd.jutils.toolHeaven.util.time.impl;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.util.time.Time;

/**
 * 默认系统时间
 *
 * @author binbin.hou
 * @since 0.1.37
 */
@ThreadSafe
class DefaultSystemTime implements Time {

    private static final DefaultSystemTime INSTANCE = new DefaultSystemTime();

    public static Time getInstance() {
        return INSTANCE;
    }

    @Override
    public long time() {
        return System.currentTimeMillis();
    }

}
