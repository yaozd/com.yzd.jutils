package com.yzd.jutils.enumExt.ext2;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Set;

@Slf4j
public class _MainTest {
    /**
     * 通过反射的方式获得CODE值的集合
     */
    @Test
    public void getCodeListByReflect() {
        Set<Integer> codes = TbEnumExtend.getCodeList(TbPermissionEnum.visibleStatus.class, "CODE");
        codes.forEach(m -> log.info(m + ""));
    }

    /**
     * 查验当前CODE值是否有效
     */
    @Test
    public void checkCode() {
        EnumUtil.checkCode("可见状态", TbPermissionEnum.visibleStatus.CODES, 10);
        //
        Set<Integer> codes = TbEnumExtend.getCodeList(TbPermissionEnum.visibleStatus.class, "CODE");
        boolean isExist1 = codes.contains(1);
        log.info(isExist1 + "");
        boolean isExist2 = codes.contains(10);
        log.info(isExist2 + "");
    }
}
