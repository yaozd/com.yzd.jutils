package com.yzd.jutils.Excel;

import com.yzd.jutils.annotationExt.Value;

/**
 * Created by zd.yao on 2017/6/15.
 */
public class TableModel {
    private String name;

    public String getName() {
        return name;
    }

    @Value(value = "承保止期")
    public void setName(String name) {
        this.name = name;
    }
}
