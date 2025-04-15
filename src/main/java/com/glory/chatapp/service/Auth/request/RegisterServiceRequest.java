package com.glory.chatapp.service.Auth.request;

import com.glory.chatapp.api.controller.auth.request.TermsAgreementRequest;
import com.glory.chatapp.domain.member.RegistrationType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterServiceRequest {

    private String nickname;

    private String usernmae;

    private String password;

    private List<TermsAgreementRequest> termsAgreed;

    private RegistrationType registrationType;

    @Builder
    public RegisterServiceRequest(String nickname, String usernmae, String password, List<TermsAgreementRequest> termsAgreed, RegistrationType registrationType) {
        this.nickname = nickname;
        this.usernmae = usernmae;
        this.password = password;
        this.termsAgreed = termsAgreed;
        this.registrationType = registrationType;
    }
}
