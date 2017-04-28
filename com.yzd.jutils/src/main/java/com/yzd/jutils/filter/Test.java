package com.yzd.jutils.filter;

import com.google.common.base.Preconditions;
import com.yzd.jutils.person.Person;
import com.yzd.jutils.print.PrintUtil;

/**
 * todo 枚举实现策略模式
 * Created by zd.yao on 2017/4/28.
 */
public class Test {
    public static void main(String[] args){
        Person person=new Person(1,"address","name",12);
        //
        PersonFilter f1=PersonFilterUtil.getFilter(1);
        Preconditions.checkNotNull(f1,"f1 is null");
        boolean isOk1= f1.isFilter(person);
        PrintUtil.outLn(isOk1);
        //
        PersonFilter f2=PersonFilterUtil2.getFilter(1);
        boolean isOk2=f2.isFilter(person);
        PrintUtil.outLn(isOk2);
        PersonFilter f3=PersonFilterUtil2.getFilter(4);
        Preconditions.checkNotNull(f3,"f3 is null");
        boolean isOk3=f3.isFilter(person);
        PrintUtil.outLn(isOk3);


    }
}
