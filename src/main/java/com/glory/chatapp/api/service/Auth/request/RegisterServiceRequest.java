package com.glory.chatapp.api.service.Auth.request;

import com.glory.chatapp.api.controller.auth.request.TermsAgreementRequest;
import com.glory.chatapp.domain.member.RegistrationType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterServiceRequest {

    private String username;

    private String memberId;

    private String password;

    private List<TermsAgreementRequest> termsAgreed;

    private RegistrationType registrationType;

    @Builder
    public RegisterServiceRequest(String username, String memberId, String password, List<TermsAgreementRequest> termsAgreed, RegistrationType registrationType) {
        this.username = username;
        this.memberId = memberId;
        this.password = password;
        this.termsAgreed = termsAgreed;
        this.registrationType = registrationType;
    }
}
