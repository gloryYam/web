package com.glory.chatapp.user.service;

import com.glory.chatapp.exception.ErrorResponseCode;
import com.glory.chatapp.exception.user.EmailDuplicateException;
import com.glory.chatapp.user.model.dto.LoginServiceRequest;
import com.glory.chatapp.user.model.dto.response.SignResponse;
import com.glory.chatapp.user.model.entity.User;
import com.glory.chatapp.user.repository.MemberRepository;
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
        User user = User.of(request.getUsername(), request.getEmail(), encodePassword);

        User saveUser = memberRepository.save(user);

        return SignResponse.of(saveUser);
    }

    private void emailDuplicateCheck(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(e -> {
                    throw new EmailDuplicateException(ErrorResponseCode.DUPLICATE_EMAIL.getMessage(), ErrorResponseCode.DUPLICATE_LOGIN);
                });
    }
}
