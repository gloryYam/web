package com.glory.chatapp.exception.user;

import com.glory.chatapp.exception.ErrorCode_400;
import com.glory.chatapp.exception.custom.Custom400Exception;

public class TermsNotAgreedException extends Custom400Exception {

    public TermsNotAgreedException() {
        super(ErrorCode_400.TERMS_NOT_AGREED_EXCEPTION);
    }
}
