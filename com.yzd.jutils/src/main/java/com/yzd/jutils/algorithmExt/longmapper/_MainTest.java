package com.yzd.jutils.algorithmExt.longmapper;

import org.junit.Test;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    @Test
    public void Test() {
        /**
         * https://github.com/codahale/longmapper
         * 使用场景：通过用户ID生成对应longmapper，做为盐值，然后再生成密码保存在数据库中
         */
        final LongMapper mapper = new LongMapper("ayellowsubmarine".getBytes(UTF_8));

        System.out.printf("Mapped %d to %d\n", 200, mapper.map(200));
        System.out.printf("Unmapped %d to %d\n", mapper.map(200), mapper.unmap(mapper.map(200)));
    }
}
