package com.glory.chatapp.user.model.dto.response;

import com.glory.chatapp.user.model.entity.Role;
import com.glory.chatapp.user.model.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignResponse {

    private String username;
    private String email;
    private String password;
    private Role role;

    @Builder
    public SignResponse(String username, String email, String password, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static SignResponse of(User user) {
        return SignResponse.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(Role.USER)
                .build();
    }
}
