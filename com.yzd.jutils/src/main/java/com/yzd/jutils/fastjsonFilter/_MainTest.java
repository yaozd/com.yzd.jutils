package com.yzd.jutils.fastjsonFilter;

import com.yzd.jutils.fastjsonFilter.model.CardInfo;
import org.junit.Test;

import java.util.Date;

public class _MainTest {
    //通过fastJson对日志中的敏感信息(如：密码、身份证、电话、银行卡等)，进行日志字段脱敏处理
    //自定义Json注解实现输出日志字段脱敏
    //https://blog.csdn.net/worstezreal/article/details/75453812
    @Test
    public void t1() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setUserId("11111111111111111");
        cardInfo.setCardId("6228480402564890018");
        cardInfo.setCertId("123456711118971118");
        cardInfo.setPassword(null);
        cardInfo.setPhone("15210445510");
        cardInfo.setCreateDate(new Date());
        //
        System.out.println(FastJsonLogUtil.toJsonString(cardInfo));
    }

    //测试速度
    @Test
    public void t2() {
        for (int i = 0; i < 1000000; i++) {
            fastJsonLog();
        }
        fastJsonLog();
    }

    private void fastJsonLog() {
        CardInfo cardInfo = new CardInfo();
        cardInfo.setUserId("11111111111111111");
        cardInfo.setCardId("6228480402564890018");
        cardInfo.setCertId("12345678978");
        cardInfo.setPassword("123456");
        cardInfo.setPhone("15210445510");
        //
        //System.out.println(FastJsonLogUtil.toJsonString(cardInfo));
    }
}
