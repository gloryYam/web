package com.glory.chatapp.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private int code;
    private String errorCode;
    private String message;
    private Object detail;

    @Builder
    public ErrorResponse(final int code, final String errorCode, final String message, final Object detail) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
        this.detail = detail;
    }
}
