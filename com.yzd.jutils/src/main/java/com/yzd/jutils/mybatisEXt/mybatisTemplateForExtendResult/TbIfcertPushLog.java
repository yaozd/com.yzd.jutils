package com.yzd.jutils.mybatisEXt.mybatisTemplateForExtendResult;

import java.util.Date;

public class TbIfcertPushLog {
    private Long id;

    private String batchNum;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

}