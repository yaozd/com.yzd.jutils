package com.yzd.jutils.stringExt;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public static void StringToList(){
        String[] s={"1","2","3","5","6"};
        List<String> listA = Arrays.asList(s);
        List<String> listB = new ArrayList<String>();
        listB.addAll(listA);
        listB.add("7");
        String[]s2=listB.toArray(new String[]{});

    }
}
