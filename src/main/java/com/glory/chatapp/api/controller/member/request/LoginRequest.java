package com.glory.chatapp.api.controller.member.request;

import com.glory.chatapp.api.service.member.request.LoginServiceRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

/*
    1. 유효성 처리 하기
 */
@Getter
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @Size(min = 8, max = 20)
    private String password;

    @Builder
    public LoginRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public LoginServiceRequest toServiceDto() {
        return new LoginServiceRequest(username, email, password);
    }
}
