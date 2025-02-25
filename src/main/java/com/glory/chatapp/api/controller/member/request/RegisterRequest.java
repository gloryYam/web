package com.glory.chatapp.api.controller.member.request;

import com.glory.chatapp.api.service.member.request.RegisterServiceRequest;
import com.glory.chatapp.domain.member.entity.RegistrationType;
import com.glory.chatapp.exception.user.TermsNotAgreedException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/*
    1. 유효성 처리 하기
 */
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @Size(min = 4, max = 20)
    private String password;

    private boolean termsAgreed;

    private boolean emailVerified = false;

    private RegistrationType registrationType;

    public RegisterRequest(String username, String email, String password, boolean termsAgreed, boolean emailVerified, RegistrationType registrationType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.termsAgreed = termsAgreed;
        this.emailVerified = emailVerified;
        this.registrationType = registrationType;
    }

    @Builder
    public RegisterServiceRequest toServiceDto() {
        return new RegisterServiceRequest(username, email, password, termsAgreed, emailVerified, registrationType);
    }

    public void validateTermsAgreement() {
        if(!termsAgreed) {
            throw new TermsNotAgreedException();
        }
    }
}
