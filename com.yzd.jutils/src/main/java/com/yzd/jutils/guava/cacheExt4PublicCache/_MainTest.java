package com.yzd.jutils.guava.cacheExt4PublicCache;

import org.junit.Test;

public class _MainTest {

    //测试方法，参考：com.yzd.jutils.guava.cacheExt4PublicCache
    //LocalCacheUtilTest
    @Test
    public void t1() {
        String key = "yzd";
        LocalCacheUtil.getInstance().increment(key);
    }
}
