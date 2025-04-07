package com.glory.chatapp.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode_401 {

    INVALID_CREDENTIALS_EXCEPTION(HttpStatus.UNAUTHORIZED.value(), "아이디 또는 비밀번호가 틀렸습니다.");

    private final int status;
    private final String message;

    ErrorCode_401(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
