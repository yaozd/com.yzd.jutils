package com.yzd.jutils.objectExt;

import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class _MainTest {

    @Test
    public void t0(){
        Integer val=0;
        //必须不能为NULL
        Objects.requireNonNull(val);
        //是否为NULL
        Objects.isNull(val);
        //是否不为NULL
        Objects.nonNull(val);
    }
    @Test
    public void t1()
    {
        List<String> stringList=null;
        stringList= ObjectUtils.defaultIfNull(stringList, new ArrayList<String>());
        System.out.println(stringList.isEmpty());
        t2(null);
    }
    private void t2(@NotNull String notNull){
        System.out.println(notNull);
    }
}
