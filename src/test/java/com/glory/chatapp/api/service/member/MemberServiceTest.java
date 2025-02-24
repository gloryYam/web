package com.glory.chatapp.api.service.member;

import com.glory.chatapp.IntegrationTestSupport;
import com.glory.chatapp.api.service.member.request.RegisterServiceRequest;
import com.glory.chatapp.api.service.member.response.SignResponse;
import com.glory.chatapp.domain.member.entity.Member;
import com.glory.chatapp.domain.member.entity.Role;
import com.glory.chatapp.domain.repository.MemberRepository;
import com.glory.chatapp.exception.user.EmailDuplicateException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MemberServiceTest extends IntegrationTestSupport {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("회원가입")
    void emailRegister() {

        // given
        RegisterServiceRequest request = RegisterServiceRequest.builder()
                .username("test")
                .email("test@test.com")
                .password("test")
                .build();

        //when
        SignResponse signup = memberService.emailRegister(request);

        //then
        Assertions.assertThat(signup).isNotNull();
        Assertions.assertThat(signup.getUsername()).isEqualTo(request.getUsername());

        Member savedMember = memberRepository.findByEmail("test@test.com").orElseThrow();
        Assertions.assertThat(passwordEncoder.matches("test", savedMember.getPassword())).isTrue();
    }

    @Test
    @DisplayName("중복 이메일 검증")
    void duplicateEmail() {

        Member member = new Member("test", "test@test.com", "test", Role.USER);
        memberRepository.save(member);

        RegisterServiceRequest request = RegisterServiceRequest.builder()
                .username("test")
                .email("test@test.com")
                .password("test")
                .build();

        assertThatThrownBy(() -> memberService.emailRegister(request))
                .isInstanceOf(EmailDuplicateException.class)
                .hasMessage("중복된 이메일입니다.");
    }
}