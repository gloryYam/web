package com.glory.chatapp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode_404 {

    USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_IMPLEMENTED.value(), "해당 사용자를 찾을 수 없습니다.");


    private final int status;
    private final String errorMessage;
}
