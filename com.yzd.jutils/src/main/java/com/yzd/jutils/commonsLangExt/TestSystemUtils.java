package com.yzd.jutils.commonsLangExt;

import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

public class TestSystemUtils {
    @Test
    public void t1(){
        System.out.println ("SystemUtilsDemo" );
        System.out.println ("获得系统文件分隔符.");
        System.out.println (SystemUtils.FILE_SEPARATOR);

        System.out.println ("获得源文件编码.");
        System.out.println (SystemUtils.FILE_ENCODING);

        System.out.println ("获得ext目录.");
        System.out.println (SystemUtils.JAVA_EXT_DIRS);

        System.out.println ("获得java版本.");
        System.out.println (SystemUtils.JAVA_VM_VERSION);

        System.out.println ("获得java厂商.");
        System.out.println (SystemUtils.JAVA_VENDOR);

    }
}
