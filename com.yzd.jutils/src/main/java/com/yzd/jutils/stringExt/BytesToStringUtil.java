package com.yzd.jutils.stringExt;

/**
 * Java中16进制与字符串之间的相互转换
 * https://www.cnblogs.com/kliine/p/9987512.html
 *
 * @Author: yaozh
 * @Description:
 */
public class BytesToStringUtil {

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
// 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 字符串转化成为16进制字符串
     *
     * @param s
     * @return
     */
    public static String strTo16(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 16进制转换成为string类型字符串
     *
     * @param s
     * @return
     */
    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    /**
     * 16进制直接转换成为字符串
     *
     * @param args
     */
    public static void main(String[] args) {
        //io.netty.handler.codec.http2.Http2Exception: HTTP/2 client preface string missing or corrupt.
        //Hex dump for received bytes: 7b226964223a312c226a736f6e727063223a22322e30222c
        String encodingStr = "7b226964223a312c226a736f6e727063223a22322e30222c";
        //推荐此方法：
        String decodingStr = hexStringToString(encodingStr);
        System.out.println(decodingStr);
        String newStr = strTo16(decodingStr);
        System.out.println(newStr);

    }
}
