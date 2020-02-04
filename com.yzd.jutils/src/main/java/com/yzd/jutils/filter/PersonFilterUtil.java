package com.yzd.jutils.filter;

/**
 * todo 写法比较复杂但是不点用内存
 * Created by zd.yao on 2017/4/28.
 */
public enum PersonFilterUtil {
    年龄(1, PersonFilter.ageFilter), 名称(2, PersonFilter.nameFilter);
    private int value;
    private PersonFilter filter;

    private PersonFilterUtil(int val, PersonFilter personFilter) {
        this.value = val;
        this.filter = personFilter;
    }

    public int getValue() {
        return this.value;
    }

    public PersonFilter getFilter() {
        return this.filter;
    }

    public static PersonFilter getFilter(int index) {
        PersonFilterUtil personFilter = getEnum(index);
        return personFilter == null ? null : personFilter.getFilter();
    }

    public static PersonFilterUtil getEnum(int index) {
        for (PersonFilterUtil c : PersonFilterUtil.values()) {
            if (c.getValue() == index) {
                return c;
            }
        }
        return null;
    }
}
