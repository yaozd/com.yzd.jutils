package com.yzd.jutils.encrypt;

/**
 * Created by zd.yao on 2017/3/17.
 */
public class EncryptAESUtil {
    private static String AES_KEY = "BAA31820BAA31820";

    public static String toEncrypt(String source) {
        EncryptUtil EncryptUtil = new EncryptUtil();
        try {
            return EncryptUtil.encryptToAES(AES_KEY, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toDecrypt(String source) {
        EncryptUtil EncryptUtil = new EncryptUtil();
        try {
            return EncryptUtil.decryptByAES(AES_KEY, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String source = "www.com";
        String t1 = EncryptAESUtil.toEncrypt(source);
        String t2 = EncryptAESUtil.toDecrypt(t1);
        System.out.println(t1);
        System.out.println(t2);
    }
}
