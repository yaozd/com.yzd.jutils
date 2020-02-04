package com.yzd.jutils.toolHeaven.support.wait.impl;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.response.exception.CommonRuntimeException;
import com.yzd.jutils.toolHeaven.support.wait.IWait;

import java.util.concurrent.TimeUnit;

/**
 * 沉睡等待
 *
 * @author binbin.hou
 * @since 0.1.8
 */
@ThreadSafe
public class SleepWait implements IWait {

    @Override
    public void waits(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CommonRuntimeException(e);
        }
    }

}
