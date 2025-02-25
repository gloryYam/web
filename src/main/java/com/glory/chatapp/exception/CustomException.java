package com.glory.chatapp.exception;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException{
    private int status;
    private String message;

    public CustomException(int status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public CustomException(int status, String errorMessage, String errorMessageArgument) {
        this(status, String.format(errorMessage, errorMessageArgument));
    }

    public abstract String getErrorCode();
}
