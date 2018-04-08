package com.yzd.jutils.objectExt;

import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _MainTest {
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
