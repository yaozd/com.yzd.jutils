package com.yzd.jutils.stringExt;

import com.yzd.jutils.print.PrintUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by zd.yao on 2017/12/19.
 */
public class _MainTest {
    @Test
    public void equalsIgnoreCase() {
        //返回结果：成功是字符串时对比相等需要忽略大小写（特别是在对接回调接口中）
        String val = "Success";
        if ("success".equalsIgnoreCase(val)) {
            PrintUtil.outLn(true);
        } else {
            PrintUtil.outLn(false);
        }
    }

    @Test
    public void grpcPathTest() {
        String grpcPath = "/a/b";
         grpcPath = null;
        String value = String.valueOf(grpcPath);
        String s = StringUtils.substringBeforeLast(grpcPath, "/");
        if(StringUtil.isBlank(s)){
            System.out.println(null+"");
        }
        System.out.println(s);
        ConcurrentHashMap<String,String> hashMap= new ConcurrentHashMap<String, String>();
        //java.lang.NullPointerException
        hashMap.get(null);
    }
}
