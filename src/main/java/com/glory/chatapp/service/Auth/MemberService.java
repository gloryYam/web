package com.glory.chatapp.service.Auth;

import com.glory.chatapp.api.controller.auth.request.TermsAgreementRequest;
import com.glory.chatapp.service.Auth.request.RegisterServiceRequest;
import com.glory.chatapp.service.Auth.response.SignResponse;
import com.glory.chatapp.domain.member.Member;
import com.glory.chatapp.domain.userTerms.UserTerms;
import com.glory.chatapp.domain.userTerms.UserTermsId;
import com.glory.chatapp.exception._404.UserNotFoundException;
import com.glory.chatapp.exception.user.EmailDuplicateException;
import com.glory.chatapp.repository.MemberRepository;
import com.glory.chatapp.repository.terms.TermsRepository;
import com.glory.chatapp.repository.userTemrs.UserTermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 컨트롤러에서 ApiResponse<T> 받기
 */
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final UserTermsRepository userTermsRepository;
    private final TermsRepository termsRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param request
     * @return
     */
    public SignResponse emailRegister(RegisterServiceRequest request) {

        emailDuplicateCheck(request.getUsernmae());                // 중복체크

        String encodedPassword = encodePssword(request.getPassword());
        Member member = Member.of(request.getNickname(), request.getUsernmae(), encodedPassword);

        Member saveMember = memberRepository.save(member);

        saveUserTerms(member, request.getTermsAgreed());        // 약관 동의 저장

        return saveMember.toSignResponse();
    }


    /**
     * 약관 동의 저장하기
     * @param member
     * @param agreements
     */
    private void  saveUserTerms(Member member, List<TermsAgreementRequest> agreements) {
        if(agreements.isEmpty() || agreements == null) {
            throw new IllegalArgumentException("약관 동의 정보가 없습니다.");
        }

        List<UserTerms> userTermsList = agreements.stream()
                .map(agreement -> new UserTerms(
                        new UserTermsId(member.getMemberId(), agreement.getTermsId()),
                        member,
                        termsRepository.findById(agreement.getTermsId()).orElseThrow(),
                        agreement.isAgreed(),
                        LocalDateTime.now()
                ))
                .collect(Collectors.toList());

        userTermsRepository.saveAll(userTermsList);
    }

    /**
     * 중복체크 메소드
     * @param username
     */
    private void emailDuplicateCheck(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(EmailDuplicateException::new);
    }


    /**
     * 패스워드 인코딩
     * @return
     */
    private String encodePssword(String password) {
        return passwordEncoder.encode(password);
    }
}
