package com.yzd.jutils.guava.objectsExt;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import org.junit.Test;

/**
 * Created by zd.yao on 2018/9/9.
 */
public class ObjectsGuava {
    @Test
    public void t1(){
        Objects.equal("a", "a"); // returns true
        Objects.equal(null, "a"); // returns false
        Objects.equal("a", null); // returns false
        Objects.equal(null, null); // returns true
        //为null给默认值--推荐MoreObjects方法-byArvin
        String ret=null;
        String val= MoreObjects.firstNonNull(ret, "默认值");//使用MoreObjects.firstNonNull
        System.out.println(val);
    }
    class Person implements Comparable<Person> {
        private String lastName;
        private String firstName;
        private int zipCode;
        public int compareTo(Person that) {
            return ComparisonChain.start()
                    .compare(this.lastName, that.lastName)
                    .compare(this.firstName, that.firstName)
                    .compare(this.zipCode, that.zipCode)
                    .result();
        }
    }
}
