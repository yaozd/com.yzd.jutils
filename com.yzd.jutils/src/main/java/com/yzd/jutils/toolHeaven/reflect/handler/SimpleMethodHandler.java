package com.yzd.jutils.toolHeaven.reflect.handler;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.simple.SimpleMethod;
import com.yzd.jutils.toolHeaven.support.handler.IHandler;

import java.lang.reflect.Method;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SimpleMethodHandler implements IHandler<Method, SimpleMethod> {

    @Override
    public SimpleMethod handle(Method method) {
        return null;
    }
}
