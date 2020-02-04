package com.yzd.jutils.toolHeaven.util.id.impl;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.constant.PunctuationConst;
import com.yzd.jutils.toolHeaven.util.id.Id;

import java.util.UUID;

/**
 * UUID 返回
 * 默认和 uuid 保持一致，使用 32 位的 uuid
 *
 * @author binbin.hou
 * @since 0.1.12
 */
@ThreadSafe
public class UUID32 implements Id {

    /**
     * 實例信息
     *
     * @since 0.1.37
     */
    private static final Id INSTANCE = new UUID32();

    public static Id getInstance() {
        return INSTANCE;
    }

    @Override
    public String genId() {
        return UUID.randomUUID().toString()
                .replaceAll(PunctuationConst.MIDDLE_LINE, PunctuationConst.EMPTY);
    }

}
