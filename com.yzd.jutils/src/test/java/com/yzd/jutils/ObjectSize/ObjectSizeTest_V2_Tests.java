package com.yzd.jutils.ObjectSize;

import com.yzd.jutils.person.Person;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;

public class ObjectSizeTest_V2_Tests {
    @Test
    public void getAllFieldSizeTest(){
        Person person = new Person(1, "1", "2", 3);
        Reflections reflections = new Reflections(Person.class);
    }
}
