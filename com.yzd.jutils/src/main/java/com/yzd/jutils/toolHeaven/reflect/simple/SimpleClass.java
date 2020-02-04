package com.yzd.jutils.toolHeaven.reflect.simple;

import com.yzd.jutils.toolHeaven.reflect.api.IClass;
import com.yzd.jutils.toolHeaven.reflect.api.IField;
import com.yzd.jutils.toolHeaven.reflect.api.IMethod;

import java.util.List;

/**
 * @author binbin.hou
 * @since 0.1.5
 */
public class SimpleClass implements IClass {

    @Override
    public List<IField> fields() {
        return null;
    }

    @Override
    public List<IMethod> methods() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String fullName() {
        return null;
    }

    @Override
    public Class type() {
        return null;
    }

    @Override
    public int access() {
        return 0;
    }
}
