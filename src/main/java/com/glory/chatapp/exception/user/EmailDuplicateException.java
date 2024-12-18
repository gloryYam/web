package com.glory.chatapp.exception.user;

import com.glory.chatapp.exception.ErrorCode_409;
import com.glory.chatapp.exception.custom.Custom409Exception;

public class EmailDuplicateException extends Custom409Exception {

    public EmailDuplicateException() {
        super(ErrorCode_409.EMAIL_DUPLICATED_CHECK);
    }
}
