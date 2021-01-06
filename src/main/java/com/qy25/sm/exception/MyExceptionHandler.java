package com.qy25.sm.exception;

import com.qy25.sm.common.http.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 处理自定义异常  返回json字符串给前段
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyLoginException.class)
    public AjaxResult<Void> exceptionHandler(MyLoginException myLoginException){
        AjaxResult<Void> ajaxResult = new AjaxResult<>();
        ajaxResult.setMessage(myLoginException.getMessage());
        ajaxResult.setStatus(myLoginException.getStatus());
        return ajaxResult;
    }
}
