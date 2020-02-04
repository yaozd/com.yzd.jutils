package com.yzd.jutils.toolHeaven.reflect.handler;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.simple.SimpleReturn;
import com.yzd.jutils.toolHeaven.support.handler.IHandler;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SimpleReturnHandler implements IHandler<Class, SimpleReturn> {

    @Override
    public SimpleReturn handle(Class aClass) {
        return null;
    }

}
