package com.glory.chatapp.api.controller.member.request;

import com.glory.chatapp.api.service.member.request.RegisterServiceRequest;
import com.glory.chatapp.domain.member.RegistrationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.util.List;

/*
    1. 유효성 처리 하기
 */
public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String memberId;

    @Size(min = 4, max = 20)
    private String password;

    private List<TermsAgreementRequest> termsAgreementRequestList;

    private RegistrationType registrationType;

    @Builder
    public RegisterRequest(String username, String memberId, String password, List<TermsAgreementRequest> termsAgreementRequestList, boolean emailVerified, RegistrationType registrationType) {
        this.username = username;
        this.memberId = memberId;
        this.password = password;
        this.termsAgreementRequestList = termsAgreementRequestList;
        this.registrationType = registrationType;
    }

    public RegisterServiceRequest toServiceDto() {
        return new RegisterServiceRequest(username, memberId, password, termsAgreementRequestList, RegistrationType.EMAIL);
    }

}
