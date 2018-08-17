package com.yzd.jutils.stringExt;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
    private static final Pattern INT_PATTERN = Pattern.compile("^\\d+$");

    public static boolean isBlank(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isEquals(String s1, String s2) {
        if (s1 == null && s2 == null) {
            return true;
        } else {
            return s1 != null && s2 != null ? s1.equals(s2) : false;
        }
    }

    public static boolean isInteger(String str) {
        return str != null && str.length() != 0 ? INT_PATTERN.matcher(str).matches() : false;
    }

    public static int parseInteger(String str) {
        return !isInteger(str) ? 0 : Integer.parseInt(str);
    }
}
