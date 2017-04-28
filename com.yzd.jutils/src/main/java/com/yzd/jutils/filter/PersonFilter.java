package com.yzd.jutils.filter;

import com.yzd.jutils.person.Person;

/**
 * Created by zd.yao on 2017/4/28.
 */
public enum PersonFilter implements Filter<Person> {
    nameFilter {
        @Override
        public boolean isFilter(Person s) {
            return "name".equals(s.getName());
        }
    },

    ageFilter {
        @Override
        public boolean isFilter(Person s) {
            return 12 == s.getAge();
        }
    }
}
