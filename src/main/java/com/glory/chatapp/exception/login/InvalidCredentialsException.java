package com.glory.chatapp.exception.login;

import com.glory.chatapp.exception.ErrorCode_401;
import com.glory.chatapp.exception.custom.Custom401Exception;

public class InvalidCredentialsException extends Custom401Exception {
    public InvalidCredentialsException() {
        super(ErrorCode_401.INVALID_CREDENTIALS_EXCEPTION);
    }
}
