package com.yzd.jutils.kryoExt;

import com.yzd.jutils.person.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class _MainTest {

    //Kryo 是一个快速序列化/反序列化工具
    /***
     * Kryo 使用指南
     * https://www.cnblogs.com/hntyzgn/p/7122709.html
     */
    @Test
    public void t1(){
        List<Person> personList=new ArrayList<>();
        personList.add(new Person(2,"xx","xx",2));
        String tempStr = KryoUtil.writeToString(personList);
        log.info(tempStr);
        //tempStr 就是序列化的结果
        List<Person> personListNew = KryoUtil.readFromString(tempStr);
    }
}
