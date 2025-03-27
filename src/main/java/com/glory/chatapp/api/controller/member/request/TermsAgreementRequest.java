package com.glory.chatapp.api.controller.member.request;

/*
{
  "terms": [
    {
      "id": 1,
      "type": "TERMS_OF_SERVICE",
      "title": "서비스 이용 약관",
      "required": true
    },
    {
      "id": 2,
      "type": "PRIVACY_POLICY",
      "title": "개인정보 처리방침",
      "required": true
    },
    {
      "id": 3,
      "type": "MARKETING",
      "title": "마케팅 수신 동의",
      "required": false
    }
  ]
}
 */
public class TermsAgreementRequest {

    private Long id;                // 약관 ID
    private String type;            // 약관 타입 (TERMS_OF_SERVICE, PRIVACY_POLICY 등)
    private String title;           // 약관 제목
    private boolean required;       // 필수,선택 여부
    private boolean agreed;         // 동의 여부


    public TermsAgreementRequest(Long id, String type, String title, boolean required, boolean agreed) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.required = required;
        this.agreed = agreed;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean isAgreed() {
        return agreed;
    }
}
