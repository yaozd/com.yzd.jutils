package com.yzd.jutils.mybatisEXt.mybatisTemplateForExtendResult;

public class TbIfcertPushLogWithBLOBs extends TbIfcertPushLog {
    private String requestMsg;

    private String responseMsg;

    public String getRequestMsg() {
        return requestMsg;
    }

    public void setRequestMsg(String requestMsg) {
        this.requestMsg = requestMsg == null ? null : requestMsg.trim();
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg == null ? null : responseMsg.trim();
    }
}