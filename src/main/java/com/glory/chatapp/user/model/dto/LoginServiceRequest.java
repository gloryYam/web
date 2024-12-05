package com.glory.chatapp.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginServiceRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @Size(min = 8, max = 20)
    private String password;

    public LoginServiceRequest(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
