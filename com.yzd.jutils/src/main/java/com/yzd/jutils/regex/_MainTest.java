package com.yzd.jutils.regex;

import org.junit.Test;

import java.util.regex.Matcher;

public class _MainTest {

    /**
     * 参考：
     *
     */
    @Test
    public void t1(){
        String regEx = ">Method:([^\\<]+)<";
        String str = "<h4>Method:aes-256-cfb</h4>";
        Matcher mat = RegExUtil.getMatcher(regEx, str);
        //使用group时一定要使用mat.find()方法才可以读取到
        if(mat.find()){
            System.out.println(mat.group(1));
        }
    }
    @Test
    public void t2(){
        String regEx = "\\[success_time\\](\\d+)\\[/success_time\\]";
        String str = "惠邦宝付查询[success_time]20180328105211[/success_time]";
        Matcher mat = RegExUtil.getMatcher(regEx, str);
        //使用group时一定要使用mat.find()方法才可以读取到
        if(mat.find()){
            System.out.println(mat.group(1));
        }
    }

}
