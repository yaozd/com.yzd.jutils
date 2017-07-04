package com.yzd.jutils.stringExt;

import java.io.UnsupportedEncodingException;

/**
 *
 * Created by zd.yao on 2017/6/28.
 */
public class StringUtil {
    //region string to byte 不要使用Utf-8，要使用ISO-8859-1编码，不然后fst 序列化时会所错
    public static byte[] stringToByte(String str){
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static String byteToString(byte[] bt){
        try {
            return new String(bt,"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
