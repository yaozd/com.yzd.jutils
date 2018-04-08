package com.yzd.jutils.streamExt;

import com.yzd.jutils.person.Person;

import java.util.function.Supplier;

public class PersonSupplier implements Supplier<Person> {
    @Override
    public Person get() {
        return new Person();
    }
}
