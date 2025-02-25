package com.glory.chatapp.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode_400 {

    EMPTY_FILE_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "파일 비어있습니다."),
    MALFORMAD_HEADER(HttpStatus.BAD_REQUEST.value(), "잘못된 헤더입니다."),
    TERMS_NOT_AGREED_EXCEPTION(HttpStatus.BAD_REQUEST.value(), "약관을 체크하지 않았습니다.");

    private final int status;
    private final String errorMessage;


}
