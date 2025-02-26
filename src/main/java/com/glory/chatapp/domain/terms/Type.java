package com.glory.chatapp.domain.terms;

public enum Type {

    MANDATORY("필수"),
    OPTIONAL("선택");

    private final String value;

    Type(String value) {
        this.value = value;
    }
}
