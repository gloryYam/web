package com.glory.chatapp.exception;

import lombok.Getter;

@Getter
public abstract class ExceptionBase extends RuntimeException{

    protected String errorMessage;
    protected ErrorResponseCode errorCode;

    public ExceptionBase(String errorMessage, ErrorResponseCode errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public abstract int getStatusCode();

}
