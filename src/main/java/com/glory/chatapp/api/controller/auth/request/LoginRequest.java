package com.glory.chatapp.api.controller.auth.request;

import com.glory.chatapp.service.Auth.request.LoginServiceRequest;
import lombok.Getter;

@Getter
public class LoginRequest {

    private String loginId;
    private String password;

    public LoginRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public LoginServiceRequest toLoginServiceRequest() {
        return LoginServiceRequest.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }
}
