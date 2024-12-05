package com.glory.chatapp.exception.user;

import com.glory.chatapp.exception.ErrorResponseCode;
import com.glory.chatapp.exception.ExceptionBase;

public class EmailDuplicateException extends ExceptionBase {

    public EmailDuplicateException(String errorMessage, ErrorResponseCode errorCode) {
        super(errorMessage, errorCode);
    }

    @Override
    public int getStatusCode() {
        return 401;
    }
}
