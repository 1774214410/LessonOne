package com.liuzw.common.config.exception;

/**
 * 自定义异常
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/7 14:23
 */
public class ApiException extends RuntimeException {

    private static final long serialVersionUID = -5024531408125552116L;

    /**异常码*/
    private String code;

    public ApiException() {
    }

    public ApiException(String code,String message){
        super(message);
        this.code = code;
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
