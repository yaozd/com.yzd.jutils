package com.yzd.jutils.algorithmExt.Shamir;

import com.yzd.jutils.algorithmExt.longmapper.LongMapper;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Author: yaozh
 * @Description:
 */
public class _MainTest {
    @Test
    public void Test() {
        /**
         * 秘密共享技术
         * https://github.com/codahale/shamir
         * 使用场景：Shamir密钥分享算法简析
         * https://blog.csdn.net/shangsongwww/article/details/90266455
         * 秘密共享技术是密码学和信息安全的一个重要研究内容，ShamirShamir密钥分享算法最早是由ShamirShamir和BlacklyBlackly在1970年基于LagrangeLagrange插值和矢量方法提出的，其基本思想是分发者通过秘密多项式，将秘密ss分解为nn个秘密分发个持有者，其中任意不少于kk个秘密均能恢复密文，而任意少于kk个秘密均无法得到密文的任何信息。
         *
         * 实际应用
         * 比如在门限体系中，为了保证信息安全性，一个秘密通常不能由单个持有者保存。比如一些重要场所的通行，比如遗嘱的生效等，必须将秘密分由多人保管并且只有当多人同时在场时秘密才能得以恢复。在这些场合，就需要这样一套的密钥分享技术。
         */
        final Scheme scheme = new Scheme(new SecureRandom(), 5, 3);
        final byte[] secret = "hello there".getBytes(StandardCharsets.UTF_8);
        final Map<Integer, byte[]> parts = scheme.split(secret);
        final byte[] recovered = scheme.join(parts);
        System.out.println(new String(recovered, StandardCharsets.UTF_8));
    }
}
