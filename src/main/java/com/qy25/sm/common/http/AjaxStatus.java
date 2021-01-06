package com.qy25.sm.common.http;

public enum AjaxStatus {
    OK(20000,"操作成功"),
    ERROR(50000,"操作失败"),
    UN_LOGIN(55555,"未登录"),
    ;

    private int status;
    private String message;

    AjaxStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    AjaxStatus() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
