package com.qy25.sm.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * controller层的返回值
 * @param <T>
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//为空的数值  传输时不显示
public class AjaxResult<T> {

    private int status;
    private String message;
    private T data;

    /**
     * 静态方法中不能使用this  但是可以通过set方法进行赋值
     * 都是通过setData产生的方法
     * @param ajaxStatus
     * @param data
     * @param <T>
     * @return
     */
    private static <T> AjaxResult<T> setData(AjaxStatus ajaxStatus, T data) {
        AjaxResult<T> ajaxResult = new AjaxResult<>();
        ajaxResult.setStatus(ajaxStatus.getStatus());
        ajaxResult.setMessage(ajaxStatus.getMessage());
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 成功无参
     *
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> success() {
        return setData(AjaxStatus.OK, null);
    }

    /**
     * 成功有参
     */
    public static <T> AjaxResult<T> success(T data){
        return setData(AjaxStatus.OK, data);
    }

    /**
     * 成功自定义状态吗
     */
    public static <T> AjaxResult<T> success(AjaxStatus ajaxStatus){
        return setData(ajaxStatus, null);
    }

    /**
     * 成功自定义
     */
    public static <T> AjaxResult<T> success(AjaxStatus ajaxStatus,T data){
        return setData(ajaxStatus,data);
    }

    /**
     * 失败无参
     *
     * @param <T>
     * @return
     */
    public static <T> AjaxResult<T> error() {
        return setData(AjaxStatus.ERROR, null);
    }

    /**
     * 失败有参
     */
    public static <T> AjaxResult<T> error(T data){
        return setData(AjaxStatus.ERROR, data);
    }

    /**
     * 失败自定义状态吗
     */
    public static <T> AjaxResult<T> error(AjaxStatus ajaxStatus){
        return setData(ajaxStatus, null);
    }

    /**
     * 失败自定义
     */
    public static <T> AjaxResult<T> error(AjaxStatus ajaxStatus,T data){
        return setData(ajaxStatus,data);
    }

}
