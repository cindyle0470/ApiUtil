package com.example.ap0365.bean;

public class ErrorResult extends BasicResult{

    public String errorCode;
    public String errorMessage;

    public ErrorResult(String status, String errorCode, String errorMessage) {
        super(status);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
