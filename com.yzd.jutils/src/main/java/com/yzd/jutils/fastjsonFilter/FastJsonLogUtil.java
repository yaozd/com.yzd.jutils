package com.yzd.jutils.fastjsonFilter;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

/**
 * 日志敏感信息脱敏工具
 * 通过fastJson对日志中的敏感信息(如：密码、身份证、电话、银行卡等)，进行日志字段脱敏处理
 *
 * @author worstEzreal
 * @version V1.0.0
 * @date 2017/7/19
 */
public class FastJsonLogUtil {

    public static String toJsonString(Object object) {
        return JSON.toJSONString(object, getValueFilter());
    }

    /***
     * 电话号码或身份证的显示规则
     * @param cardNum
     * @return
     */
    private static String desensitizePhoneOrIdCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        int cardNumLength = cardNum.length();
        StringBuilder sb = new StringBuilder();
        //规则一：前3位+********（8个星）+后4位
        if (cardNumLength > 15) {
            sb.append(StringUtils.left(cardNum, 3));
            sb.append("********");
            sb.append(StringUtils.right(cardNum, 4));
            return sb.toString();
        }
        //规则二：前3位+****（4个星）+后4位
        if (cardNumLength > 10) {
            sb.append(StringUtils.left(cardNum, 3));
            sb.append("****");
            sb.append(StringUtils.right(cardNum, 4));
            return sb.toString();
        }
        //规则三：前4位+****（4个星）
        sb.append(StringUtils.left(cardNum, 3)).append("****");
        return sb.toString();
    }

    /***
     * 银行卡的显示规则
     * @param cardNum
     * @return
     */
    private static String desensitizeBankCard(String cardNum) {
        if (StringUtils.isBlank(cardNum)) {
            return "";
        }
        int cardNumLength = cardNum.length();
        StringBuilder sb = new StringBuilder();
        //规则一：前4位+********（8个星）+后4位
        if (cardNumLength > 10) {
            sb.append(StringUtils.left(cardNum, 4));
            sb.append("********");
            sb.append(StringUtils.right(cardNum, 4));
            return sb.toString();
        }
        //规则二：前4位+********（6个星）
        sb.append(StringUtils.left(cardNum, 4)).append("******");
        return sb.toString();
    }

    /***
     * 密码的显示规则
     * @param password
     * @return
     */
    private static String desensitizePassword(String password) {
        if (StringUtils.isBlank(password)) {
            return "";
        }
        //规则一：******（6个星）
        return "******";
    }

    private static final ValueFilter getValueFilter() {
        return new ValueFilter() {
            @Override
            public Object process(Object obj, String key, Object value) {//obj-对象  key-字段名  value-字段值
                try {
                    Field field = obj.getClass().getDeclaredField(key);
                    SensitiveLogInfo annotation = field.getAnnotation(SensitiveLogInfo.class);
                    if (null != annotation && value instanceof String) {
                        String strVal = (String) value;
                        if (StringUtils.isNotBlank(strVal)) {
                            switch (annotation.type()) {
                                case PHONE:
                                    return desensitizePhoneOrIdCard(strVal);
                                case ID_CARD:
                                    return desensitizePhoneOrIdCard(strVal);
                                case PASSWORD:
                                    return desensitizePassword(strVal);
                                case BANK_CARD:
                                    return desensitizeBankCard(strVal);
                                default:
                                    break;
                            }
                        }
                    }
                } catch (NoSuchFieldException e) {
                    //找不到的field对功能没有影响,空处理
                }
                return value;
            }
        };
    }
}
