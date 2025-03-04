package com.glory.chatapp.api.service.member.response;

import com.glory.chatapp.domain.member.Role;
import com.glory.chatapp.domain.member.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignResponse {

    private String username;
    private String email;
    private Role role;

    @Builder
    public SignResponse(String username, String email, Role role) {
        this.username = username;
        this.email = email;
        this.role = role;
    }

    public static SignResponse of(Member member) {
        return SignResponse.builder()
                .username(member)
                .email(member)
                .role(Role.USER)
                .build();
    }
}
