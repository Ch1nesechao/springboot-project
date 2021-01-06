package com.qy25.sm.exception;


import com.qy25.sm.common.http.AjaxStatus;
import lombok.Data;

@Data
public class MyLoginException extends RuntimeException{

    private int status;
    private String message;

    public MyLoginException(AjaxStatus ajaxStatus) {
        this.status = ajaxStatus.getStatus();
        this.message = ajaxStatus.getMessage();
    }
}
