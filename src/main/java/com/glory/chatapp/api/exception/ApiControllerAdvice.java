package com.glory.chatapp.api.exception;

import com.glory.chatapp.api.ApiResponse;
import com.glory.chatapp.exception.custom.Custom409Exception;
import com.glory.chatapp.exception.response.ErrorResponse;
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

    @ExceptionHandler(Custom409Exception.class)
    public ResponseEntity<ErrorResponse> conflictException(Custom409Exception e) {
        return ErrorResponse.error(e);
    }
}
