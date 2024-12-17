package com.glory.chatapp.api.service.member;

import com.glory.chatapp.exception.ErrorResponseCode;
import com.glory.chatapp.exception.user.EmailDuplicateException;
import com.glory.chatapp.api.service.member.request.LoginServiceRequest;
import com.glory.chatapp.api.service.member.response.SignResponse;
import com.glory.chatapp.domain.member.entity.Member;
import com.glory.chatapp.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 컨트롤러에서 ApiResponse<T> 받기
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public SignResponse signup(LoginServiceRequest request) {

        // 중복체크
        emailDuplicateCheck(request.getEmail());

        String encodePassword = passwordEncoder.encode(request.getPassword());
        Member member = Member.of(request.getUsername(), request.getEmail(), encodePassword);

        Member saveMember = memberRepository.save(member);

        return SignResponse.of(saveMember);
    }

    private void emailDuplicateCheck(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(e -> {
                    throw new EmailDuplicateException(ErrorResponseCode.DUPLICATE_EMAIL.getMessage(), ErrorResponseCode.DUPLICATE_EMAIL);
                });
    }
}
