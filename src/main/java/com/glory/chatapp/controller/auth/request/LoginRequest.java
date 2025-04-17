package com.glory.chatapp.controller.auth.request;

import com.glory.chatapp.service.Auth.request.LoginServiceRequest;
import lombok.Getter;

@Getter
public class LoginRequest {

    private String usernmae;
    private String password;

    public LoginRequest(String loginId, String password) {
        this.usernmae = loginId;
        this.password = password;
    }

    public LoginServiceRequest toLoginServiceRequest() {
        return LoginServiceRequest.builder()
                .loginId(usernmae)
                .password(password)
                .build();
    }
}
