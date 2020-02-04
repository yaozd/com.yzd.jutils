package com.yzd.jutils.toolHeaven.support.proxy;

import com.yzd.jutils.toolHeaven.constant.enums.ProxyTypeEnum;
import com.yzd.jutils.toolHeaven.util.lang.ObjectUtil;

import java.lang.reflect.Proxy;

/**
 * 代理工厂
 *
 * @author binbin.hou
 * @since 0.1.3
 */
public class ProxyFactory {

    private ProxyFactory() {
    }

    /**
     * 获取代理类型
     *
     * @param object 对象
     * @return 代理枚举
     */
    public static ProxyTypeEnum getProxyType(final Object object) {
        if (ObjectUtil.isNull(object)) {
            return ProxyTypeEnum.NONE;
        }

        final Class clazz = object.getClass();

        // 如果targetClass本身是个接口或者targetClass是JDK Proxy生成的,则使用JDK动态代理。
        // 参考 spring 的 AOP 判断
        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return ProxyTypeEnum.DYNAMIC;
        }

        return ProxyTypeEnum.CGLIB;
    }

}
