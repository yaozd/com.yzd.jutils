package com.yzd.jutils.stringExt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringUtilForSplit {

    @Test
    public void t1() {
        String value = "/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh";
        //5K=5*1024
        List<String> arr = splitBySize(value, 5 * 1024);
    }

    /***
     *java 字符串按大小（占字节数）切分
     * https://blog.csdn.net/zeratyl/article/details/79103519
     * @param value
     * @param length
     * @return
     */
    public static List<String> splitBySize(String value, int length) {
        char[] cs = value.toCharArray();
        StringBuilder result = new StringBuilder();
        List<String> resultList = new ArrayList<String>();
        int index = 0;
        for (char c : cs) {
            index += String.valueOf(c).getBytes().length;
            if (index > length) {
                resultList.add(result.toString());
                result.delete(0, index - 1);
                index = 0;
            } else {
                result.append(c);
            }
        }
        return resultList;
    }

}
