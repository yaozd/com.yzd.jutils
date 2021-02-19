package com.yzd.jutils;

import org.junit.Test;

/**
 * @Author: yaozh
 * @Description: 临时测试使用
 */
public class TempTest {
    /**
     * output:123
     */
    @Test
    public void testSwitch() {
        int a = 1;
        switch (a) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            case 3:
                System.out.println(3);
        }
    }

    @Test
    public void byteTest() {
        byte OPCODE_ALL = 0xF;
        System.out.println(OPCODE_ALL);
        System.out.println(OPCODE_ALL==15);
    }
}
