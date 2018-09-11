package com.yzd.jutils.JWTExt.t1;

/***
 *
 *
 * Created by yzd on 2018/9/11 10:56.
 */

public class RefreshResultJWT {
    public static RefreshResultJWT success(String token){
        return new RefreshResultJWT(true,token,null);
    }
    public static RefreshResultJWT fail(String errorMsg){
        return new RefreshResultJWT(false,null,errorMsg);
    }
    private RefreshResultJWT(Boolean isOk, String token, String errorMsg) {
        this.isOk = isOk;
        this.token = token;
        this.errorMsg = errorMsg;
    }
    private Boolean isOk;
    private String token;
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
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setIsOk(Boolean ok) {
        isOk = ok;
    }
}
