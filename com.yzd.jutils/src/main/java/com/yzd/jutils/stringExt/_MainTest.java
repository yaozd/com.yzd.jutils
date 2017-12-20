package com.yzd.jutils.stringExt;

import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

/**
 * Created by zd.yao on 2017/12/19.
 */
public class _MainTest {
    @Test
    public void equalsIgnoreCase()
    {
        //返回结果：成功是字符串时对比相等需要忽略大小写（特别是在对接回调接口中）
        String val="Success";
        if("success".equalsIgnoreCase(val)){
            PrintUtil.outLn(true);
        }else {
            PrintUtil.outLn(false);
        }
    }
}
