package com.glory.chatapp.api.controller.member.request;

import com.glory.chatapp.api.service.member.request.RegisterServiceRequest;
import com.glory.chatapp.domain.member.RegistrationType;
import com.glory.chatapp.exception.user.TermsNotAgreedException;
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
    private String email;

    @Size(min = 4, max = 20)
    private String password;

    private List<TermsAgreementRequest> termsAgreementRequestList;

    private boolean emailVerified = false;

    private RegistrationType registrationType;

    public RegisterRequest(String username, String email, String password, List<TermsAgreementRequest> termsAgreementRequestList, boolean emailVerified, RegistrationType registrationType) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.termsAgreementRequestList = termsAgreementRequestList;
        this.emailVerified = emailVerified;
        this.registrationType = registrationType;
    }

    @Builder
    public RegisterServiceRequest toServiceDto() {
        return new RegisterServiceRequest(username, email, password, termsAgreementRequestList, emailVerified, registrationType);
    }


    /**
     * 약관 검증
     * 필수약관을 체크했는지 / 아니라면 -> TermsNotAgreedException
     */
    public void validateTermsAgreement() {
        boolean allRequiredAgreed = termsAgreementRequestList.stream()
                .filter(TermsAgreementRequest::isRequired)
                .allMatch(TermsAgreementRequest::isAgreed);

        if (!allRequiredAgreed) {
            throw new TermsNotAgreedException();
        }
    }
}
