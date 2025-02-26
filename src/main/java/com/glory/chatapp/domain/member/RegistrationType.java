package com.glory.chatapp.domain.member;

public enum RegistrationType {

    EMAIL("이메일"),
    KAKAO("카카오톡"),
    NAVER("네이버"),
    GOOGLE("구글"),
    FACEBOOK("페이스북"),
    APPLE("애플");

    private final String value;

    RegistrationType(String value) {
        this.value = value;
    }
}
