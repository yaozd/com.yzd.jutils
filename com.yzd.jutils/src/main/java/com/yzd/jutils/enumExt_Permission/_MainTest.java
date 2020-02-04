package com.yzd.jutils.enumExt_Permission;

import org.junit.Test;

public class _MainTest {
    /***
     * java编程中使用二进制进行权限或状态控制
     * https://blog.csdn.net/scorpio3k/article/details/8100890
     * 巧妙运用二进制验证权限
     * https://www.cnblogs.com/zxlovenet/p/3526627.html
     */
    @Test
    public void t1() {
        int value = TaskEnum.全部.getValue();
        //value=3;
        //value=4;
        //value=6;
        System.out.println(value);
        if ((value & TaskEnum.个人.getValue()) == TaskEnum.个人.getValue()) {
            System.out.println("执行任务：" + TaskEnum.个人.name());
        }
        if ((value & TaskEnum.团长.getValue()) == TaskEnum.团长.getValue()) {
            System.out.println("执行任务：" + TaskEnum.团长.name());
        }
        if ((value & TaskEnum.企业.getValue()) == TaskEnum.企业.getValue()) {
            System.out.println("执行任务：" + TaskEnum.企业.name());
        }
        //(求补、与运算)是=删除权限
        int t1 = value & (~TaskEnum.个人.getValue());
        System.out.println(t1);
        //或是=增加权限
        int t2 = value | TaskEnum.个人.getValue();
        System.out.println(t2);
        //与是=判断是否佣有此权限
        boolean t3 = (value & TaskEnum.个人.getValue()) == TaskEnum.个人.getValue();
        System.out.println(t3);
    }
}
