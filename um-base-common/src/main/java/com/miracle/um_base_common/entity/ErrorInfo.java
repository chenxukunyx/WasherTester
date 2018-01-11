package com.miracle.um_base_common.entity;

/**
 * Created by 万启林 on 2015/7/28.
 */
public class ErrorInfo {
    private String errorCode;
    private String message;

    public ErrorInfo() {}

    public ErrorInfo(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
