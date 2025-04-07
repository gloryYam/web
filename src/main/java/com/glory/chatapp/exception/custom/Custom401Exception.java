package com.glory.chatapp.exception.custom;

import com.glory.chatapp.exception.CustomException;
import com.glory.chatapp.exception.ErrorCode_401;

public class Custom401Exception extends CustomException {

    private ErrorCode_401 errorCode401;

    public Custom401Exception(ErrorCode_401 errorCode401) {
        super(errorCode401.getStatus(), errorCode401.getMessage());

        this.errorCode401 = errorCode401;
    }

    @Override
    public String getErrorCode() {
        return errorCode401.name();
    }
}
