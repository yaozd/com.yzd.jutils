package com.yzd.jutils.regex;

import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;

public class _MainTest {

    /**
     * 参考：
     */
    @Test
    public void t1() {
        String regEx = ">Method:([^\\<]+)<";
        String str = "<h4>Method:aes-256-cfb</h4>";
        Matcher mat = RegExUtil.getMatcher(regEx, str);
        //使用group时一定要使用mat.find()方法才可以读取到
        if (mat.find()) {
            System.out.println(mat.group(1));
        }
    }

    @Test
    public void t11() {
        String regEx = "([^{]+)\\{([^}]+)}[\\s]+([\\d\\\\.E]+)$";
        String str = "hyperspace_request_seconds_count{service=\"172.20.60.45:8888\",endpoint_type=\"target\",} 18.0";
        List<String> matcherList = RegExUtil.regMatchAll2list(regEx, str);
        Matcher mat = RegExUtil.getMatcher(regEx, str);
        //使用group时一定要使用mat.find()方法才可以读取到
        if (mat.find()) {
            System.out.println(mat.groupCount());
            System.out.println(mat.group(1));
            System.out.println(mat.group(0));
            System.out.println(mat.group(2));
            System.out.println(mat.group(3));
        }
    }

    @Test
    public void t2() {
        String regEx = "\\[success_time\\](\\d+)\\[/success_time\\]";
        String str = "惠邦宝付查询[success_time]20180328105211[/success_time]";
        Matcher mat = RegExUtil.getMatcher(regEx, str);
        //使用group时一定要使用mat.find()方法才可以读取到
        if (mat.find()) {
            System.out.println(mat.group(1));
        }
    }

}
