package com.yzd.jutils.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by zd.yao on 2017/8/1.
 */
public class AESUtil {
    public static String aesEncrypt(String content, String encryptKey,String encodeName){
        try {
            return isEmpty(content) ? null : base64Encode(aesEncryptToBytes(content, encryptKey,encodeName));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    private static byte[] aesEncryptToBytes(String content, String encryptKey,String encodeName) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes(encodeName));
    }
    /**
     * AES解密
     * @param encryptBytes 待解密的byte[]
     * @param decryptKey 解密密钥
     * @return 解密后的String
     * @throws Exception
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey,String encodeName) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes,encodeName);
    }

    /**
     * 将base 64 code AES解密
     * @param encryptStr 待解密的base 64 code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey,String encodeName)  {
        try {
            return isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey,encodeName);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    /**
     * base 64 decode
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     * @throws Exception
     */
    private static byte[] base64Decode(String base64Code) throws Exception{
        return isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
    }
    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     *  Java 加密 AES 对称加密算法
     *  http://blog.csdn.net/uikoo9/article/details/27982575
     *  Java、C#双语版配套AES加解密示例
     *  http://www.cnblogs.com/lzrabbit/p/3639503.html
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)  {
        //AES 加密默认情况下都是使用UTF-8做为内容的编码的格式
        //不过AES有key有多种情况当前是通过SecureRandom生成
        //如果KEY是严格的不16位不需要SecureRandom生成的话，可以参考【Java、C#双语版配套AES加解密示例】http://www.cnblogs.com/lzrabbit/p/3639503.html
        String encodeName="utf-8";
        String content = "hello";
        System.out.println("加密前：" + content);

        String key = "123456";
        System.out.println("加密密钥和解密密钥：" + key);

        String encrypt = aesEncrypt(content, key,encodeName);
        System.out.println("加密后：" + encrypt);
        String decrypt = aesDecrypt(encrypt, key,encodeName);
        System.out.println("解密后：" + decrypt);
    }
}
