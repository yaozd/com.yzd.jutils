package com.yzd.jutils.guava.cacheExt4Idempotence;

import org.apache.commons.lang3.ThreadUtils;
import org.junit.Test;

import java.util.UUID;

public class _MainTest {
    /**
     * 一个简单的幂等工具类实现
     * https://segmentfault.com/a/1190000015497226
     */
    @Test
    public void t1() throws InterruptedException {
        String uuid=UUID.randomUUID().toString();
        //开启-主动回收
        LocalCacheUtil.registerScheduleClearTask();
        //放入缓存数据
        LocalCacheUtil.putObject(uuid,1);
        Thread.sleep(10000);
        //读取缓存数据
        Object value= LocalCacheUtil.getObject(uuid);
        System.out.println(value);
        //删除缓存数据
        LocalCacheUtil.remove(uuid);
        //读取缓存数据
        value=LocalCacheUtil.getObject(uuid);
        System.out.println(value);

    }
}
