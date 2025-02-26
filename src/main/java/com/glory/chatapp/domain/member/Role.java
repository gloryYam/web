package com.glory.chatapp.domain.member;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("관리자"),
    MANAGER("매니저"),
    USER("회원");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
