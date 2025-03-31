package com.glory.chatapp.api.service.member;

import com.glory.chatapp.IntegrationTestSupport;
import com.glory.chatapp.api.controller.member.request.TermsAgreementRequest;
import com.glory.chatapp.api.service.member.request.RegisterServiceRequest;
import com.glory.chatapp.api.service.member.response.SignResponse;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.member.RegistrationType;
import com.glory.chatapp.domain.member.Role;
import com.glory.chatapp.domain.terms.Terms;
import com.glory.chatapp.domain.terms.Type;
import com.glory.chatapp.exception.user.EmailDuplicateException;
import com.glory.chatapp.repository.MemberRepository;
import com.glory.chatapp.repository.terms.TermsRepository;
import com.glory.chatapp.repository.userTemrs.UserTermsRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserTermsRepository userTermsRepository;

    @Autowired
    private TermsRepository termsRepository;

    private RegisterServiceRequest request;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("회원가입 성공 테스트 ")
    void testEmailRegisterSuccess() {

        List<Terms> termsList = List.of(
                new Terms(1L, "서비스 이용 약관", "test", Type.MANDATORY),
                new Terms(2L, "개인정보 처리방침", "test", Type.MANDATORY),
                new Terms(3L, "마케팅 수신 동의", "test", Type.OPTIONAL)
        );
        termsRepository.saveAll(termsList);

        RegisterServiceRequest request = RegisterServiceRequest.builder()
                .username("testUser")
                .memberId("test@example")
                .password("password123")
                .termsAgreed(List.of(
                        new TermsAgreementRequest(1L, "PRIVACY_POLICY", "서비스 이용 약관", true, true),   // 필수 약관 동의
                        new TermsAgreementRequest(2L, "PRIVACY_POLICY", "개인정보 처리방침", true, true),   // 필수 약관 동의
                        new TermsAgreementRequest(3L, "PRIVACY_POLICY", "마케팅 수신 동의", false, false)   // 선택 약관 거부
                ))
                .registrationType(RegistrationType.EMAIL)
                .build();

        SignResponse response = memberService.emailRegister(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getEmail()).isEqualTo(request.getMemberId());
        assertThat(response.getUsername()).isEqualTo(request.getUsername());

        // 실제 DB에 데이터가 저장되었는지 확인
        Member savedMember = memberRepository.findByMemberId(request.getMemberId());
        assertThat(savedMember).isNotNull();

        // 약관 동의 내역 저장 확인
        long count = userTermsRepository.count();
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("회원가입 실패 - 중복 이메일 예외")
    void testEmailRegisterDuplicateEmail() {
        // Given: 이미 가입된 사용자 추가
        memberService.emailRegister(request);

        // When & Then: 같은 이메일로 가입 시 예외 발생해야 함
        assertThatThrownBy(() -> memberService.emailRegister(request))
                .isInstanceOf(EmailDuplicateException.class);
    }
}