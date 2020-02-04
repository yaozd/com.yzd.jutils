package com.yzd.jutils.toolHeaven.util.id.impl;

import com.yzd.jutils.toolHeaven.annotation.ThreadSafe;
import com.yzd.jutils.toolHeaven.util.id.Id;
import com.yzd.jutils.toolHeaven.util.util.DateUtil;
import com.yzd.jutils.toolHeaven.util.util.RandomUtil;

import java.util.Date;

/**
 * 基于随机数生成的随机数字标识
 * <p>
 * 组成方式：前缀+15位时间戳+后续随机数字
 * <p>
 * 重复概率：1 Mills 内重复的概率为 1/(10)^10
 * <p>
 * 应用场景：平时生成随机的标识。
 * <p>
 * 优点：便于阅读，生成方便。
 * 缺点：重复的概率和后续随机的长度有关，有时候可能会过长。
 *
 * @author binbin.hou
 * @see DateUtil#TIMESTAMP_FORMAT_15 15 位时间戳
 * @since 0.1.12
 */
@ThreadSafe
public class RandomNumId implements Id {

    @Override
    public String genId() {
        final int randomLength = 10;
        final String timestamp = DateUtil.getDateFormat(new Date(), DateUtil.TIMESTAMP_FORMAT_15);
        return timestamp + RandomUtil.randomNumber(randomLength);
    }

}
