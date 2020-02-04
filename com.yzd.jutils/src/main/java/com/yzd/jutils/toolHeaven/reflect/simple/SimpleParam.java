package com.yzd.jutils.toolHeaven.reflect.simple;


import com.yzd.jutils.toolHeaven.reflect.api.IParam;

/**
 * 参数实现
 *
 * @author binbin.hou
 * @since 0.1.5
 */
public class SimpleParam implements IParam {

    private String name;

    private String fullName;

    private Class type;

    private int access;

    @Override
    public String name() {
        return name;
    }

    public SimpleParam name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String fullName() {
        return fullName;
    }

    public SimpleParam fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public Class type() {
        return type;
    }

    public SimpleParam type(Class type) {
        this.type = type;
        return this;
    }

    @Override
    public int access() {
        return access;
    }

    public SimpleParam access(int access) {
        this.access = access;
        return this;
    }
}
