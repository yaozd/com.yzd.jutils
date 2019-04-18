package com.yzd.jutils.guava.cacheExt4PublicCache;

import java.util.Random;

public class ToolUtil2 {
    /**
     * 随机数
     * 通过随机数模拟不同的KEY-NAME
     * @return
     */
    public static  String getNonce() {
        return Integer.toHexString(new Random().nextInt());
    }
}
