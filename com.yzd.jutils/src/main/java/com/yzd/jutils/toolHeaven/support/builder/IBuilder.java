package com.yzd.jutils.toolHeaven.support.builder;

/**
 * 构建者模式接口
 *
 * @author bbhou
 * @version 0.0.7
 * @see com.yzd.jutils.toolHeaven.support.handler.IHandler 推荐使用这个。
 */
public interface IBuilder<T> {

    /**
     * 构建
     *
     * @return 返回的对象
     */
    T build();

}
