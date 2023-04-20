package com.standard.entity;

import io.jsonwebtoken.Claims;

public class checkResult {

    private int errCode;
    private boolean success;

    private String msg;

    public String getMsg(){
        return msg;
    }
    public void setMsg(String a){
        this.msg = a;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    private Claims claims;

}
