package com.glory.chatapp.exception.custom;

import com.glory.chatapp.exception.CustomException;
import com.glory.chatapp.exception.ErrorCode_409;

public abstract class Custom409Exception extends CustomException {

    private ErrorCode_409 errorCode409;

    public Custom409Exception(ErrorCode_409 errorCode409) {
        super(errorCode409.getStatus(), errorCode409.getErrorMessage());
        this.errorCode409 = errorCode409;
    }

    @Override
    public String getErrorCode() {
        return errorCode409.name();
    }
}
