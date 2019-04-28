package com.yzd.jutils.streamExt;

import com.yzd.jutils.person.Person;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class _MainTest {
    //Java8 Streams API 学习笔记
    //https://blog.csdn.net/ClementAD/article/details/53085322
    //Java 8新特性：全新的Stream API
    //https://www.liaoxuefeng.com/article/001411309538536a1455df20d284b81a7bfa2f91db0f223000
    @Test
    public void t1(){
        //按照年龄归组：
        Map<Integer, List<Person>> personGroups =
                Stream.generate(new PersonSupplier()).
                        limit(100).
                        collect(Collectors.groupingBy(Person::getAge));
        System.out.println(personGroups);
        //按照是否小于18岁进行分组：
        Map<Boolean, List<Person>> children =
                Stream.generate(new PersonSupplier()).
                        limit(100).
                        collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println(children);
    }

    @Test
    public void t2(){
         getPerson();
    }

    private Person getPerson() {
        // Optional.ofNullable - 允许传递为 null 参数
        Integer value1 = null;
        Optional<Integer> a = Optional.ofNullable(value1);
        List<Person> itemList4Person=null;
        if(itemList4Person==null){return null;}
        //如果是在mybatis-selectOnlyOne
        itemList4Person.stream().findFirst().orElseGet(()->null);
        return itemList4Person.stream().findFirst().get();
    }
}
