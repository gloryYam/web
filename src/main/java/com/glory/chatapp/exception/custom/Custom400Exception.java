package com.glory.chatapp.exception.custom;

import com.glory.chatapp.exception.CustomException;
import com.glory.chatapp.exception.ErrorCode_400;
import lombok.Getter;


@Getter
public abstract class Custom400Exception extends CustomException {

    private ErrorCode_400 errorCode400;

    public Custom400Exception(ErrorCode_400 errorCode400) {
        super(errorCode400.getStatus(), errorCode400.getErrorMessage());
        this.errorCode400 = errorCode400;
    }

    @Override
    public String getErrorCode() {
        return errorCode400.name();
    }
}
