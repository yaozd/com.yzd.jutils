package com.yzd.jutils.toolHeaven.reflect.handler;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.simple.SimpleClass;
import com.yzd.jutils.toolHeaven.support.handler.IHandler;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SimpleClassHandler implements IHandler<Class, SimpleClass> {

    @Override
    public SimpleClass handle(Class aClass) {
        return null;
    }

}
