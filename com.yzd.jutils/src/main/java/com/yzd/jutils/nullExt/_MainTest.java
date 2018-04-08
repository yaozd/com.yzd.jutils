package com.yzd.jutils.nullExt;

import com.google.common.base.Optional;
import com.yzd.jutils.person.Person;
import lombok.NonNull;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _MainTest {
    @Test
    public void t1()
    {

        //        <dependency>
        //            <groupId>org.apache.commons</groupId>
        //            <artifactId>commons-lang3</artifactId>
        //            <version>3.6</version>
        //        </dependency>
        List<Person> personList=null;
        //集合的isNullOrEmpty
        boolean isEmpty=CollectionUtils.isEmpty(personList);
        System.out.println(isEmpty);
        boolean isNotEmpty=CollectionUtils.isNotEmpty(personList);
        System.out.println(isNotEmpty);
        // 不建议使用guava
        Optional<List<Person>> personListField= Optional.fromNullable(personList);
        System.out.println(personListField.isPresent());
        // 建议使用 commons.lang3
        List<String> stringList=null;
        stringList= ObjectUtils.defaultIfNull(stringList, new ArrayList<String>());
        System.out.println(stringList.isEmpty());
        //t3(null); //不建议使用
        t2(null);//建议使用
    }
    //NotNull 引用org.jetbrains.annotations.NotNull
    //使用 JetBrains 注解库注释你的代码
    //https://zhuanlan.zhihu.com/p/24778947
    //提示：
    //java.lang.IllegalArgumentException: Argument for @NotNull parameter 'notNull' of com/yzd/jutils/objectExt/_MainTest.t2 must not be null
    //        <dependency>
    //            <groupId>org.jetbrains</groupId>
    //            <artifactId>annotations</artifactId>
    //            <version>13.0</version>
    //        </dependency>
    private void t2(@NotNull String notNull){
        System.out.println(notNull);
    }
    //使用的lombok 的NonNull 但是提示信息不全 java.lang.NullPointerException: notNull
    private void t3(@NonNull String notNull){
        System.out.println(notNull);
    }
}
