package com.glory.chatapp.exception.custom;

import com.glory.chatapp.exception.CustomException;
import com.glory.chatapp.exception.ErrorCode_400;
import com.glory.chatapp.exception.ErrorCode_404;
import lombok.Getter;


@Getter
public abstract class Custom404Exception extends CustomException {

    private ErrorCode_404 errorCode404;

    public Custom404Exception(ErrorCode_404 errorCode404) {
        super(errorCode404.getStatus(), errorCode404.getErrorMessage());
        this.errorCode404 = errorCode404;
    }

    @Override
    public String getErrorCode() {
        return "404";
    }
}
