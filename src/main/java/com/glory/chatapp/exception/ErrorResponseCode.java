package com.glory.chatapp.exception;


import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ErrorResponseCode {

    // HTTP_CODE 200
    SUCCESS(2000, "Success"),

    // HTTP_CODE 204
    ACCEPTED(2041, "Accepted"),

    // HTTP_CODE 400
    // InvalidParameterException
    MISSING_REQUIRED_PARAMETER(4001, "Missing required parameter"),
    INVALID_PARAMETER(4002, "Invalid parameter"),

    // HTTP_CODE 401
    // AuthException
    NO_AUTH_TOKEN(4011, "No auth token"),
    INVALID_AUTH_TOKEN(4012, "Invalid auth token"),
    EXPIRED_AUTH_TOKEN(4013, "Expired auth token"),
    FAILED_LOGIN(4014, "Failed login"),
    DUPLICATE_LOGIN(4015, "Duplicate login"),
    INVALID_VERIFICATION_CODE(4016, "Invalid verification code"),
    INVALID_DEVICE_TOKEN(4017, "Invalid device token"),

    // HTTP_CODE 403
    // PermissionDeniedException
    NOT_ALLOWED(4031, "Not allowed"),
    NOT_ADMIN_USER(4032, "Not admin user"),

    // HTTP_CODE 409
    DUPLICATE_EMAIL(4091,"Duplicate email"),;


    private final int code;
    private final String message;

    ErrorResponseCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 위에 정의된 에러코드(e.code) 순회하면서 비교
     * 같은 Value 가 있으면 반환
     */
    public static ErrorResponseCode getErrorName(final int code) {
        return Arrays.stream(ErrorResponseCode.values()).filter(e -> e.code == code)
                .findFirst()
                .orElse(null);
    }
}
