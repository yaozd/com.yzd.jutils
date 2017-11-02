package com.yzd.jutils.shardingExt.inExt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zd.yao on 2017/11/2.
 */
public class _MainTest {
    @Test
    public void in_test(){
        String name="1";
        //获得除重后的name集合
        List<String> nameList=new ArrayList<>();
        boolean isExist=nameList.contains(name);
        if(isExist)return;
        nameList.add(name);
    }

}
