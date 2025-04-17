package com.glory.chatapp.service.Auth;

import com.glory.chatapp.IntegrationTestSupport;
import com.glory.chatapp.controller.auth.request.TermsAgreementRequest;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.member.RegistrationType;
import com.glory.chatapp.domain.terms.Terms;
import com.glory.chatapp.domain.terms.Type;
import com.glory.chatapp.exception.user.EmailDuplicateException;
import com.glory.chatapp.repository.MemberRepository;
import com.glory.chatapp.repository.terms.TermsRepository;
import com.glory.chatapp.repository.userTemrs.UserTermsRepository;
import com.glory.chatapp.service.Auth.request.RegisterServiceRequest;
import com.glory.chatapp.service.Auth.response.SignResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

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

        RegisterServiceRequest request = getRegisterServiceRequest("글로리", "test", "1234");

        SignResponse response = memberService.emailRegister(request);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getNickName()).isEqualTo(request.getNickname());
        assertThat(response.getUsername()).isEqualTo(request.getUsernmae());

        // 실제 DB에 데이터가 저장되었는지 확인
        Optional<Member> savedMember = memberRepository.findByUsername(request.getUsernmae());
        assertThat(savedMember).isNotNull();

        // 약관 동의 내역 저장 확인
        long count = userTermsRepository.count();
        assertThat(count).isEqualTo(3);
    }


    @Test
    @DisplayName("회원가입 실패 - 아이디 중복 예외")
    void testEmailRegisterDuplicateEmail() {

        Member member = Member.of("글로리", "test", "1234");
        memberRepository.save(member);

        RegisterServiceRequest request = getRegisterServiceRequest("글로리", "test", "1234");

        // When & Then: 같은 이메일로 가입 시 예외 발생해야 함
        assertThatThrownBy(() -> memberService.emailRegister(request))
                .isInstanceOf(EmailDuplicateException.class);
    }

    private RegisterServiceRequest getRegisterServiceRequest(String nickname, String username, String password) {
        return RegisterServiceRequest.builder()
                .nickname(nickname)
                .usernmae(username)
                .password(password)
                .termsAgreed(List.of(
                        new TermsAgreementRequest(1L, "PRIVACY_POLICY", "서비스 이용 약관", true, true),   // 필수 약관 동의
                        new TermsAgreementRequest(2L, "PRIVACY_POLICY", "개인정보 처리방침", true, true),   // 필수 약관 동의
                        new TermsAgreementRequest(3L, "PRIVACY_POLICY", "마케팅 수신 동의", false, false)   // 선택 약관 거부
                ))
                .registrationType(RegistrationType.EMAIL)
                .build();

    }
}