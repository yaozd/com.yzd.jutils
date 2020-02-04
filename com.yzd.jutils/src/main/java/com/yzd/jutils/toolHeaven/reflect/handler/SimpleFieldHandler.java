package com.yzd.jutils.toolHeaven.reflect.handler;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.reflect.simple.SimpleField;
import com.yzd.jutils.toolHeaven.support.handler.IHandler;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
@ThreadSafe
public class SimpleFieldHandler implements IHandler<Field, SimpleField> {

    @Override
    public SimpleField handle(Field field) {
        SimpleField simpleField = new SimpleField();
        simpleField.field(field);
        simpleField.name(field.getName());
        simpleField.fullName(field.getName());
        simpleField.type(field.getType());
        simpleField.annotations(Arrays.asList(field.getAnnotations()));
        simpleField.access(field.getModifiers());
        return simpleField;
    }

}
