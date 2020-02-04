package com.yzd.jutils.toolHeaven.support.wait.impl;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.support.wait.IWait;

import java.util.concurrent.TimeUnit;

/**
 * 不进行任何等待
 *
 * @author binbin.hou
 * @since 0.1.8
 */
@ThreadSafe
public class NoWait implements IWait {

    @Override
    public void waits(long time, TimeUnit timeUnit) {

    }

}
