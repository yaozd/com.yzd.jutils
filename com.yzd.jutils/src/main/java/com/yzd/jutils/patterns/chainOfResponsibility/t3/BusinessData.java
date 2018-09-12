package com.yzd.jutils.patterns.chainOfResponsibility.t3;

/***
 *
 *
 * Created by yzd on 2018/9/12 9:48.
 */

public class BusinessData {
    private BusinessStatus status;
    private String dataJson;

    public BusinessData(BusinessStatus status, String dataJson) {
        this.status = status;
        this.dataJson = dataJson;
    }

    public BusinessStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessStatus status) {
        this.status = status;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }
}
