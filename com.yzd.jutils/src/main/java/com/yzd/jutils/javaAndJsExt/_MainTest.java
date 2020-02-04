package com.yzd.jutils.javaAndJsExt;



import com.yzd.jutils.rsaExt.Base64;

import java.io.UnsupportedEncodingException;

public class _MainTest {
    // 加密
    public static String encode(String str) {
        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.encode(b);
            //据RFC 822规定，每76个字符，还需要加上一个回车换行去掉换行符
            s = s.replaceAll("[\\s*\t\n\r]", "");
        }
        return s;
    }

    // 解密
    public static String decode(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            try {
                b = Base64.decode(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Base64-java版
     *
     * @param args
     */
    public static void main(String[] args) {
        //使用统一的UTF-8编码
        String s = "你好world";
        //System.out.println(s.length());
        String enStr = encode(s);
        String deStr = decode(enStr);
        System.out.println("原始数据：" + s + "\n加密数据：" + enStr + "\n解密数据：" + deStr);
        System.out.println("5L2g5aW9d29ybGQ=".equals(enStr));
    }
}
