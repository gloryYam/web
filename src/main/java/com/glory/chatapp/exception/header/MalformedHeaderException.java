package com.glory.chatapp.exception.header;

import com.glory.chatapp.exception.ErrorCode_400;
import com.glory.chatapp.exception.custom.Custom400Exception;

public class MalformedHeaderException extends Custom400Exception {

    public MalformedHeaderException() {
        super(ErrorCode_400.MALFORMAD_HEADER);
    }
}
