package com.glory.chatapp.user.model.dto.request;

import com.glory.chatapp.user.model.dto.LoginServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
    1. 유효성 처리 하기
 */
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @Size(min = 8, max = 20)
    private String password;

    public LoginServiceRequest toServiceDto() {
        return new LoginServiceRequest(username, email, password);
    }
}
