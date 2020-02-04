/*
 * Copyright (c)  2019. houbinbin Inc.
 * async All rights reserved.
 */

package com.yzd.jutils.toolHeaven.constant.enums;

/**
 * <p> 代理类型枚举 </p>
 *
 * <pre> Created: 2019/3/5 10:25 PM  </pre>
 * <pre> Project: async  </pre>
 *
 * @author houbinbin
 * @since 0.1.3
 */
public enum ProxyTypeEnum {

    /**
     * 不执行任何代理
     */
    NONE,

    /**
     * jdk 动态代理
     */
    DYNAMIC,

    /**
     * cglib 动态代理
     */
    CGLIB;

}
