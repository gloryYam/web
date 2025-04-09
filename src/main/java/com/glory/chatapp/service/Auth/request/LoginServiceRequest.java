package com.glory.chatapp.service.Auth.request;

import lombok.Builder;

public class LoginServiceRequest {

    private String loginId;
    private String password;

    @Builder
    public LoginServiceRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
