package com.glory.chatapp.service.Auth.response;

import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.member.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignResponse {

    private String nickName;
    private String username;
    private Role role;

    @Builder
    public SignResponse(String nickName, String username, Role role) {
        this.nickName = nickName;
        this.username = username;
        this.role = role;
    }

    public static SignResponse of(Member member) {
        return new SignResponse(member.getNickName(), member.getUsername(), member.getRole());
    }
}
