package com.yzd.jutils.JWTExt.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 10:10.
 */

public class VerifyResultJWT {
    public static VerifyResultJWT success(String userJson) {
        return new VerifyResultJWT(true, userJson, null);
    }

    public static VerifyResultJWT fail(String errorMsg) {
        return new VerifyResultJWT(false, null, errorMsg);
    }

    private VerifyResultJWT(Boolean isOk, String userJson, String errorMsg) {
        this.isOk = isOk;
        this.userJson = userJson;
        this.errorMsg = errorMsg;
    }

    private Boolean isOk;
    private String userJson;
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean ok) {
        isOk = ok;
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson;
    }
}
