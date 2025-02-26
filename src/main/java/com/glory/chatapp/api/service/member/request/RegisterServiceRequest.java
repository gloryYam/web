package com.glory.chatapp.api.service.member.request;

import com.glory.chatapp.domain.member.RegistrationType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RegisterServiceRequest {

    private String username;

    private String email;

    private String password;

    private boolean termsAgreed;

    private boolean emailVerified = false;

    private RegistrationType registrationType;

    @Builder
    public RegisterServiceRequest(String username, String email, String password, boolean termsAgreed, boolean emailVerified, RegistrationType registrationType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.termsAgreed = termsAgreed;
        this.emailVerified = emailVerified;
        this.registrationType = registrationType;
    }
}
