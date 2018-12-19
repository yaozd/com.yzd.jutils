package com.yzd.jutils.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keith.w on 2017/1/22.
 */
public class RegExUtil2 {
    /**
     * 判断是否匹配
     *
     * @param INPUT
     * @param REGEX
     * @return true/false
     */
    public static boolean matches(String INPUT, String REGEX) {
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(INPUT);
        return matcher.matches();
    }

    /**
     * 使用正则表达式REGEX从文本INPUT（比如html文档）中获取匹配的字符串
     *
     * @param INPUT
     * @param REGEX
     * @return 匹配到的所有字符串
     */
    public static List<String> getContentByPattern(String INPUT, String REGEX) {
        List<String> resultList = new ArrayList<String>();
        Pattern p = Pattern.compile(REGEX); //根据正则表达式构造一个Pattern对象

        if (INPUT == null) {
            System.out.println("INPUT不能为NULL！");
            return resultList;
        }

        Matcher m = p.matcher(INPUT);        //利用patter对象为被匹配的文本构造一个Matcher对象
        while (m.find()) { //如果在任何位置中发现匹配的字符串……
            resultList.add(m.group()); //保存匹配到的字符串
        }
        return resultList;
    }

    /**
     * 使用正则表达式REGEX从文本INPUT中获取第一个匹配的字符串
     *
     * @param INPUT
     * @param REGEX
     * @return
     */
    public static String getFirstMatch(String INPUT, String REGEX) {
        List<String> ss = getContentByPattern(INPUT, REGEX);
        if (ss.size() > 0) {
            return ss.get(0);
        } else {
            return null;
        }

    }

    /**
     * 根据正则表达式REGEX，把INPUT中所有被匹配到的字符串替换成REPLACE
     *
     * @param INPUT
     * @param REGEX
     * @param REPLACE
     */
    public static String replaceContentByPattern(String INPUT, String REGEX, String REPLACE) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);
        return m.replaceAll(REPLACE);
    }

    /**
     * 从INPUT中找到第一串数字
     *
     * @param INPUT
     * @return
     */
    public static String findFirstNumber(String INPUT) {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(INPUT);
        if (m.find()) {
            return m.group();
        } else {
            return null;
        }
    }
}

