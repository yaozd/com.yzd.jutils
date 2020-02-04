package com.yzd.jutils.classExt;

import com.yzd.jutils.person.Person;
import com.yzd.jutils.print.PrintUtil;
import org.junit.Test;

/**
 * Created by zd.yao on 2017/11/30.
 */
public class _MainTest {
    @Test
    public void Example() {
        Person person = new Person(1, "address", "name", 12);
        int size = ClassSize.calcSize(person);
        PrintUtil.outLn("类实例大小" + size);
    }
}
