package com.glory.chatapp.exception._404;

import com.glory.chatapp.exception.ErrorCode_404;
import com.glory.chatapp.exception.custom.Custom404Exception;

public class UserNotFoundException extends Custom404Exception {
    public UserNotFoundException() {
        super(ErrorCode_404.USER_NOT_FOUND_EXCEPTION);
    }
}
