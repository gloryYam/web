package com.glory.chatapp.exception;

import com.glory.chatapp.controller.ApiResponse;
import com.glory.chatapp.exception.custom.Custom401Exception;
import com.glory.chatapp.exception.custom.Custom409Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> bindException(BindException e) {
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(Custom401Exception.class)
    public ResponseEntity<ErrorResponse> custom401Exception(Custom401Exception e) {
        return ErrorResponse.error(e);
    }

    @ExceptionHandler(Custom409Exception.class)
    public ResponseEntity<ErrorResponse> conflictException(Custom409Exception e) {
        return ErrorResponse.error(e);
    }
}
