package com.yzd.jutils.toolHeaven.reflect.api;

/**
 * 所有反射信息的基础类
 *
 * @author binbin.hou
 * @since 0.1.5
 */
public interface IMember {

    /**
     * 简称
     *
     * @return 简称
     */
    String name();

    /**
     * 全称
     *
     * @return 全称
     */
    String fullName();

    /**
     * 类型
     *
     * @return 类型
     */
    Class type();

    /**
     * 访问权限
     *
     * @return 访问权限
     */
    int access();

}
