package com.yzd.jutils.toolHeaven.reflect.handler;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.simple.SimpleParam;
import com.yzd.jutils.toolHeaven.support.handler.IHandler;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SimpleParamHandler implements IHandler<Class, SimpleParam> {

    @Override
    public SimpleParam handle(Class aClass) {
        return null;
    }

}
