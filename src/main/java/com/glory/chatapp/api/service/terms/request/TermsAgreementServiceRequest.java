package com.glory.chatapp.api.service.terms.request;

import lombok.Builder;

public class TermsAgreementServiceRequest {

    private Long id;
    private String type;
    private String title;
    private boolean required;

    @Builder
    public TermsAgreementServiceRequest(Long id, String type, String title, boolean required) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.required = required;
    }
}
