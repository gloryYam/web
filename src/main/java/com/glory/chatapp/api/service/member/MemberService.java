package com.glory.chatapp.api.service.member;

import com.glory.chatapp.api.service.member.request.RegisterServiceRequest;
import com.glory.chatapp.api.service.member.response.SignResponse;
import com.glory.chatapp.domain.member.entity.Member;
import com.glory.chatapp.domain.repository.MemberRepository;
import com.glory.chatapp.exception.user.EmailDuplicateException;
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

    /**
     * 회원가입
     * @param request
     * @return
     */
    public SignResponse emailRegister(RegisterServiceRequest request) {

        // 중복체크
        emailDuplicateCheck(request.getEmail());

        String encodedPassword = encodePssword(request.getPassword());

        Member member = Member.of(request.getEmail(), request.getUsername(), encodedPassword, );

        Member saveMember = memberRepository.save(member);

        return SignResponse.of(saveMember);
    }

    /**
     * 중복체크 메소드
     * @param email
     */
    private void emailDuplicateCheck(String email) {
        Member findMember = memberRepository.findByEmail(email);

        if(findMember != null) {
            throw new EmailDuplicateException();
        }
    }

    /**
     * 패스워드 인코딩
     * @return
     */
    private String encodePssword(String password) {
        return passwordEncoder.encode(password);
    }
}
