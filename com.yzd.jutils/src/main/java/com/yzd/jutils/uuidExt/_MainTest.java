package com.yzd.jutils.uuidExt;

import org.junit.Test;

/**
 * 通过UUID+ThreadLocal 加速唯一ID生成的速度
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    @Test
    public void Test() {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(UuIdGenerator.generate());
        }
    }
}
