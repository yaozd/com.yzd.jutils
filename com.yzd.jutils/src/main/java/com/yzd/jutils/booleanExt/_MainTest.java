package com.yzd.jutils.booleanExt;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _MainTest {
    @Test
    public void t1()
    {
        if(BooleanUtils.isTrue(true)){}
        if(BooleanUtils.isNotTrue(true)){}
        //不建议使用：
        if(!true){}
    }
}
