package com.yzd.jutils.stringExt;

import java.io.UnsupportedEncodingException;

/**
 *
 * Created by zd.yao on 2017/6/28.
 */
public class StringUtil {
    public static byte[] stringToByte(String str){
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    public static String byteToString(byte[] bt){
        try {
            return new String(bt,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
