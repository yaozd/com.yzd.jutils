package com.yzd.jutils.encrypt;

import org.junit.Test;

import java.math.BigInteger;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

public class CoderUtilTest {
    //参考：
    //HMAC_SHA1和SHA1的区别
    //http://blog.csdn.net/yinkai1205/article/details/8313589
    //HMAC_SHA1与其他MD5的区别是增加了一个KEY（秘钥）
    @Test
    public void test() throws Exception {
        String inputStr = "简单加密";
        System.err.println("原文:/n" + inputStr);

        byte[] inputData = inputStr.getBytes();
        String code = CoderUtil.encryptBASE64(inputData);

        System.err.println("BASE64加密后:/n" + code);

        byte[] output = CoderUtil.decryptBASE64(code);

        String outputStr = new String(output);

        System.err.println("BASE64解密后:/n" + outputStr);

        // 验证BASE64加密解密一致性
        assertThat(inputData, equalTo(inputData));
        // 验证MD5对于同一内容加密是否一致
        assertArrayEquals(CoderUtil.encryptMD5(inputData), CoderUtil.encryptMD5(inputData));

        // 验证SHA对于同一内容加密是否一致
        assertArrayEquals(CoderUtil.encryptSHA(inputData), CoderUtil.encryptSHA(inputData));

        String key = CoderUtil.initMacKey();
        System.err.println("Mac密钥:/n" + key);

        // 验证HMAC对于同一内容，同一密钥加密是否一致
        assertArrayEquals(CoderUtil.encryptHMAC(inputData, key), CoderUtil.encryptHMAC(
                inputData, key));

        BigInteger md5 = new BigInteger(CoderUtil.encryptMD5(inputData));
        System.err.println("MD5:/n" + md5.toString(16));

        BigInteger sha = new BigInteger(CoderUtil.encryptSHA(inputData));
        System.err.println("SHA:/n" + sha.toString(32));

        BigInteger mac = new BigInteger(CoderUtil.encryptHMAC(inputData, inputStr));
        System.err.println("HMAC:/n" + mac.toString(16));
    }
}
